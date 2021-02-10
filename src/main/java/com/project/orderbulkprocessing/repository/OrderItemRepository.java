package com.project.orderbulkprocessing.repository;

import com.project.orderbulkprocessing.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItems, UUID> {
}
