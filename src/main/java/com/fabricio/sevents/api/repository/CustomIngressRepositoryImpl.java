package com.fabricio.sevents.api.repository;

import com.fabricio.sevents.api.model.dto.response.IngressEventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CustomIngressRepositoryImpl implements CustomIngressRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private static final String QUERY_FIND_BY_ID_EVENTO = """
        SELECT
            i.id,
            i.nome AS nomeIngresso,
            i.descricao AS descricaoIngresso,
            i.disponibilidade AS disponibilidade, 
            i.data_inicio AS dataInicio,
            i.data_fim AS dataFim,
            i.imagem,
            i.id_evento AS idEvento,
            e.nome AS nomeEvento, 
            e.descricao AS descricaoEvento
        FROM
            ingresso i
        INNER JOIN evento e ON i.id_evento = e.id
        WHERE
            i.id_evento = :idEvento AND
            i.deletado_em IS NULL
""";


    @Override
    public List<IngressEventResponse> findByIdEvento(UUID idEvento) {

        BeanPropertyRowMapper<IngressEventResponse> rowMapper = new BeanPropertyRowMapper<>(IngressEventResponse.class);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idEvento", idEvento);

        return namedJdbcTemplate.query(QUERY_FIND_BY_ID_EVENTO, parameters, rowMapper);

    }
}
