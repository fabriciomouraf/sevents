CREATE TABLE compra (
    id UUID PRIMARY KEY,
    codigo_carrinho VARCHAR(255),
    id_usuario UUID NOT NULL,
    status VARCHAR(100),
    expira_em TIMESTAMP,
    criado_em TIMESTAMP,
    deletado_em TIMESTAMP,
    atualizado_em TIMESTAMP
);