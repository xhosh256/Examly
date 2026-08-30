--formatted liquibase sql

--changeset cephei:1
CREATE TABLE topics (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    topic varchar(256) not null
);
