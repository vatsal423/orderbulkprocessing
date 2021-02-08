package com.project.orderbulkprocessing.repository;

import com.project.orderbulkprocessing.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, UUID> {
}
