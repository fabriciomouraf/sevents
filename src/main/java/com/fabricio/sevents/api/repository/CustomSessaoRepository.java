package com.fabricio.sevents.api.repository;

import java.util.List;
import java.util.UUID;

import com.fabricio.sevents.api.model.dto.response.SessaoIngressoResponse;

public interface CustomSessaoRepository {
  List<SessaoIngressoResponse> findByIdIngresso(UUID idIngresso);
}