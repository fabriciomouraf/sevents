package com.fabricio.sevents.api.service;

import com.fabricio.sevents.api.model.domain.PriceSession;
import com.fabricio.sevents.api.model.dto.persist.PriceSessionPersist;
import com.fabricio.sevents.api.model.dto.request.PrecoSessaoRequest;
import com.fabricio.sevents.api.model.dto.response.PriceSessionJoinSessionResponse;
import com.fabricio.sevents.api.model.dto.response.PriceSessionResponse;
import com.fabricio.sevents.api.repository.PriceSessionRepository;
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

import static com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum.*;
import static com.fabricio.sevents.api.util.object.Objeto.notBlank;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PriceSessionService extends GenericObjectContext {

    private final PriceSessionRepository priceRepository;

    private final SessionService sessionService;

    public PriceSessionResponse create(PriceSessionPersist persist){

        PriceSession price = convert(persist, PriceSession.class);

        return convert(priceRepository.save(price));

    }
    public PageSevent<PriceSessionResponse> get(PrecoSessaoRequest request){

        PriceSession price = convert(request, PriceSession.class);

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.STARTING);

        Page<PriceSession> page = priceRepository.findAll(Example.of(price, exampleMatcher),
                PageableSevent.setPageable(request.getPage(), request.getLimit(), PriceSession.class, request.getSort()));

        checkThrow(Objeto.isBlank(page) || Objeto.isBlank(page.getContent()), GLOBAL_EXPRESSION_SORT_INVALID);
        return convertToPageSevent(page, new TypeToken<List<PriceSessionResponse>>() {}.getType());
    }
    
    public List<PriceSessionJoinSessionResponse> findBySession(UUID id) {

        return priceRepository.findByIdSessao(id);

    }

    private PriceSessionResponse convert(PriceSession price){

        PriceSessionResponse response = convert(price, PriceSessionResponse.class);

        if(notBlank(price.getIdSession())){

            response.setSession(sessionService.findById(price.getIdSession()));

        }

        return response;
    }

}