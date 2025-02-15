
package com.reviewer.assistant.ReviewerAssistant.controller;

import com.reviewer.assistant.ReviewerAssistant.entity.CustomerInformation;
import com.reviewer.assistant.ReviewerAssistant.service.CustomerInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created a controller
 */
@Controller
public class CustomerInformationController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerInformationController.class);

    @Autowired
    private CustomerInformationService customerInformationService;

    @PostMapping("/customerInformation")
    public ResponseEntity<CustomerInformation> createCustomerInformation(@Valid @RequestBody CustomerInformation customerInformation) {
        try {
            CustomerInformation savedCustomerInformation = customerInformationService.createCustomerInformation(customerInformation);
            return new ResponseEntity<>(savedCustomerInformation, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid customer information provided", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException e) {
            logger.error("Error accessing data", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customerInformation")
    public ResponseEntity<List<CustomerInformation>> getCustomerInformationByFirstName(@RequestParam(required = false) String firstName) {
        try {
            List<CustomerInformation> customerInformations = customerInformationService.getCustomerInformationByFirstName(firstName);
            if (customerInformations == null || customerInformations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(customerInformations, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving customer information", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

