package com.fabricio.sevents.api.controller;

import com.fabricio.sevents.api.core.util.constants.ConstantsExternalResources;
import com.fabricio.sevents.api.model.dto.persist.MacroEventPersist;
import com.fabricio.sevents.api.model.dto.request.MacroEventRequest;
import com.fabricio.sevents.api.model.dto.response.MacroEventResponse;
import com.fabricio.sevents.api.model.dto.update.MacroEventUpdate;
import com.fabricio.sevents.api.service.MacroEventService;
import com.fabricio.sevents.api.util.page.PageSevent;
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
public class MacroEventController {

    private final MacroEventService macroEventService;

    @GetMapping
    public ResponseEntity<PageSevent<MacroEventResponse>> get(@Valid @ParameterObject MacroEventRequest macroEventRequest) {

        log.debug("[BONUS] - REST request to get all bonus");

        return ResponseEntity.ok(macroEventService.get(macroEventRequest));

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MacroEventResponse> get(@Valid @PathVariable UUID id) {

        log.info("[EVENTO-ANCORA-RESOURCE] - request para pegar um evento ancora");

        return ResponseEntity.ok(macroEventService.getMacroEvent(id));

    }

    @PostMapping
    public ResponseEntity<MacroEventResponse> create(@Valid @RequestBody MacroEventPersist persist){

        log.info("[EVENTO-ANCORA-RESOURCE] - request para criar evento ancora");

        return ResponseEntity.ok(macroEventService.create(persist));

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<MacroEventResponse> put(@Valid @PathVariable UUID id, @Valid @RequestBody MacroEventUpdate update) {

        log.info("[EVENTO-ANCORA-RESOURCE] - request para atualizar evento ancora");

        return ResponseEntity.ok(macroEventService.put(id, update));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable UUID id){

        log.info("[EVENTO-ANCORA-RESOURCE] - request para deletar evento ancora");

        macroEventService.delete(id);

        return ResponseEntity.ok().build();

    }


}