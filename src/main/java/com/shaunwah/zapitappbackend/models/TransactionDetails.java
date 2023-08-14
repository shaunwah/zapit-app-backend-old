package com.shaunwah.zapitappbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {
    private Integer id;
    private Integer transactionId;
    private Integer productId;
    private Integer quantity;
    private Double taxRate;
    private Boolean isHidden;
    private Date createdAt;
    private Date updatedAt;
}
