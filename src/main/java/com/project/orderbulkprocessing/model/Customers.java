package com.project.orderbulkprocessing.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customers {

    @Id
    @Column(name = "id")
    @Type(type = "pg-uuid")
    private UUID id;

    @OneToMany(mappedBy = "customers")
    private List<Orders> ordersList;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "addressline1",length = 100)
    private String addressLine1;

    @Column(name = "addressline2",length =50)
    private String addressLine2;

    @Column(name = "city",length = 30)
    private String city;

    @Column(name = "state",length = 50)
    private String state;

    @Column(name = "zip")
    private int zip;
}
