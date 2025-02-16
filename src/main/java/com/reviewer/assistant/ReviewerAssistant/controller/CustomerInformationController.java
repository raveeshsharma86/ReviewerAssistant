
package com.reviewer.assistant.ReviewerAssistant.controller;

import com.reviewer.assistant.ReviewerAssistant.entity.CustomerInformation;
import com.reviewer.assistant.ReviewerAssistant.repository.CustomerInformationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created a controller
 */
@Controller
public class CustomerInformationController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerInformationController.class);

    @Autowired
    private CustomerInformationRepository customerInformationRepository;

    @PostMapping("/customerInformation")
    public ResponseEntity<CustomerInformation> createTutorial(@RequestBody CustomerInformation customerInformation) {
        try {
            validateCustomerInformation(customerInformation);
            CustomerInformation save = customerInformationRepository
                    .save(customerInformation);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            logger.error("Validation error: " + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error creating customer information", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateCustomerInformation(CustomerInformation customerInformation) {
        if (customerInformation == null) {
            throw new IllegalArgumentException("Customer information cannot be null");
        }
        validateString(customerInformation.getFirstName(), "First name");
        validateString(customerInformation.getLastName(), "Last name");
        validateList(customerInformation.getAddressList(), "Address list");
        validateList(customerInformation.getPhoneNumberList(), "Phone number list");
    }

    private void validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }

    private void validateList(List<?> list, String fieldName) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }

    @GetMapping("/customerInformation/")
    public ResponseEntity<List<CustomerInformation>> getCustomerInformationByFirstName(@RequestParam(required = false) String firstName) {
        try {
            List<CustomerInformation> customerInformationList;
            if (firstName == null) {
                customerInformationList = customerInformationRepository.findAll();
            } else {
                customerInformationList = customerInformationRepository.findByFirstName(firstName);
            }
            return new ResponseEntity<>(customerInformationList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

