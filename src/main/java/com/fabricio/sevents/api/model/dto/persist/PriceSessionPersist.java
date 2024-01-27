package com.fabricio.sevents.api.model.dto.persist;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceSessionPersist {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private AvailabilityEnum availability;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer max_quantity;

    @NotNull
    private LocalDateTime startedSale;

    @NotNull
    private LocalDateTime endedSale;

    private Integer minPerPurchase;

    private Integer maxPerPurchase;

    @NotNull
    private UUID idSession;

}
