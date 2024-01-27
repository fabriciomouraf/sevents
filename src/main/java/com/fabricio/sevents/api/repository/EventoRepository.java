package com.fabricio.sevents.api.repository;

import com.fabricio.sevents.api.model.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventoRepository extends JpaRepository<Event, UUID> {
}