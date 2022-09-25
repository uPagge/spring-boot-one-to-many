CREATE TABLE status
(
    id    BIGINT NOT NULL PRIMARY KEY,
    title VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE post
(
    id     BIGINT NOT NULL PRIMARY KEY,
    title  VARCHAR(60),
    status VARCHAR(30) NOT NULL
        CONSTRAINT fk_post_status_status_title
            REFERENCES status(title) ON UPDATE CASCADE
);