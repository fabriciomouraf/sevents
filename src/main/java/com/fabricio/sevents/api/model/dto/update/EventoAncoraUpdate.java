package com.fabricio.sevents.api.model.dto.update;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoAncoraUpdate {

    @NotBlank
    private String nome;

    private String descricao;

    @NotNull
    private AvailabilityEnum disponibilidade;

    @NotNull
    private LocalDateTime dataInicio;

    @NotNull
    private LocalDateTime dataFim;

}