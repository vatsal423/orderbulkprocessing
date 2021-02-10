package com.project.orderbulkprocessing.repository;

import com.project.orderbulkprocessing.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Items, UUID> {
}
