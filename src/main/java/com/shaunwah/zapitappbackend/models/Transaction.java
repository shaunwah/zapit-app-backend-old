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
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction { // TODO possible many to many?
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", allocationSize = 5)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne // TODO pay in full
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    private Invoice invoice;

    @Column(nullable = false)
    private String identifier;

    @OneToOne
    @JoinColumn(name = "status", referencedColumnName = "id")
    private TransactionStatus transactionStatus;

    @OneToOne
    @JoinColumn(name = "payment_method", referencedColumnName = "id")
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private Double amount;

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
