CREATE TABLE students
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(150) NOT NULL,
    date_of_birth DATE,
    sex           VARCHAR(1) CHECK (sex IN ('M', 'F')),
    telephone     VARCHAR(30),
    cell_phone    VARCHAR(30),
    email         VARCHAR(150),
    observations  TEXT,
    address       VARCHAR(150),
    number        VARCHAR(20),
    complements   VARCHAR(100),
    neighborhood  VARCHAR(100),
    city          VARCHAR(100),
    state         VARCHAR(2),
    cep           VARCHAR(20),
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP
);

CREATE TABLE modalities(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE graduation(
    id BIGSERIAL PRIMARY KEY,
    modality_id BIGINT NOT NULL REFERENCES modalities(id),
    name VARCHAR(100) NOT NULL,
    UNIQUE (modality_id,name)
);

CREATE TABLE plans(
    id BIGSERIAL PRIMARY KEY,

)