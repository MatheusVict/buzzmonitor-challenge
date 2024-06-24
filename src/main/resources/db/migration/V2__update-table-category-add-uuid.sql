ALTER TABLE category
    ADD COLUMN uuid UUID NOT NULL;


ALTER TABLE category
    ADD CONSTRAINT uc_category_uuid UNIQUE (uuid);