package com.fabricio.sevents.api.controller;

import com.fabricio.sevents.api.core.util.constants.ConstantsExternalResources;
import com.fabricio.sevents.api.model.dto.persist.EventoPersist;
import com.fabricio.sevents.api.model.dto.request.EventoRequest;
import com.fabricio.sevents.api.model.dto.response.EventoResponse;
import com.fabricio.sevents.api.service.EventoService;
import com.fabricio.sevents.api.util.page.PageBevent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(ConstantsExternalResources.RESOURCE_EVENTO)
@AllArgsConstructor
@Slf4j
public class EventoController {

    private final EventoService eventoService;

    @PostMapping
    public ResponseEntity<EventoResponse> criar(@Valid @RequestBody EventoPersist persist){

        log.info("[EVENTO-RESOURCE] - request para criar evento");

        return ResponseEntity.ok(eventoService.criar(persist));

    }
    @GetMapping
    public ResponseEntity<PageBevent<EventoResponse>> get(@Valid @ParameterObject EventoRequest eventoRequest) {
        log.info("[EVENTO-RESOURCE] - request para listar eventos");

        return ResponseEntity.ok(eventoService.get(eventoRequest));
    }
}