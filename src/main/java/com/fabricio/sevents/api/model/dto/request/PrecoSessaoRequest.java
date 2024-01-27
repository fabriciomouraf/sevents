package com.fabricio.sevents.api.model.dto.request;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import com.fabricio.sevents.api.util.GenericRequest;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrecoSessaoRequest extends GenericRequest {

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private AvailabilityEnum disponibilidade;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;
    
}