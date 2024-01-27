package com.fabricio.sevents.api.model.dto.response;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceSessionJoinSessionResponse {
    private UUID id;
    private String namePriceSession;
    private String descriptionPriceSession;
    private BigDecimal price;
    private AvailabilityEnum availability;
    private Integer maxQuantity;
    private LocalDateTime startedSale;
    private LocalDateTime endedSale;
    private UUID idSession;
    private String nameSession;
    private String descriptionSession;
}