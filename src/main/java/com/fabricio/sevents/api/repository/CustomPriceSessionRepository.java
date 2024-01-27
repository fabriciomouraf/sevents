package com.fabricio.sevents.api.repository;

import java.util.List;
import java.util.UUID;

import com.fabricio.sevents.api.model.dto.response.PriceSessionJoinSessionResponse;

public interface CustomPriceSessionRepository {
  List<PriceSessionJoinSessionResponse> findByIdSessao(UUID idSessao);
}