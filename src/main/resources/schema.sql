CREATE TABLE IF NOT EXISTS reservations (
    PRIMARY KEY (id),
    id       INT          NOT NULL GENERATED ALWAYS AS IDENTITY,
    at       TIMESTAMP    NOT NULL,
    name     VARCHAR (50) NOT NULL,
    email    VARCHAR (50) NOT NULL,
    quantity INT          NOT NULL
);
