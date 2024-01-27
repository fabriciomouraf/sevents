package com.fabricio.sevents.api.model.dto.response;

import com.fabricio.sevents.api.model.domain.Session;
import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import com.fabricio.sevents.api.util.GenericResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrecoSessaoResponse extends GenericResponse {

    private String nome;

    private String descricao;

    private AvailabilityEnum disponibilidade;

    private BigDecimal preco;

    private Integer quantidadeMaxima;

    private LocalDateTime inicioVendas;

    private LocalDateTime fimVendas;

    private Integer minPorCompra;

    private Integer maxPorCompra;

    private Session sessao;
}