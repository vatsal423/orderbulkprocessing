package com.project.orderbulkprocessing.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "items")
@Getter
@Setter
public class Items {

    @Id
    @Column(name = "id")
    @Type(type = "pg-uuid")
    private UUID id;

    @OneToMany(mappedBy = "items")
    private List<OrderItems> orderItemsList;

    @Column(name = "name",nullable =false)
    private String name;

    @Column(name = "price",nullable = false)
    private BigDecimal price;

    @Column(name = "sin_no",nullable = false)
    private int sinNo;

    @Column(name = "description")
    private String description;
}
