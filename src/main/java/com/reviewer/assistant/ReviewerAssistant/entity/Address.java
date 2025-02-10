package com.reviewer.assistant.ReviewerAssistant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class Address {
    @Id
   // @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long addressId;
    private String line1;
    private String line2;
    private String line3;
    private Integer pinCode;
    private String cityName;
    private String state;
    private String country;
    private AddressType addressType;


}
