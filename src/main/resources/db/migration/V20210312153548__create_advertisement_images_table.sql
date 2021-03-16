CREATE TABLE advertisement_images (
    id SERIAL PRIMARY KEY,
    ad_id INT NOT NULL,
    is_primary BOOLEAN NOT NULL,
    position_order INT NOT NULL,
    image_url TEXT NOT NULL
);
