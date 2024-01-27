package com.fabricio.sevents.api.controller;

import com.fabricio.sevents.api.core.util.constants.ConstantsExternalResources;
import com.fabricio.sevents.api.model.dto.persist.IngressoPersist;
import com.fabricio.sevents.api.model.dto.request.IngressoRequest;
import com.fabricio.sevents.api.model.dto.response.IngressoEventoResponse;
import com.fabricio.sevents.api.model.dto.response.IngressoResponse;
import com.fabricio.sevents.api.service.IngressoService;
import com.fabricio.sevents.api.util.page.PageBevent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

@RestController
@RequestMapping(ConstantsExternalResources.RESOURCE_INGRESSO)
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class IngressoController {

    private final IngressoService ingressoService;

    @PostMapping
    public ResponseEntity<IngressoResponse> criar(@Valid @RequestBody IngressoPersist persist){

        log.info("[INGRESSO-RESOURCE] - request para criar ingresso");

        return ResponseEntity.ok(ingressoService.criar(persist));

    }

    @GetMapping
    public ResponseEntity<PageBevent<IngressoResponse>> get(@Valid @ParameterObject IngressoRequest ingressoRequest) {

        log.info("[INGRESSO-RESOURCE] - request para listar ingressos");

        return ResponseEntity.ok(ingressoService.get(ingressoRequest));
    }

    @GetMapping(path = "/filtrar")
    public ResponseEntity<List<IngressoEventoResponse>> listarPorEvento(@RequestParam(value = "codigoEvento", required = true) UUID eventoId) {

        log.info("[INGRESSO-RESOURCE] - request para listar ingressos de um determinado evento");

        return ResponseEntity.ok(ingressoService.findByEvento(eventoId));
    }
}