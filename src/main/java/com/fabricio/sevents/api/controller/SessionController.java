package com.fabricio.sevents.api.controller;

import com.fabricio.sevents.api.core.util.constants.ConstantsExternalResources;
import com.fabricio.sevents.api.model.dto.persist.SessaoPersist;
import com.fabricio.sevents.api.model.dto.request.SessaoRequest;
import com.fabricio.sevents.api.model.dto.response.SessionIngressResponse;
import com.fabricio.sevents.api.model.dto.response.SessionResponse;
import com.fabricio.sevents.api.service.SessionService;
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
@RequestMapping(ConstantsExternalResources.RESOURCE_SESSAO)
@AllArgsConstructor
@Slf4j
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionResponse> create(@Valid @RequestBody SessaoPersist persist){

        log.info("[SESSAO-RESOURCE] - request para criar sessao");

        return ResponseEntity.ok(sessionService.create(persist));
    }
    
    @GetMapping
    public ResponseEntity<PageSevent<SessionResponse>> get(@Valid @ParameterObject SessaoRequest request) {

        log.info("[SESSAO-RESOURCE] - request para listar todas as sessões");

        return ResponseEntity.ok(sessionService.get(request));
    }


    @GetMapping(path = "/ingress")
    public ResponseEntity<List<SessionIngressResponse>> listByIngressId(@RequestParam(value = "idIngress", required = true) UUID idIngress) {

        log.info("[SESSAO-RESOURCE] - request para listar sessões de um determinado ingresso");

        return ResponseEntity.ok(sessionService.findByIngress(idIngress));

    }
}