package com.fabricio.sevents.api.repository;

import java.util.List;
import java.util.UUID;

import com.fabricio.sevents.api.model.dto.response.PrecoSessaoJoinSessaoResponse;

public interface CustomPrecoSessaoRepository {
  List<PrecoSessaoJoinSessaoResponse> findByIdSessao(UUID idSessao);
}