CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(150) NOT NULL,
    role       VARCHAR(20)  NOT NULL DEFAULT 'BASIC',
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    CHECK ( role IN ('ADMIN', 'BASIC') )
)