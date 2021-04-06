ALTER TABLE images
    ADD COLUMN is_attached boolean NOT NULL,
    ADD COLUMN uploaded_at TIMESTAMP NOT NULL;