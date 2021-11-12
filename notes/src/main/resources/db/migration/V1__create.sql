CREATE TABLE users
(
    id  varchar(255) PRIMARY KEY NOT NULL,
    user_name      varchar(50) UNIQUE NOT NULL,
    password varchar(100)        NOT NULL,
    user_role     varchar(10)         NOT NULL


);

CREATE TABLE note
(
    id varchar(255) PRIMARY KEY NOT NULL,
    name        varchar(100)   NOT NULL,
    description varchar(10000) NOT NULL,
    access      varchar(10)    NOT NULL,
    user_id     varchar(255)   NOT NULL
);