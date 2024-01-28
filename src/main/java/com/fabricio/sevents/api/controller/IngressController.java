package com.fabricio.sevents.api.controller;

import com.fabricio.sevents.api.core.util.constants.ConstantsExternalResources;
import com.fabricio.sevents.api.model.dto.persist.IngressPersist;
import com.fabricio.sevents.api.model.dto.request.IngressRequest;
import com.fabricio.sevents.api.model.dto.response.IngressEventResponse;
import com.fabricio.sevents.api.model.dto.response.IngressResponse;
import com.fabricio.sevents.api.service.IngressService;
import com.fabricio.sevents.api.util.page.PageSevent;

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
@RequestMapping(ConstantsExternalResources.RESOURCE_INGRESS)
@AllArgsConstructor
@Slf4j
public class IngressController {

    private final IngressService ingressService;

    @PostMapping
    public ResponseEntity<IngressResponse> create(@Valid @RequestBody IngressPersist persist){

        log.info("[INGRESSO-RESOURCE] - request para criar ingresso");

        return ResponseEntity.ok(ingressService.create(persist));

    }

    @GetMapping
    public ResponseEntity<PageSevent<IngressResponse>> get(@Valid @ParameterObject IngressRequest ingressoRequest) {

        log.info("[INGRESSO-RESOURCE] - request para listar ingressos");

        return ResponseEntity.ok(ingressService.get(ingressoRequest));
    }

    @GetMapping(path = "/event")
    public ResponseEntity<List<IngressEventResponse>> listByEventId(@RequestParam(value = "idEvent", required = true) UUID idEvent) {

        log.info("[INGRESSO-RESOURCE] - request para listar ingressos de um determinado evento");

        return ResponseEntity.ok(ingressService.findByEvent(idEvent));
    }
}