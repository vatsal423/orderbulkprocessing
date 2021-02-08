package com.project.orderbulkprocessing.repository;

import com.project.orderbulkprocessing.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customers, UUID> {
}
