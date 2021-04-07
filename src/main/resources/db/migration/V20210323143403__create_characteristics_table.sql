CREATE TABLE characteristics
(
    id                    SERIAL PRIMARY KEY,
    advertisement_id      INT          NOT NULL REFERENCES advertisements (id),
    category_name         VARCHAR(100) NOT NULL,
    characteristics_name  VARCHAR(100) NOT NULL,
    characteristics_value VARCHAR(100) NOT NULL,
    is_deleted            BOOLEAN DEFAULT false,
    is_approved           BOOLEAN DEFAULT false
);