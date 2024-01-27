package com.fabricio.sevents.api.repository;

import com.fabricio.sevents.api.model.domain.Ingress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngressRepository extends CustomIngressRepository, JpaRepository<Ingress, UUID> {
}