package com.fabricio.sevents.api.controller;

import com.fabricio.sevents.api.core.util.constants.ConstantsExternalResources;
import com.fabricio.sevents.api.model.dto.persist.PrecoSessaoPersist;
import com.fabricio.sevents.api.model.dto.request.PrecoSessaoRequest;
import com.fabricio.sevents.api.model.dto.response.PrecoSessaoJoinSessaoResponse;
import com.fabricio.sevents.api.model.dto.response.PrecoSessaoResponse;
import com.fabricio.sevents.api.service.PrecoSessaoService;
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
@RequestMapping(ConstantsExternalResources.RESOURCE_PRECO_SESSAO)
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class PrecoSessaoController {

    private final PrecoSessaoService precoService;

    @PostMapping
    public ResponseEntity<PrecoSessaoResponse> criar(@Valid @RequestBody PrecoSessaoPersist persist){
        log.info("[PRECO-SESSAO-RESOURCE] - request para criar preco sessao");
        return ResponseEntity.ok(precoService.criar(persist));
    }

    @GetMapping
    public ResponseEntity<PageBevent<PrecoSessaoResponse>> get(@Valid @ParameterObject PrecoSessaoRequest precoSessaoRequest) {
        log.info("[PRECO-SESSAO-RESOURCE] - request para listar preco sessao");
        return ResponseEntity.ok(precoService.get(precoSessaoRequest));
    }

    @GetMapping(path = "/filtrar")
    public ResponseEntity<List<PrecoSessaoJoinSessaoResponse>> listarPorSessao(@RequestParam(value = "codigoSessao", required = true) UUID sessaoId) {

        log.info("[PRECO-SESSAO-RESOURCE] - request para listar preco-sessao de uma determinada sessão");

        return ResponseEntity.ok(precoService.findBySessao(sessaoId));

    }
}