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
public class IngressPersist {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private AvailabilityEnum availability;

    @NotNull
    private LocalDateTime started_at;

    @NotNull
    private LocalDateTime ended_at;

    @NotNull
    private String image;

    @NotNull
    private UUID idEvent;

}
