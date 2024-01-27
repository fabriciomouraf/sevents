package com.fabricio.sevents.api.model.dto.persist;

import com.fabricio.sevents.api.model.domain.enumeration.Status;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraPersist {

    @Column(name = "id_usuario")
    private UUID idUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

}