package com.fabricio.sevents.api.model.dto.request;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import com.fabricio.sevents.api.util.GenericRequest;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrecoSessaoRequest extends GenericRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private AvailabilityEnum availability;

    private LocalDateTime startedSale;

    private LocalDateTime endedSale;
    
}