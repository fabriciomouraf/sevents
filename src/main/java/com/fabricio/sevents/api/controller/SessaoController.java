package com.fabricio.sevents.api.controller;

import com.fabricio.sevents.api.core.util.constants.ConstantsExternalResources;
import com.fabricio.sevents.api.model.dto.persist.SessaoPersist;
import com.fabricio.sevents.api.model.dto.request.SessaoRequest;
import com.fabricio.sevents.api.model.dto.response.SessaoIngressoResponse;
import com.fabricio.sevents.api.model.dto.response.SessaoResponse;
import com.fabricio.sevents.api.service.SessaoService;
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
@RequestMapping(ConstantsExternalResources.RESOURCE_SESSAO)
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class SessaoController {

    private final SessaoService sessaoService;

    @PostMapping
    public ResponseEntity<SessaoResponse> criar(@Valid @RequestBody SessaoPersist persist){

        log.info("[SESSAO-RESOURCE] - request para criar sessao");

        return ResponseEntity.ok(sessaoService.criar(persist));
    }
    
    @GetMapping
    public ResponseEntity<PageBevent<SessaoResponse>> get(@Valid @ParameterObject SessaoRequest sessaoRequest) {

        log.info("[SESSAO-RESOURCE] - request para listar todas as sessões");

        return ResponseEntity.ok(sessaoService.get(sessaoRequest));
    }


    @GetMapping(path = "/filtrar")
    public ResponseEntity<List<SessaoIngressoResponse>> listarPorIngresso(@RequestParam(value = "codigoIngresso", required = true) UUID ingressoId) {
        log.info("[SESSAO-RESOURCE] - request para listar sessões de um determinado ingresso");
        return ResponseEntity.ok(sessaoService.findByIngresso(ingressoId));
    }
}