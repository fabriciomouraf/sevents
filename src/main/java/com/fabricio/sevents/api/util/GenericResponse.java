package com.fabricio.sevents.api.util;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class GenericResponse implements Serializable {

     private static final long serialVersionUID = -2643069682814918036L;

     private UUID id;

     private LocalDateTime criadoEm;

     private LocalDateTime atualizadoEm;

}