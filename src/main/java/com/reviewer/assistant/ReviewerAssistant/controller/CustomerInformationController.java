package com.reviewer.assistant.ReviewerAssistant.controller;

import com.reviewer.assistant.ReviewerAssistant.entity.CustomerInformation;
import com.reviewer.assistant.ReviewerAssistant.repository.CustomerInformationRepository;
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
    private CustomerInformationRepository customerInformationRepository;

    @PostMapping("/customerInformation")
    public ResponseEntity<CustomerInformation> createTutorial(@RequestBody CustomerInformation customerInformation) {
        try {
            validateCustomerInformation(customerInformation);
            CustomerInformation save = customerInformationRepository
                    .save(customerInformation);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateCustomerInformation(CustomerInformation customerInformation) {
        if(customerInformation == null){
            System.out.println("Customer information cannot be null");
        }if(customerInformation.getFirstName() == null){
            System.out.println("First name cannot be null");
        }if(customerInformation.getLastName() == null){
            System.out.println("last name cannot be null");
        }if(customerInformation.getAddressList() == null){
            System.out.println("Address list cannot be null");
        }if(customerInformation.getPhoneNumberList() == null){
            System.out.println("Address list cannot be null");
        }if(customerInformation.getEmailAddressList() == null){
            System.out.println("Phone list cannot be null");
        }
        //making a copy paste mistake
    }

    @GetMapping("/customerInformation/")
    public ResponseEntity<List<CustomerInformation>> getCustomerInformationByFirstName(@RequestParam(required = false) String  firstName) {
        try {
            List<CustomerInformation> save = null;
            if(firstName == null){
                save= customerInformationRepository
                        .findAll();
            }else{
               save = customerInformationRepository
                        .findByFirstName(firstName);
            }

            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
