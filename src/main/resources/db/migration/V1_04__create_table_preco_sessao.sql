CREATE TABLE preco_sessao (
    id UUID PRIMARY KEY,
    nome VARCHAR(255),
    descricao TEXT,
    disponibilidade VARCHAR(100),
    preco DECIMAL(21,9),
    quantidade_maxima INT NOT NULL,
    inicio_vendas TIMESTAMP NOT NULL,
    fim_vendas TIMESTAMP NOT NULL,
    min_por_compra INT,
    max_por_compra INT,
    criado_em TIMESTAMP,
    deletado_em TIMESTAMP,
    atualizado_em TIMESTAMP,
    id_sessao UUID NOT NULL
);