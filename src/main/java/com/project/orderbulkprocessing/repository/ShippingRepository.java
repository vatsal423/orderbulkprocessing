package com.project.orderbulkprocessing.repository;

import com.project.orderbulkprocessing.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShippingRepository extends JpaRepository<Shipping, UUID> {
}
