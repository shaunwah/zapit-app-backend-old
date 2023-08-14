package com.shaunwah.zapitappbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Integer id;
    private Integer merchantStoreId;
    private Integer userId;
    private String identifier;
    private Integer status;
    private Boolean isHidden;
    private Date createdAt;
    private Date updatedAt;
}
