package com.fabricio.sevents.api.model.dto.response;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IngressEventResponse {
    private UUID id;
    private String nameIngress;
    private String descriptionIngress;
    private AvailabilityEnum availability;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String image;
    private UUID idEvent;
    private String nameEvent;
    private String descriptionEvent;
}