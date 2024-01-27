package com.fabricio.sevents.api.model.dto.request;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import com.fabricio.sevents.api.util.GenericRequest;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MacroEventRequest extends GenericRequest {

    private String name;

    private String description;

    private AvailabilityEnum availability;

    private LocalDateTime started_at;

    private LocalDateTime ended_at;
    
}