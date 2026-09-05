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

CREATE TABLE modalities
(
    id     BIGSERIAL PRIMARY KEY,
    name   VARCHAR(100) NOT NULL UNIQUE,
    active BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE graduations
(
    id          BIGSERIAL PRIMARY KEY,
    modality_id BIGINT       NOT NULL REFERENCES modalities (id),
    name        VARCHAR(100) NOT NULL,
    UNIQUE (modality_id, name)
);

CREATE TABLE plans
(
    id             BIGSERIAL PRIMARY KEY,
    modality_id    BIGINT         NOT NULL REFERENCES modalities (id),
    name           VARCHAR(100)   NOT NULL,
    monthly_amount NUMERIC(10, 2) NOT NULL CHECK (monthly_amount >= 0),
    active         BOOLEAN        NOT NULL DEFAULT TRUE,
    UNIQUE (modality_id, name)
);

CREATE TABLE enrollments
(
    id              BIGSERIAL PRIMARY KEY,
    student_id      BIGINT      NOT NULL REFERENCES students (id),
    enrollment_date DATE        NOT NULL DEFAULT CURRENT_DATE,
    day_maturity    DATE,
    status          VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    CHECK (status IN ('ACTIVE', 'CLOSED', 'CANCELED'))
);

CREATE TABLE enrollments_modalities
(
    id            BIGSERIAL PRIMARY KEY,
    enrollment_id BIGINT NOT NULL REFERENCES enrollments (id),
    modality_id   BIGINT NOT NULL REFERENCES modalities (id),
    graduation_id BIGINT NOT NULL REFERENCES graduations (id),
    plan_id       BIGINT NOT NULL REFERENCES plans (id),
    start_date    DATE   NOT NULL DEFAULT CURRENT_DATE,
    end_date      DATE,
    UNIQUE (enrollment_id, graduation_id)
);

CREATE TABLE enrollment_invoices
(
    id                BIGSERIAL PRIMARY KEY,
    enrollment_id     BIGINT         NOT NULL REFERENCES enrollments (id),
    due_date          DATE           NOT NULL,
    value             NUMERIC(10, 2) NOT NULL CHECK ( value >= 0 ),
    payment_date      TIMESTAMP,
    cancellation_date DATE,
    status            VARCHAR(20)    NOT NULL DEFAULT 'OPEN',
    CHECK ( status IN ('OPEN', 'PAID', 'CANCELED', 'EXPIRED') ),
    UNIQUE (enrollment_id, due_date)
);

CREATE TABLE regularity
(
    id            BIGSERIAL PRIMARY KEY,
    enrollment_id BIGINT    NOT NULL REFERENCES enrollments (id),
    entry_date    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    exit_date     TIMESTAMP
)