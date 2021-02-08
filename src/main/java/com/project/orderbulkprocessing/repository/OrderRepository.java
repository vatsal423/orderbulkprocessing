package com.project.orderbulkprocessing.repository;

import com.project.orderbulkprocessing.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {
}
