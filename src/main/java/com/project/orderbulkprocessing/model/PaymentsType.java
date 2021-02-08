package com.project.orderbulkprocessing.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "payments_type")
@Getter
@Setter
public class PaymentsType {

    @Id
    @Column(name = "id")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "payment_type",nullable = false)
    private String paymentType;

    @OneToMany(mappedBy = "paymentsType")
    private List<Payments> payments;

}
