package com.shaunwah.zapitappbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantStore {
    private Integer id;
    private Integer merchantId;
    private String identifier;
    private String name;
    private String address;
    private String telephone;
    private Boolean isActive;
    private Boolean isHidden;
    private Date createdAt;
    private Date updatedAt;
}
