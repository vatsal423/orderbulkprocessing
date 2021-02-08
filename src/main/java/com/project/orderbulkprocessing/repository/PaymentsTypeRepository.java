package com.project.orderbulkprocessing.repository;

import com.project.orderbulkprocessing.model.PaymentsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentsTypeRepository extends JpaRepository<PaymentsType, UUID> {
}
