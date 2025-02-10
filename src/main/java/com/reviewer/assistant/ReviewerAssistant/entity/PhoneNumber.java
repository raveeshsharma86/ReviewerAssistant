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
public class PhoneNumber {

    @Id
//@GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long phoneNumberId;
    private String phoneNumber;
    private PhoneNumberType phoneNumberType;

}
