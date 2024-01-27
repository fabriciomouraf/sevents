package com.fabricio.sevents.api.service;

import com.fabricio.sevents.api.model.domain.Ingress;
import com.fabricio.sevents.api.model.dto.persist.IngressoPersist;
import com.fabricio.sevents.api.model.dto.request.IngressoRequest;
import com.fabricio.sevents.api.model.dto.response.IngressoEventoResponse;
import com.fabricio.sevents.api.model.dto.response.IngressoResponse;
import com.fabricio.sevents.api.repository.IngressoRepository;
import com.fabricio.sevents.api.util.GenericObjectContext;
import com.fabricio.sevents.api.util.object.Objeto;
import com.fabricio.sevents.api.util.page.PageBevent;
import com.fabricio.sevents.api.util.page.PageableSevents;

import lombok.AllArgsConstructor;

import org.modelmapper.TypeToken;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum.GLOBAL_EXPRESSAO_SORT_INVALIDO;
import static com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum.checkThrow;
import static com.fabricio.sevents.api.util.object.Objeto.notBlank;

@Service
@AllArgsConstructor
public class IngressoService extends GenericObjectContext {

    private final IngressoRepository ingressoRepository;

    private final EventoService eventoService;

    public IngressoResponse criar(IngressoPersist persist){

        Ingress ingresso = convert(persist, Ingress.class);

        return convert(ingressoRepository.save(ingresso));

    }

    public Ingress findById(UUID id){

        return ingressoRepository.findById(id).orElse(null);

    }

    public PageBevent<IngressoResponse> get(IngressoRequest request){

        Ingress ingresso = convert(request, Ingress.class);

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.STARTING);

        Page<Ingress> page = ingressoRepository.findAll(Example.of(ingresso, exampleMatcher),
                PageableSevents.setPageable(request.getPage(), request.getLimit(), Ingress.class, request.getSort()));

        checkThrow(Objeto.isBlank(page) || Objeto.isBlank(page.getContent()), GLOBAL_EXPRESSAO_SORT_INVALIDO);
        return convertToPageBevent(page, new TypeToken<List<IngressoResponse>>() {}.getType());
    }

    public List<IngressoEventoResponse> findByEvento(UUID id) {

        return ingressoRepository.findByIdEvento(id);

    }

    private IngressoResponse convert(Ingress ingresso){

        IngressoResponse response = convert(ingresso, IngressoResponse.class);

        if(notBlank(ingresso.getIdEvento())){

            response.setEvento(eventoService.findById(ingresso.getIdEvento()));

        }

        return response;
    }

}
