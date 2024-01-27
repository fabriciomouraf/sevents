package com.fabricio.sevents.api.service;

import com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum;

import static com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum.EVENTO_ANCORA_NO_CONTENT;
import static com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum.checkThrow;
import com.fabricio.sevents.api.model.domain.MacroEvent;
import com.fabricio.sevents.api.model.dto.request.EventoAncoraRequest;
import com.fabricio.sevents.api.model.dto.response.EventoAncoraResponse;
import com.fabricio.sevents.api.model.dto.persist.EventoAncoraPersist;
import com.fabricio.sevents.api.model.dto.update.EventoAncoraUpdate;
import com.fabricio.sevents.api.repository.EventoAncoraRepository;
import com.fabricio.sevents.api.util.GenericObjectContext;
import com.fabricio.sevents.api.util.object.Objeto;
import com.fabricio.sevents.api.util.page.PageBevent;
import com.fabricio.sevents.api.util.page.PageableSevents;
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

@Service
@AllArgsConstructor
public class EventoAncoraService extends GenericObjectContext {

    private final EventoAncoraRepository eventoAncoraRepository;

    public PageBevent<EventoAncoraResponse> get(EventoAncoraRequest request){

        MacroEvent eventoAncora = convert(request, MacroEvent.class);

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.STARTING);

        Page<MacroEvent> page = eventoAncoraRepository.findAll(Example.of(eventoAncora, exampleMatcher),
                PageableSevents.setPageable(request.getPage(), request.getLimit(), MacroEvent.class, request.getSort()));

        checkThrow(Objeto.isBlank(page) || Objeto.isBlank(page.getContent()), EVENTO_ANCORA_NO_CONTENT);

        return convertToPageBevent(page, new TypeToken<List<EventoAncoraResponse>>() {}.getType());

    }

    public EventoAncoraResponse getEventoAncora(UUID eventoAncoraId){

        MacroEvent eventoAncora = get(eventoAncoraId);

        return convert(eventoAncora, EventoAncoraResponse.class);

    }

    public EventoAncoraResponse criar(EventoAncoraPersist persist){

        MacroEvent evento = super.convert(persist, MacroEvent.class);

        evento = eventoAncoraRepository.save(evento);

        return convert(evento, EventoAncoraResponse.class);

    }


    public EventoAncoraResponse put(UUID id, EventoAncoraUpdate update) {

        MacroEvent eventoAncora = get(id);

        BeanUtils.copyProperties(update, eventoAncora);

        eventoAncora = eventoAncoraRepository.save(eventoAncora);

        return convert(eventoAncora, EventoAncoraResponse.class);

    }


    private MacroEvent get(UUID id) {

        return eventoAncoraRepository.findById(id).orElseThrow(ExceptionEnum.EVENTO_ANCORA_NOT_FOUNT::getEx);

    }

    public MacroEvent findById(UUID id) {

        return eventoAncoraRepository.findById(id).orElse(null);

    }

    public void deletar(UUID id){

        eventoAncoraRepository.delete(get(id));

    }

}