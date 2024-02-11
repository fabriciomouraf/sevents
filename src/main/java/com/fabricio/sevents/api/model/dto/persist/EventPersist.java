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
public class EventPersist {

    @NotBlank
    @Column(name="name")
    private String name;

    @NotBlank
    @Column(name="description")
    private String description;

    @NotNull
    private AvailabilityEnum availability;

    @NotNull
    @Column(name="started_at")
    private LocalDateTime startedAt;

    @NotNull
    @Column(name="ended_at")
    private LocalDateTime endedAt;

    @NotNull
    @Column(name="id_macro_event")
    private UUID idMacroEvent;

}
