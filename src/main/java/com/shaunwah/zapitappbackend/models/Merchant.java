package com.shaunwah.zapitappbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {
    private Integer id;
    private Integer userId;
    private String identifier;
    private String name;
    private String nameAlt;
    private Boolean isActive;
    private Boolean isHidden;
    private Date createdAt;
    private Date updatedAt;
}
