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
@Table(name = "ingresso")
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

    @Column(name="nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name="disponibilidade")
    private AvailabilityEnum disponibilidade;

    @Column(name="data_inicio")
    private LocalDateTime dataInicio;

    @Column(name="data_fim")
    private LocalDateTime dataFim;

    @Column(name="imagem")
    private String imagem;

    @Column(name="id_evento")
    private UUID idEvento;
}