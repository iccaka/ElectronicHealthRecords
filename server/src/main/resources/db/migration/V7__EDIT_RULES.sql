ALTER TABLE examination
    ADD  prescription_id BIGINT;

ALTER TABLE examination
    ADD CONSTRAINT FK_EXAMINATION_ON_TREATMENT FOREIGN KEY (prescription_id) REFERENCES prescription (id);