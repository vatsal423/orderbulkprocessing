package com.project.orderbulkprocessing.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shipping")
@Getter
@Setter
public class Shipping {

    @Id
    @Column(name = "id")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "shipping_type",nullable = false)
    private String shippingType;

    @OneToMany(mappedBy = "shipping")
    private List<Orders> orders;
}
