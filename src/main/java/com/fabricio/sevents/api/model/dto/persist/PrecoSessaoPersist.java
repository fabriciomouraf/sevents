package com.fabricio.sevents.api.model.dto.persist;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrecoSessaoPersist {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    private AvailabilityEnum disponibilidade;

    @NotNull
    private BigDecimal preco;

    @NotNull
    private Integer quantidadeMaxima;

    @NotNull
    private LocalDateTime inicioVendas;

    @NotNull
    private LocalDateTime fimVendas;

    private Integer minPorCompra;

    private Integer maxPorCompra;

    @NotNull
    private UUID idSessao;

}
