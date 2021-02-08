package com.project.orderbulkprocessing.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders {

    @Id
    @Column(name = "id")
    @Type(type = "pg-uuid")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id",nullable =false)
    private Customers customers;

    @ManyToOne
    @JoinColumn(name = "status_id",referencedColumnName = "id",nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "shipping_id",referencedColumnName = "id",nullable = false)
    private Shipping shipping;

    @OneToMany(mappedBy = "orders")
    private List<Payments> paymentsList;

    @OneToMany(mappedBy = "orders")
    private List<OrderItems> orderItemsList;

    @Column(name = "amount_pretax")
    private BigDecimal amountPreTax;

    @Column(name = "tax_amount")
    private BigDecimal taxAmount;

    @Column(name = "shipping_amount")
    private BigDecimal shippingAmount;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;
}
