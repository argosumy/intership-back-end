CREATE TABLE "advertisements"
(
    "id"                    SERIAL PRIMARY KEY NOT NULL,
    "description"           TEXT               NOT NULL,
    "category"              VARCHAR(50)        NOT NULL,
    "price"                 BIGINT             NOT NULL,
    "currency"              VARCHAR(3)         NOT NULL,
    "is_discount_available" BOOLEAN            NOT NULL DEFAULT False,
    "user_id"               INTEGER            NOT NULL,
    "location"              VARCHAR(50)        NOT NULL,
    "status"                VARCHAR(20)        NOT NULL,
    "created_at"            timestamp          NOT NULL,
    "publication_datetime"  timestamp          NOT NULL,
    "status_changed_at"     timestamp          NOT NULL
);