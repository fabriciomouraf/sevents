package com.fabricio.sevents.api.model.dto.response;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IngressoEventoResponse {
    private UUID id;
    private String nomeIngresso;
    private String descricaoIngresso;
    private AvailabilityEnum disponibilidade;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String imagem;
    private UUID idEvento;
    private String nomeEvento;
    private String descricaoEvento;
}