package com.fabricio.sevents.api.model.dto.persist;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngressoPersist {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    private AvailabilityEnum disponibilidade;

    @NotNull
    private LocalDateTime dataInicio;

    @NotNull
    private LocalDateTime dataFim;

    @NotNull
    private String imagem;

    @NotNull
    private UUID idEvento;

}
