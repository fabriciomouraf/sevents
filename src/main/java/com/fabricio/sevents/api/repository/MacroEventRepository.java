package com.fabricio.sevents.api.repository;

import com.fabricio.sevents.api.model.domain.MacroEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MacroEventRepository extends JpaRepository<MacroEvent, UUID> {
}