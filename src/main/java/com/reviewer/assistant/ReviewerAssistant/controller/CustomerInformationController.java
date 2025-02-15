
package com.reviewer.assistant.ReviewerAssistant.controller;

import com.reviewer.assistant.ReviewerAssistant.entity.CustomerInformation;
import com.reviewer.assistant.ReviewerAssistant.repository.CustomerInformationRepositoryInterface;
import com.reviewer.assistant.ReviewerAssistant.validation.CustomerInformationValidator;
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

    @Autowired
    private CustomerInformationRepositoryInterface customerInformationRepository;

    @Autowired
    private CustomerInformationValidator customerInformationValidator;

    @PostMapping("/customerInformation")
    public ResponseEntity<CustomerInformation> createTutorial(@RequestBody CustomerInformation customerInformation) {
        try {
            customerInformationValidator.validate(customerInformation);
            CustomerInformation save = customerInformationRepository
                    .save(customerInformation);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customerInformation/")
    public ResponseEntity<List<CustomerInformation>> getCustomerInformationByFirstName(@RequestParam(required = false) String  firstName) {
        try {
            List<CustomerInformation> customers = null;
            if(firstName == null){
                customers= customerInformationRepository
                        .findAll();
            }else{
                customers = customerInformationRepository
                        .findByFirstName(firstName);
            }

            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

