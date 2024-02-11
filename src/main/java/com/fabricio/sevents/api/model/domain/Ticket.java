package com.fabricio.sevents.api.model.domain;

import com.fabricio.sevents.api.model.domain.enumeration.Status;
import com.fabricio.sevents.api.util.GenericObject;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SQLDelete(sql = "UPDATE entry SET deleted_at = current_timestamp WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class Ticket extends GenericObject {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private UUID id;

    @Embedded
    private Person person;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

    @Column(name = "id_ingress")
    private UUID idIngress;

    @Column(name = "id_session")
    private UUID idSession;

    @Column(name = "id_price")
    private UUID idPrice;

    @Column(name = "id_purchase")
    private UUID idPurchase;

}