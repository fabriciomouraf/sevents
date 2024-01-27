package com.fabricio.sevents.api.model.dto.response;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrecoSessaoJoinSessaoResponse {
    private UUID id;
    private String nomePrecoSessao;
    private String descricaoPrecoSessao;
    private BigDecimal preco;
    private AvailabilityEnum disponibilidade;
    private Integer quantidadeMaxima;
    private LocalDateTime inicioVendas;
    private UUID idSessao;
    private String nomeSessao;
    private String descricaoSessao;
}