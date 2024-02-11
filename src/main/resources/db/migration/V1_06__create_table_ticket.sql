CREATE TABLE ticket (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    document VARCHAR(15),
    phone VARCHAR(30),
    birthdate TIMESTAMP,
    status VARCHAR(100),
    id_ingress UUID NOT NULL,
    id_session UUID NOT NULL,
    id_price UUID NOT NULL,
    id_purchase UUID NOT NULL,
    created_at TIMESTAMP,
    deleted_at TIMESTAMP,
    updated_at TIMESTAMP
);