package com.fabricio.sevents.api.model.dto.response;

import com.fabricio.sevents.api.model.domain.Event;
import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import com.fabricio.sevents.api.util.GenericResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IngressResponse extends GenericResponse {

    private String name;

    private String description;

    private AvailabilityEnum availability;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private String image;

    private Event event;
}