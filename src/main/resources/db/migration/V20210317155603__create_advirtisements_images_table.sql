CREATE TABLE advertisements_images
(
    id       SERIAL PRIMARY KEY,
    ad_id    INT      NOT NULL,
    image_id INT      NOT NULL REFERENCES images(id),
    position SMALLINT NOT NULL
);