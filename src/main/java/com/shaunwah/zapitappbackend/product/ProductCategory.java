package com.shaunwah.zapitappbackend.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shaunwah.zapitappbackend.merchant.Merchant;
import com.shaunwah.zapitappbackend.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "product_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String colour;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id", nullable = false)
    private Merchant merchant;

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
