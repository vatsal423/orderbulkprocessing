package com.project.orderbulkprocessing.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItems {

    @Id
    @Column(name = "id")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "quantity",nullable = false,columnDefinition = "integer default 1")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id",nullable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "item_id",referencedColumnName = "id",nullable = false)
    private Items items;
}
