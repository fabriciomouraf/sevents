package com.fabricio.sevents.api.model.dto.persist;

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
public class MacroEventPersist {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private LocalDateTime start_at;

    @NotNull
    private LocalDateTime ended_at;

    @NotNull
    private AvailabilityEnum availability;

}