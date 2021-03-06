CREATE TABLE IF NOT EXISTS advertisements_images
(
    id         SERIAL PRIMARY KEY,
    ad_id      INT      NOT NULL,
    image_id   INT      NOT NULL REFERENCES images (id) ON DELETE CASCADE,
    is_primary BOOLEAN  NOT NULL,
    position   SMALLINT NOT NULL
);