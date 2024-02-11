CREATE TABLE macro_event (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    availability VARCHAR(100),
    started_at TIMESTAMP,
    ended_at TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);
