CREATE TABLE ingresso (
    id UUID PRIMARY KEY,
    nome VARCHAR(255),
    descricao TEXT,
    imagem varchar(255),
    disponibilidade VARCHAR(100),
    data_inicio TIMESTAMP,
    data_fim TIMESTAMP,
    criado_em TIMESTAMP,
    deletado_em TIMESTAMP,
    atualizado_em TIMESTAMP,
    id_evento UUID NOT NULL
);
