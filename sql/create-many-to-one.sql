CREATE TABLE tutorials
(
    id          BIGINT NOT NULL PRIMARY KEY,
    description VARCHAR(255),
    published   BOOLEAN,
    title       VARCHAR(255)
);

CREATE TABLE photos
(
    id          BIGINT NOT NULL PRIMARY KEY,
    description VARCHAR(255),
    published   BOOLEAN,
    title       VARCHAR(255)
);

CREATE TABLE comments
(
    id      BIGINT NOT NULL PRIMARY KEY,
    content TEXT
);

CREATE TABLE tutorial_comments
(
    tutorial_id BIGINT NOT NULL
        CONSTRAINT fk_tutorial_comments_tutorial_id
            REFERENCES tutorials,
    comment_id  BIGINT NOT NULL
        CONSTRAINT fk_tutorial_comments_comment_id
            REFERENCES comments
);

CREATE TABLE photos_comments
(
    photo_id   BIGINT NOT NULL
        CONSTRAINT fk_photos_comments_photo_id
            REFERENCES photos,
    comment_id BIGINT NOT NULL
        CONSTRAINT fk_photos_comments_comment_id
            REFERENCES comments
)