package com.project.orderbulkprocessing.repository;

import com.project.orderbulkprocessing.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payments, UUID> {
}
