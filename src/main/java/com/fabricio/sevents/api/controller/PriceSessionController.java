package com.fabricio.sevents.api.controller;

import com.fabricio.sevents.api.core.util.constants.ConstantsExternalResources;
import com.fabricio.sevents.api.model.dto.persist.PriceSessionPersist;
import com.fabricio.sevents.api.model.dto.request.PrecoSessaoRequest;
import com.fabricio.sevents.api.model.dto.response.PriceSessionJoinSessionResponse;
import com.fabricio.sevents.api.model.dto.response.PriceSessionResponse;
import com.fabricio.sevents.api.service.PriceSessionService;
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
@RequestMapping(ConstantsExternalResources.RESOURCE_PRECO_SESSAO)
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class PriceSessionController {

    private final PriceSessionService priceService;

    @PostMapping
    public ResponseEntity<PriceSessionResponse> create(@Valid @RequestBody PriceSessionPersist persist){

        log.info("[PRECO-SESSAO-RESOURCE] - request para criar preco sessao");

        return ResponseEntity.ok(priceService.create(persist));

    }

    @GetMapping
    public ResponseEntity<PageSevent<PriceSessionResponse>> get(@Valid @ParameterObject PrecoSessaoRequest priceRequest) {

        log.info("[PRECO-SESSAO-RESOURCE] - request para listar preco sessao");

        return ResponseEntity.ok(priceService.get(priceRequest));

    }

    @GetMapping(path = "/session")
    public ResponseEntity<List<PriceSessionJoinSessionResponse>> listarPorSessao(@RequestParam(value = "idSession", required = true) UUID idSession) {

        log.info("[PRECO-SESSAO-RESOURCE] - request para listar preco-sessao de uma determinada sessão");

        return ResponseEntity.ok(priceService.findBySession(idSession));

    }
}