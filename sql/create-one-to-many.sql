CREATE TABLE tutorials
(
    id          BIGINT NOT NULL PRIMARY KEY,
    description VARCHAR(255),
    published   BOOLEAN,
    title       VARCHAR(255)
);

CREATE TABLE comments
(
    id          BIGINT NOT NULL PRIMARY KEY,
    content     TEXT,
    tutorial_id BIGINT NOT NULL
        CONSTRAINT fk_comments_tutorial_id
            REFERENCES tutorials

);

CREATE INDEX fk_comments_index ON comments (tutorial_id);