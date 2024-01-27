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
@Table(name = "event")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SQLDelete(sql = "UPDATE event SET deleted_at = current_timestamp WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class Event extends GenericObject {

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

    @Column(name="started_at")
    private LocalDateTime startedAt;

    @Column(name="ended_at")
    private LocalDateTime endedAt;

    @Column(name="id_macro_event")
    private UUID idMacroEvent;

}