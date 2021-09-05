CREATE TABLE books
(
    id        BIGINT AUTO_INCREMENT,
    isbn      VARCHAR(255),
    title     VARCHAR(255),
    author_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES authors (id)
);