CREATE TABLE ingress (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    image varchar(255),
    availability VARCHAR(100),
    startedAt TIMESTAMP,
    endedAt TIMESTAMP,
    created_at TIMESTAMP,
    deleted_at TIMESTAMP,
    updated_at TIMESTAMP,
    id_event UUID NOT NULL
);
