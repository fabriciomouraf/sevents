CREATE TABLE evento (
    id UUID PRIMARY KEY,
    nome VARCHAR(255),
    descricao TEXT,
    disponibilidade VARCHAR(100),
    data_inicio TIMESTAMP,
    data_fim TIMESTAMP,
    criado_em TIMESTAMP,
    deletado_em TIMESTAMP,
    atualizado_em TIMESTAMP,
    id_evento_ancora UUID NOT NULL
);
