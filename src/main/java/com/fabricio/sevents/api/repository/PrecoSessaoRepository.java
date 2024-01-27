package com.fabricio.sevents.api.repository;

import com.fabricio.sevents.api.model.domain.PriceSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrecoSessaoRepository extends CustomPrecoSessaoRepository,JpaRepository<PriceSession, UUID> {

}