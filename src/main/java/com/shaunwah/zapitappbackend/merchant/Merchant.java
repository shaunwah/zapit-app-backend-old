package com.shaunwah.zapitappbackend.merchant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.opencsv.bean.CsvBindByName;
import com.shaunwah.zapitappbackend.user.User;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "merchants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Merchant {
    @CsvBindByName(column = "merchant_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByName(column = "merchant_identifier")
    @Column(nullable = false)
    private String identifier;

    @CsvBindByName(column = "merchant_name")
    @Column(nullable = false)
    private String name;

    @CsvBindByName(column = "merchant_nameAlt")
    private String nameAlt;

    @CsvBindByName(column = "merchant_website")
    private String website;

    @JsonManagedReference("merchant_merchantUsersReference")
    @OneToMany(mappedBy = "merchant")
    private List<MerchantUser> merchantUsers;

    @JsonManagedReference("merchant_merchantStoresReference")
    @OneToMany(mappedBy = "merchant")
    private List<MerchantStore> merchantStores;

    @CsvBindByName(column = "merchant_isActive")
    @Column(columnDefinition = "boolean default true", nullable = false)
    private Boolean isActive;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean isHidden = false;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false, updatable = false)
    private User createdBy;

    @CreationTimestamp
    @Column(columnDefinition = "datetime default current_timestamp", nullable = false, updatable = false)
    private Timestamp createdOn;

    @UpdateTimestamp
    private Timestamp updatedOn;
}
