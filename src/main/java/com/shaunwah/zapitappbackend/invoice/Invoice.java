package com.shaunwah.zapitappbackend.invoice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shaunwah.zapitappbackend.merchant.Merchant;
import com.shaunwah.zapitappbackend.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id", nullable = false)
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String identifier;

    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "id")
    private InvoiceStatus invoiceStatus;

//    @Transient
//    private Integer invoices;
//
//    @Transient
//    private Double subTotalPrice;
//
//    @Transient
//    private Double totalPrice;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetails> invoiceDetails = null;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean isLocked = false;

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
