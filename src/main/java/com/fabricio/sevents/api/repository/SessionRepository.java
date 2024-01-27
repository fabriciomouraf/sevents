package com.fabricio.sevents.api.repository;

import com.fabricio.sevents.api.model.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface SessionRepository extends CustomSessionRepository,JpaRepository<Session, UUID> {
}