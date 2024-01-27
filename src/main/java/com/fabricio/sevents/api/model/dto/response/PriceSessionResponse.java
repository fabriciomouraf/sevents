package com.fabricio.sevents.api.model.dto.response;

import com.fabricio.sevents.api.model.domain.Session;
import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import com.fabricio.sevents.api.util.GenericResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceSessionResponse extends GenericResponse {

    private String name;

    private String description;

    private AvailabilityEnum availability;

    private BigDecimal price;

    private Integer maxQuantity;

    private LocalDateTime startedSale;

    private LocalDateTime endedSale;

    private Integer minPerPurchase;

    private Integer maxPerPurchase;

    private Session session;
}