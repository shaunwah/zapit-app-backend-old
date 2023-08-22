package com.shaunwah.zapitappbackend.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shaunwah.zapitappbackend.merchant.Merchant;
import com.shaunwah.zapitappbackend.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(name = "productsUniqueConstraint1", columnNames = { "identifier", "merchant_id" })
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String identifier;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id", nullable = false)
    private Merchant merchant;

    private String image;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id", nullable = false)
    private ProductCategory productCategory;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private Double unitPrice;

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
