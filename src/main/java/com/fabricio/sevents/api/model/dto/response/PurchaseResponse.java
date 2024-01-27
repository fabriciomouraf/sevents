package com.fabricio.sevents.api.model.dto.response;

import com.fabricio.sevents.api.model.domain.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseResponse {

    private UUID id;

    private String cartCode;

    private UUID idUser;

    private Status status;

    private LocalDateTime expiresIn;
}