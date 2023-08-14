package com.shaunwah.zapitappbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private String mobile_phone;
    private Boolean isMerchant;
    private Boolean isActive;
    private Boolean isHidden;
    private Date createdAt;
    private Date updatedAt;
}
