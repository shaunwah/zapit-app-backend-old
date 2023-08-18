package com.shaunwah.zapitappbackend.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", allocationSize = 5)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
    private String firstName;

//    @Column(nullable = false)
    private String lastName;

    private Date dateOfBirth;

    @Column(nullable = false)
    private String email;

    private String mobilePhone;

    @Column(nullable = false)
    private String roles = "ROLE_USER"; // TODO

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean isMerchant = false;

    @Column(columnDefinition = "boolean default true", nullable = false)
    private Boolean isActive = true;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean isHidden = false;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy;

    @CreationTimestamp
    @Column(columnDefinition = "datetime default current_timestamp", nullable = false, updatable = false)
    private Timestamp createdOn;

    @UpdateTimestamp
    private Timestamp updatedOn;
}
