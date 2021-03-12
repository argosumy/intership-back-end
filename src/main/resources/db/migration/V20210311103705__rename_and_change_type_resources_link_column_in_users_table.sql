ALTER TABLE users ALTER COLUMN resources_link TYPE VARCHAR[] USING resources_link::CHARACTER VARYING[];
ALTER TABLE users RENAME COLUMN resources_link TO resource_links;