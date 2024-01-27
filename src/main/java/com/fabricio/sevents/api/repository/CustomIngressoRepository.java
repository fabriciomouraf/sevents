package com.fabricio.sevents.api.repository;

import com.fabricio.sevents.api.model.dto.response.IngressoEventoResponse;

import java.util.List;
import java.util.UUID;

public interface CustomIngressoRepository {

    List<IngressoEventoResponse> findByIdEvento(UUID idEvento);

}
