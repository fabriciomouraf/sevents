CREATE TABLE price_session (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    availability VARCHAR(100),
    price DECIMAL(21,9),
    max_quantity INT NOT NULL,
    started_sale TIMESTAMP NOT NULL,
    endedSale TIMESTAMP NOT NULL,
    min_per_purchase INT,
    max_per_purchase INT,
    created_at TIMESTAMP,
    deleted_at TIMESTAMP,
    updated_at TIMESTAMP,
    id_session UUID NOT NULL
);