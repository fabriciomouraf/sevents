package com.fabricio.sevents.api.repository;

import java.util.List;
import java.util.UUID;

import com.fabricio.sevents.api.model.dto.response.SessionIngressResponse;

public interface CustomSessionRepository {
  List<SessionIngressResponse> findByIdIngress(UUID idIngress);
}