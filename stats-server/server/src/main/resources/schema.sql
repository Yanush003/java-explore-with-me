CREATE TABLE IF NOT EXISTS hits
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    app       TEXT,
    uri       TEXT,
    ip        TEXT,
    timestamp TIMESTAMP WITHOUT TIME ZONE
);