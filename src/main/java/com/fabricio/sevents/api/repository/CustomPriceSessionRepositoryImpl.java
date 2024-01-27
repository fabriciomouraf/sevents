package com.fabricio.sevents.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fabricio.sevents.api.model.dto.response.PriceSessionJoinSessionResponse;

@Repository
public class CustomPriceSessionRepositoryImpl implements CustomPriceSessionRepository {
  
  @Autowired
  private NamedParameterJdbcTemplate namedJdbcTemplate;

  private static final String QUERY_FIND_BY_ID_EVENTO = """
      SELECT
          p.id,
          p.nome AS nomePrecoSessao,
          p.descricao AS descricaoPrecoSessao,
          p.disponibilidade AS disponibilidade, 
          ROUND(p.preco, 2) AS preco,
          p.quantidade_maxima AS quantidadeMaxima,
          p.inicio_vendas AS inicioVendas,
          p.id_sessao AS idSessao,
          s.nome AS nomeSessao, 
          s.descricao AS descricaoSessao
      FROM
          preco_sessao p
      INNER JOIN sessao s ON p.id_sessao = s.id
      WHERE
          p.id_sessao = :idSessao AND
          p.deletado_em IS NULL
""";

  @Override
  public List<PriceSessionJoinSessionResponse> findByIdSessao(UUID idSessao) {
    BeanPropertyRowMapper<PriceSessionJoinSessionResponse> rowMapper = new BeanPropertyRowMapper<>(PriceSessionJoinSessionResponse.class);

    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("idSessao", idSessao);

    return namedJdbcTemplate.query(QUERY_FIND_BY_ID_EVENTO, parameters, rowMapper);
  }

}