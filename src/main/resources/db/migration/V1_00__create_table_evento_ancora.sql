CREATE TABLE evento_ancora (
    id UUID PRIMARY KEY,
    nome VARCHAR(255),
    descricao TEXT,
    disponibilidade VARCHAR(100),
    data_inicio TIMESTAMP,
    data_fim TIMESTAMP,
    criado_em TIMESTAMP,
    atualizado_em TIMESTAMP,
    deletado_em TIMESTAMP
);
