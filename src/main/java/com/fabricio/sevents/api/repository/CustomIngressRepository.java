package com.fabricio.sevents.api.repository;

import com.fabricio.sevents.api.model.dto.response.IngressEventResponse;

import java.util.List;
import java.util.UUID;

public interface CustomIngressRepository {

    List<IngressEventResponse> findByIdEvento(UUID idEvento);

}
