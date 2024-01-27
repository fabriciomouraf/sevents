package com.fabricio.sevents.api.model.domain;

import com.fabricio.sevents.api.model.domain.enumeration.Status;
import com.fabricio.sevents.api.util.GenericObject;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SQLDelete(sql = "UPDATE purchase SET deleted_at = current_timestamp WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class Purchase extends GenericObject {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "cart_code")
    private String cartCode;

    @Column(name = "id_user")
    private UUID idUser;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

    @Column(name = "expires_in")
    private LocalDateTime expires_in;

    @PrePersist
    public void prePersist(){

        setExpires_in(LocalDateTime.now().plusMinutes(30));

    }
}
