CREATE TABLE purchase (
    id UUID PRIMARY KEY,
    cart_code VARCHAR(255),
    id_user UUID NOT NULL,
    status VARCHAR(100),
    expires_in TIMESTAMP,
    created_at TIMESTAMP,
    deleted_at TIMESTAMP,
    updated_at TIMESTAMP
);