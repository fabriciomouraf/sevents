package com.fabricio.sevents.api.service;

import com.fabricio.sevents.api.model.domain.Ingress;
import com.fabricio.sevents.api.model.dto.persist.IngressPersist;
import com.fabricio.sevents.api.model.dto.request.IngressRequest;
import com.fabricio.sevents.api.model.dto.response.IngressEventResponse;
import com.fabricio.sevents.api.model.dto.response.IngressResponse;
import com.fabricio.sevents.api.repository.IngressRepository;
import com.fabricio.sevents.api.util.GenericObjectContext;
import com.fabricio.sevents.api.util.object.Objeto;
import com.fabricio.sevents.api.util.page.PageSevent;
import com.fabricio.sevents.api.util.page.PageableSevent;

import lombok.AllArgsConstructor;

import org.modelmapper.TypeToken;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum.*;
import static com.fabricio.sevents.api.util.object.Objeto.notBlank;

@Service
@AllArgsConstructor
public class IngressService extends GenericObjectContext {

    private final IngressRepository ingressRepository;

    private final EventService eventService;

    public IngressResponse create(IngressPersist persist){

        Ingress ingress = convert(persist, Ingress.class);

        return convert(ingressRepository.save(ingress));

    }

    public Ingress findById(UUID id){

        return ingressRepository.findById(id).orElse(null);

    }

    public PageSevent<IngressResponse> get(IngressRequest request){

        Ingress ingress = convert(request, Ingress.class);

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.STARTING);

        Page<Ingress> page = ingressRepository.findAll(Example.of(ingress, exampleMatcher),
                PageableSevent.setPageable(request.getPage(), request.getLimit(), Ingress.class, request.getSort()));

        checkThrow(Objeto.isBlank(page) || Objeto.isBlank(page.getContent()), GLOBAL_EXPRESSION_SORT_INVALID);
        return convertToPageSevent(page, new TypeToken<List<IngressResponse>>() {}.getType());
    }

    public List<IngressEventResponse> findByEvent(UUID id) {

        return ingressRepository.findByIdEvento(id);

    }

    private IngressResponse convert(Ingress ingress){

        IngressResponse response = convert(ingress, IngressResponse.class);

        if(notBlank(ingress.getIdEvent())){

            response.setEvent(eventService.findById(ingress.getIdEvent()));

        }

        return response;
    }

}
