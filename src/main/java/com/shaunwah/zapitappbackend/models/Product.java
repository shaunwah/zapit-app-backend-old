package com.shaunwah.zapitappbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private Integer merchantId;
    private String identifier;
    private String name;
    private Double unitPrice;
    private Boolean isHidden;
    private Date createdAt;
    private Date updatedAt;
}
