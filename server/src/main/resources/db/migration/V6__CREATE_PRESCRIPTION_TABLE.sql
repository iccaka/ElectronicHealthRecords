CREATE TABLE prescription
(
    id  BIGINT  NOT NULL,
    medications VARCHAR(255),
    start_date  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_date    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    description VARCHAR(255),
    CONSTRAINT pk_prescription PRIMARY KEY (id)
);