CREATE TABLE bilhete (
    id UUID PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(15),
    rg VARCHAR(30),
    telefone VARCHAR(30),
    nascimento TIMESTAMP,
    status VARCHAR(100),
    id_ingresso UUID NOT NULL,
    id_sessao UUID NOT NULL,
    id_preco UUID NOT NULL,
    id_compra UUID NOT NULL,
    criado_em TIMESTAMP,
    deletado_em TIMESTAMP,
    atualizado_em TIMESTAMP
);