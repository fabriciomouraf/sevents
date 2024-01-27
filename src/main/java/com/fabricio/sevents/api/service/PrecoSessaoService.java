package com.fabricio.sevents.api.service;

import com.fabricio.sevents.api.model.domain.PriceSession;
import com.fabricio.sevents.api.model.dto.persist.PrecoSessaoPersist;
import com.fabricio.sevents.api.model.dto.request.PrecoSessaoRequest;
import com.fabricio.sevents.api.model.dto.response.PrecoSessaoJoinSessaoResponse;
import com.fabricio.sevents.api.model.dto.response.PrecoSessaoResponse;
import com.fabricio.sevents.api.repository.PrecoSessaoRepository;
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

import static com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum.GLOBAL_EXPRESSAO_SORT_INVALIDO;
import static com.fabricio.sevents.api.exceptionhandler.enumeration.ExceptionEnum.checkThrow;
import static com.fabricio.sevents.api.util.object.Objeto.notBlank;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PrecoSessaoService extends GenericObjectContext {

    private final PrecoSessaoRepository precoRepository;

    private final SessaoService sessaoService;

    public PrecoSessaoResponse criar(PrecoSessaoPersist persist){

        PriceSession preco = convert(persist, PriceSession.class);

        return convert(precoRepository.save(preco));

    }
    public PageBevent<PrecoSessaoResponse> get(PrecoSessaoRequest request){
        PriceSession precoSessao = convert(request, PriceSession.class);
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.STARTING);

        Page<PriceSession> page = precoRepository
            .findAll(
                Example.of(precoSessao, exampleMatcher),
                PageableSevents.setPageable(request.getPage(), request.getLimit(), PriceSession.class, request.getSort())
            );

        checkThrow(Objeto.isBlank(page) || Objeto.isBlank(page.getContent()), GLOBAL_EXPRESSAO_SORT_INVALIDO);
        return convertToPageBevent(page, new TypeToken<List<PrecoSessaoResponse>>() {}.getType());
    }
    
    public List<PrecoSessaoJoinSessaoResponse> findBySessao(UUID id) {

        return precoRepository.findByIdSessao(id);

    }

    private PrecoSessaoResponse convert(PriceSession preco){

        PrecoSessaoResponse response = convert(preco, PrecoSessaoResponse.class);

        if(notBlank(preco.getIdSessao())){

            response.setSessao(sessaoService.findById(preco.getIdSessao()));

        }

        return response;
    }

}