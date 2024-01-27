package com.fabricio.sevents.api.controller;

import com.fabricio.sevents.api.core.util.constants.ConstantsExternalResources;
import com.fabricio.sevents.api.model.dto.request.EventoAncoraRequest;
import com.fabricio.sevents.api.model.dto.response.EventoAncoraResponse;
import com.fabricio.sevents.api.model.dto.persist.EventoAncoraPersist;
import com.fabricio.sevents.api.model.dto.update.EventoAncoraUpdate;
import com.fabricio.sevents.api.service.EventoAncoraService;
import com.fabricio.sevents.api.util.page.PageBevent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping(ConstantsExternalResources.RESOURCE_EVENTO_ANCORA)
@AllArgsConstructor
@Slf4j
public class EventoAncoraController {

    private final EventoAncoraService eventoAncoraService;

    @GetMapping
    public ResponseEntity<PageBevent<EventoAncoraResponse>> get(@Valid @ParameterObject EventoAncoraRequest eventoAncoraRequest) {

        log.debug("[BONUS] - REST request to get all bonus");

        return ResponseEntity.ok(eventoAncoraService.get(eventoAncoraRequest));

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EventoAncoraResponse> get(@Valid @PathVariable UUID id) {

        log.info("[EVENTO-ANCORA-RESOURCE] - request para pegar um evento ancora");

        return ResponseEntity.ok(eventoAncoraService.getEventoAncora(id));

    }

    @PostMapping
    public ResponseEntity<EventoAncoraResponse> criar(@Valid @RequestBody EventoAncoraPersist persist){

        log.info("[EVENTO-ANCORA-RESOURCE] - request para criar evento ancora");

        return ResponseEntity.ok(eventoAncoraService.criar(persist));

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EventoAncoraResponse> put(@Valid @PathVariable UUID id, @Valid @RequestBody EventoAncoraUpdate update) {

        log.info("[EVENTO-ANCORA-RESOURCE] - request para atualizar evento ancora");

        return ResponseEntity.ok(eventoAncoraService.put(id, update));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Valid @PathVariable UUID id){

        log.info("[EVENTO-ANCORA-RESOURCE] - request para deletar evento ancora");

        eventoAncoraService.deletar(id);

        return ResponseEntity.ok().build();

    }


}