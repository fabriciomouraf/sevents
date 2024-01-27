package com.fabricio.sevents.api.service;

import com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum;

import com.fabricio.sevents.api.model.domain.MacroEvent;
import com.fabricio.sevents.api.model.dto.request.MacroEventRequest;
import com.fabricio.sevents.api.model.dto.response.MacroEventResponse;
import com.fabricio.sevents.api.model.dto.persist.MacroEventPersist;
import com.fabricio.sevents.api.model.dto.update.MacroEventUpdate;
import com.fabricio.sevents.api.repository.MacroEventRepository;
import com.fabricio.sevents.api.util.GenericObjectContext;
import com.fabricio.sevents.api.util.object.Objeto;
import com.fabricio.sevents.api.util.page.PageSevent;
import com.fabricio.sevents.api.util.page.PageableSevent;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;

import java.util.List;
import java.util.UUID;

import static com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum.*;

@Service
@AllArgsConstructor
public class MacroEventService extends GenericObjectContext {

    private final MacroEventRepository macroEventRepository;

    public PageSevent<MacroEventResponse> get(MacroEventRequest request){

        MacroEvent event = convert(request, MacroEvent.class);

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.STARTING);

        Page<MacroEvent> page = macroEventRepository.findAll(Example.of(event, exampleMatcher),
                PageableSevent.setPageable(request.getPage(), request.getLimit(), MacroEvent.class, request.getSort()));

        checkThrow(Objeto.isBlank(page) || Objeto.isBlank(page.getContent()), MACRO_EVENT_NO_CONTENT);

        return convertToPageSevent(page, new TypeToken<List<MacroEventResponse>>() {}.getType());

    }

    public MacroEventResponse getMacroEvent(UUID macroEventId){

        MacroEvent event = get(macroEventId);

        return convert(event, MacroEventResponse.class);

    }

    public MacroEventResponse create(MacroEventPersist persist){

        MacroEvent event = super.convert(persist, MacroEvent.class);

        event = macroEventRepository.save(event);

        return convert(event, MacroEventResponse.class);

    }


    public MacroEventResponse put(UUID id, MacroEventUpdate update) {

        MacroEvent event = get(id);

        BeanUtils.copyProperties(update, event);

        event = macroEventRepository.save(event);

        return convert(event, MacroEventResponse.class);

    }


    private MacroEvent get(UUID id) {

        return macroEventRepository.findById(id).orElseThrow(MACRO_EVENT_NOT_FOUNT::getEx);

    }

    public MacroEvent findById(UUID id) {

        return macroEventRepository.findById(id).orElse(null);

    }

    public void delete(UUID id){

        macroEventRepository.delete(get(id));

    }

}