package com.reviewer.assistant.ReviewerAssistant.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class CustomerInformation {
    @Id
  //  @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "emailId", cascade = {CascadeType.ALL})
    @Column(name = "email_address")
    private List<EmailAddress> emailAddressList;

    @OneToMany(mappedBy = "phoneNumberId", cascade = {CascadeType.ALL})
    @Column(name = "phone_numbers")
    private List<PhoneNumber> phoneNumberList;
    @OneToMany(mappedBy = "addressId", cascade = {CascadeType.ALL})
    @Column(name = "addresses")
    private List<Address> addressList;

}
