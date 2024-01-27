package com.fabricio.sevents.api.service;

import com.fabricio.sevents.api.model.domain.enumeration.Status;
import com.fabricio.sevents.api.model.dto.persist.CompraPersist;
import com.fabricio.sevents.api.repository.CompraRepository;
import com.fabricio.sevents.api.util.GenericObjectContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CompraService extends GenericObjectContext {

    private final CompraRepository compraRepository;

    public void iniciar(){

        var compra = CompraPersist.builder()
                .idUsuario(UUID.randomUUID()) //TODO: não existe login ainda
                .status(Status.ABERTA)
                .build();

//        return convert(compraRepository.save(convert(compra, Compra.class), x));

    }

}