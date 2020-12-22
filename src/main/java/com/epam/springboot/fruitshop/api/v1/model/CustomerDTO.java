package com.epam.springboot.fruitshop.api.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String customerUrl;
}
