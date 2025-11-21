package com.lcdamy.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Customer {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;


}
