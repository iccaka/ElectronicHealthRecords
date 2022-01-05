ALTER TABLE examination
    ADD  perscription_id BIGINT;

ALTER TABLE examination
    ADD CONSTRAINT FK_EXAMINATION_ON_TREATMENT FOREIGN KEY (perscription_id) REFERENCES perscription (id);