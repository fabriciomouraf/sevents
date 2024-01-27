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
public class SessaoIngressoResponse {
    private UUID id;
    private String nomeSessao;
    private String descricaoSessao;
    private AvailabilityEnum disponibilidade;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private UUID idIngresso;
    private String nomeIngresso;
    private String descricaoIngresso;
}