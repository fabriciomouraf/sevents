package com.fabricio.sevents.api.repository;

import com.fabricio.sevents.api.model.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompraRepository extends JpaRepository<Purchase, UUID> {
}