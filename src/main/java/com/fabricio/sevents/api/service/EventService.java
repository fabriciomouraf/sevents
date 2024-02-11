package com.fabricio.sevents.api.service;

import com.fabricio.sevents.api.model.domain.Event;
import com.fabricio.sevents.api.model.dto.persist.EventPersist;
import com.fabricio.sevents.api.model.dto.request.EventRequest;
import com.fabricio.sevents.api.model.dto.response.EventResponse;
import com.fabricio.sevents.api.core.redis.repository.EventRedisRepository;
import com.fabricio.sevents.api.repository.EventRepository;
import com.fabricio.sevents.api.util.GenericObjectContext;
import com.fabricio.sevents.api.util.object.Objeto;
import com.fabricio.sevents.api.util.page.PageSevent;
import com.fabricio.sevents.api.util.page.PageableSevent;

import static com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum.*;
import static com.fabricio.sevents.api.util.object.Objeto.notBlank;
import lombok.AllArgsConstructor;

import org.modelmapper.TypeToken;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EventService extends GenericObjectContext {

    private final EventRepository eventRepository;

    private final EventRedisRepository eventRedisRepository;

    private final MacroEventService macroEventService;

    public EventResponse create(EventPersist persist){

        Event event = super.convert(persist, Event.class);

        event = eventRepository.save(event);

        eventRedisRepository.save(event);

        return convert(event);

    }

    public PageSevent<EventResponse> get(EventRequest request){

        Event event = convert(request, Event.class);

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.STARTING);

        Page<Event> page = eventRepository.findAll(Example.of(event, exampleMatcher),
                PageableSevent.setPageable(request.getPage(), request.getLimit(), Event.class, request.getSort()));

        checkThrow(Objeto.isBlank(page) || Objeto.isBlank(page.getContent()), GLOBAL_EXPRESSION_SORT_INVALID);
        return convertToPageSevent(page, new TypeToken<List<EventResponse>>() {}.getType());
    }

    public Event findById(UUID id){

        return eventRepository.findById(id).orElse(null);

    }

    private EventResponse convert(Event event){

        EventResponse response = convert(event, EventResponse.class);

        if(notBlank(event.getIdMacroEvent())){

            response.setMacroEvent(macroEventService.findById(event.getIdMacroEvent()));

        }

        return response;
    }

}
