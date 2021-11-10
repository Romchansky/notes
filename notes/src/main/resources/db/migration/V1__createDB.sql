DROP
DATABASE IF EXISTS "NoteAppBase";
CREATE
DATABASE "NoteAppBase"
    WITH OWNER = postgres
    ENCODING = 'UTF8';

CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    userName      varchar(255) UNIQUE NOT NULL,
    user_password varchar(255)        NOT NULL,
    user_role     varchar(50)         NOT NULL


);

CREATE TABLE note
(
    id          SERIAL PRIMARY KEY,
    name        varchar(100)   NOT NULL,
    description varchar(10000) NOT NULL,
    access      varchar(10)    NOT NULL,
    user_id     varchar(255)   NOT NULL
);