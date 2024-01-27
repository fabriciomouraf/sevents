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
public class MacroEventUpdate {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private AvailabilityEnum availability;

    @NotNull
    private LocalDateTime startedAt;

    @NotNull
    private LocalDateTime endedAt;

}