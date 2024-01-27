package com.fabricio.sevents.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fabricio.sevents.api.model.dto.response.SessionIngressResponse;

@Repository
public class CustomSessionRepositoryImpl implements CustomSessionRepository {
  
  @Autowired
  private NamedParameterJdbcTemplate namedJdbcTemplate;

  private static final String QUERY_FIND_BY_ID_INGRESS = """
      SELECT
          s.id,
          s.nome AS nomeSessao,
          s.descricao AS descricaoSessao,
          s.disponibilidade AS disponibilidade, 
          s.data_inicio AS dataInicio,
          s.data_fim AS dataFim,
          s.id_ingresso AS idIngresso,
          i.nome AS nomeIngresso, 
          i.descricao AS descricaoIngresso
      FROM
          sessao s
      INNER JOIN ingresso i ON s.id_ingresso = i.id
      WHERE
          s.id_ingresso = :idIngresso AND
          s.deletado_em IS NULL
""";
  @Override
  public List<SessionIngressResponse> findByIdIngress(UUID idIngress) {
    BeanPropertyRowMapper<SessionIngressResponse> rowMapper = new BeanPropertyRowMapper<>(SessionIngressResponse.class);

    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("idIngresso", idIngress);

    return namedJdbcTemplate.query(QUERY_FIND_BY_ID_INGRESS, parameters, rowMapper);
  }

}