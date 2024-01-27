package com.fabricio.sevents.api.model.dto.request;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import com.fabricio.sevents.api.util.GenericRequest;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngressoRequest extends GenericRequest {

    private String nome;

    private String descricao;

    private AvailabilityEnum disponibilidade;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;
    
}