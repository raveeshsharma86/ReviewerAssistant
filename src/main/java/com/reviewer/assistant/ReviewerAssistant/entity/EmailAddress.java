package com.reviewer.assistant.ReviewerAssistant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class EmailAddress {

    @Id
   // @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long emailId;
    private  String emailAddress;
    private EmailAddressType emailAddressType;

}
