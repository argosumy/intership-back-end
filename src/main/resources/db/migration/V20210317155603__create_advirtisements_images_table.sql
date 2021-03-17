CREATE TABLE advertisements_images
(
    id         SERIAL PRIMARY KEY,
    ad_id      INT      NOT NULL,
    image_id   INT      NOT NULL REFERENCES images (id),
    is_primary BOOLEAN  NOT NULL,
    position   SMALLINT NOT NULL
);