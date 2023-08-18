package com.shaunwah.zapitappbackend.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", allocationSize = 5)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    @Column(nullable = false)
    private String identifier;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    private ProductCategory productCategory;

    private String image;

    private String description;

    @Column(nullable = false)
    private Double unitPrice;

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
