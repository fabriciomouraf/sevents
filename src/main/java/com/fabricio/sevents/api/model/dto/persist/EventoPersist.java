package com.fabricio.sevents.api.model.dto.persist;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoPersist {

    @NotBlank
    @Column(name="nome")
    private String nome;

    @NotBlank
    @Column(name="descricao")
    private String descricao;

    @NotNull
    private AvailabilityEnum disponibilidade;

    @NotNull
    @Column(name="data_inicio")
    private LocalDateTime dataInicio;

    @NotNull
    @Column(name="data_fim")
    private LocalDateTime dataFim;

    @NotNull
    @Column(name="id_evento_ancora")
    private UUID idEventoAncora;

}
