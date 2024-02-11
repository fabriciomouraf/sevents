package com.fabricio.sevents.api.model.domain;

import com.fabricio.sevents.api.model.domain.enumeration.AvailabilityEnum;
import com.fabricio.sevents.api.util.GenericObject;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ingress")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SQLDelete(sql = "UPDATE ingresso SET deletado_em = current_timestamp WHERE id=?")
@Where(clause = "deletado_em IS NULL")
public class Ingress extends GenericObject {

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

    @Column(name="startedAt")
    private LocalDateTime startedAt;

    @Column(name="endedAt")
    private LocalDateTime endedAt;

    @Column(name="image")
    private String image;

    @Column(name="id_event")
    private UUID idEvent;
}