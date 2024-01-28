package com.fabricio.sevents.api.controller;

import com.fabricio.sevents.api.core.util.constants.ConstantsExternalResources;
import com.fabricio.sevents.api.model.dto.persist.EventPersist;
import com.fabricio.sevents.api.model.dto.request.EventRequest;
import com.fabricio.sevents.api.model.dto.response.EventResponse;
import com.fabricio.sevents.api.service.EventService;
import com.fabricio.sevents.api.util.page.PageSevent;

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
@RequestMapping(ConstantsExternalResources.RESOURCE_EVENT)
@AllArgsConstructor
@Slf4j
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponse> create(@Valid @RequestBody EventPersist persist){

        log.info("[EVENTO-RESOURCE] - request para criar evento");

        return ResponseEntity.ok(eventService.create(persist));

    }
    @GetMapping
    public ResponseEntity<PageSevent<EventResponse>> get(@Valid @ParameterObject EventRequest eventoRequest) {

        log.info("[EVENTO-RESOURCE] - request para listar eventos");

        return ResponseEntity.ok(eventService.get(eventoRequest));

    }
}