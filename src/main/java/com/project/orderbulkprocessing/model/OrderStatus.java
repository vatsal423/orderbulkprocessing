package com.project.orderbulkprocessing.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order_status")
@Getter
@Setter
public class OrderStatus {

    @Id
    @Column(name = "id")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(mappedBy = "orderStatus")
    private List<Orders> ordersList;

}
