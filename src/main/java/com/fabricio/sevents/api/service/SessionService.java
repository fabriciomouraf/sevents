package com.fabricio.sevents.api.service;

import com.fabricio.sevents.api.model.domain.Session;
import com.fabricio.sevents.api.model.dto.persist.SessaoPersist;
import com.fabricio.sevents.api.model.dto.request.SessaoRequest;
import com.fabricio.sevents.api.model.dto.response.SessionIngressResponse;
import com.fabricio.sevents.api.model.dto.response.SessionResponse;
import com.fabricio.sevents.api.repository.SessionRepository;
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
public class SessionService extends GenericObjectContext {

    private final SessionRepository sessionRepository;

    private final IngressService ingressService;

    public SessionResponse create(SessaoPersist persist){

        Session session = convert(persist, Session.class);

        return convert(sessionRepository.save(session));

    }

    public PageSevent<SessionResponse> get(SessaoRequest request){

        Session session = convert(request, Session.class);

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.STARTING);

        Page<Session> page = sessionRepository.findAll(Example.of(session, exampleMatcher),
                PageableSevent.setPageable(request.getPage(), request.getLimit(), Session.class, request.getSort()));

        checkThrow(Objeto.isBlank(page) || Objeto.isBlank(page.getContent()), GLOBAL_EXPRESSION_SORT_INVALID);
        return convertToPageSevent(page, new TypeToken<List<SessionResponse>>() {}.getType());
    }

    public Session findById(UUID id){

        return sessionRepository.findById(id).orElse(null);

    }

    public List<SessionIngressResponse> findByIngress(UUID id) {

        return sessionRepository.findByIdIngress(id);

    }

    private SessionResponse convert(Session session){

        SessionResponse response = convert(session, SessionResponse.class);

        if(notBlank(session.getIdIngress())){

            response.setIngress(ingressService.findById(session.getIdIngress()));

        }

        return response;
    }

}