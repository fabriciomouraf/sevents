package com.fabricio.sevents.api.service;

import com.fabricio.sevents.api.model.domain.Event;
import com.fabricio.sevents.api.model.dto.persist.EventoPersist;
import com.fabricio.sevents.api.model.dto.request.EventoRequest;
import com.fabricio.sevents.api.model.dto.response.EventoResponse;
import com.fabricio.sevents.api.repository.EventoRepository;
import com.fabricio.sevents.api.util.GenericObjectContext;
import com.fabricio.sevents.api.util.object.Objeto;
import com.fabricio.sevents.api.util.page.PageBevent;
import com.fabricio.sevents.api.util.page.PageableSevents;

import static com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum.GLOBAL_EXPRESSAO_SORT_INVALIDO;
import static com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum.checkThrow;
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
public class EventoService extends GenericObjectContext {

    private final EventoRepository eventoRepository;

    private final EventoAncoraService eventoAncoraService;

    public EventoResponse criar(EventoPersist persist){

        Event evento = super.convert(persist, Event.class);

        return convert(eventoRepository.save(evento));

    }

    public PageBevent<EventoResponse> get(EventoRequest request){
        Event eventoAncora = convert(request, Event.class);
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.STARTING);

        Page<Event> page = eventoRepository.findAll(Example.of(eventoAncora, exampleMatcher),
                PageableSevents.setPageable(request.getPage(), request.getLimit(), Event.class, request.getSort()));

        checkThrow(Objeto.isBlank(page) || Objeto.isBlank(page.getContent()), GLOBAL_EXPRESSAO_SORT_INVALIDO);
        return convertToPageBevent(page, new TypeToken<List<EventoResponse>>() {}.getType());
    }

    public Event findById(UUID id){

        return eventoRepository.findById(id).orElse(null);

    }

    private EventoResponse convert(Event evento){

        EventoResponse response = convert(evento, EventoResponse.class);

        if(notBlank(evento.getIdEventoAncora())){

            response.setEventoAncora(eventoAncoraService.findById(evento.getIdEventoAncora()));

        }

        return response;
    }

}
