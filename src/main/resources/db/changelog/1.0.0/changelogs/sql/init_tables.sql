CREATE TABLE IF NOT EXISTS department
(
    id   BIGINT PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS users
(
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(255) UNIQUE,
    department_id BIGINT NOT NULL
);

