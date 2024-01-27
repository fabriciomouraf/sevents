package com.fabricio.sevents.api.model.domain;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import com.fabricio.sevents.api.util.GenericObject;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "price_session")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SQLDelete(sql = "UPDATE price_session SET deleted_at = current_timestamp WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class PriceSession extends GenericObject {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="availability")
    private AvailabilityEnum availability;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="max_quantity")
    private Integer max_quantity;

    @Column(name="started_sale")
    private LocalDateTime startedSale;

    @Column(name="endedSale")
    private LocalDateTime endedSale;

    @Column(name="min_per_purchase")
    private Integer minPerPurchase;

    @Column(name="max_per_purchase")
    private Integer maxPerPurchase;

    @Column(name="id_session")
    private UUID idSession;

}
