package com.fabricio.sevents.api.model.dto.response;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import com.fabricio.sevents.api.util.GenericResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventoAncoraResponse extends GenericResponse {

    private String nome;

    private String descricao;

    private AvailabilityEnum disponibilidade;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;
}