package com.fabricio.sevents.api.service;

import com.fabricio.sevents.api.model.domain.Session;
import com.fabricio.sevents.api.model.dto.persist.SessaoPersist;
import com.fabricio.sevents.api.model.dto.request.SessaoRequest;
import com.fabricio.sevents.api.model.dto.response.SessaoIngressoResponse;
import com.fabricio.sevents.api.model.dto.response.SessaoResponse;
import com.fabricio.sevents.api.repository.SessaoRepository;
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
public class SessaoService extends GenericObjectContext {

    private final SessaoRepository sessaoRepository;

    private final IngressoService ingressoService;

    public SessaoResponse criar(SessaoPersist persist){

        Session sessao = convert(persist, Session.class);

        return convert(sessaoRepository.save(sessao));

    }

    public PageBevent<SessaoResponse> get(SessaoRequest request){
        Session sessao = convert(request, Session.class);
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.STARTING);

        Page<Session> page = sessaoRepository
            .findAll(
                Example.of(sessao, exampleMatcher), 
                PageableSevents.setPageable(request.getPage(), request.getLimit(), Session.class, request.getSort())
            );

        checkThrow(Objeto.isBlank(page) || Objeto.isBlank(page.getContent()), GLOBAL_EXPRESSAO_SORT_INVALIDO);
        return convertToPageBevent(page, new TypeToken<List<SessaoResponse>>() {}.getType());
    }

    public Session findById(UUID id){
        return sessaoRepository.findById(id).orElse(null);
    }

    public List<SessaoIngressoResponse> findByIngresso(UUID id) {
        return sessaoRepository.findByIdIngresso(id);
    }

    private SessaoResponse convert(Session sessao){

        SessaoResponse response = convert(sessao, SessaoResponse.class);

        if(notBlank(sessao.getIdIngresso())){

            response.setIngresso(ingressoService.findById(sessao.getIdIngresso()));

        }

        return response;
    }

}