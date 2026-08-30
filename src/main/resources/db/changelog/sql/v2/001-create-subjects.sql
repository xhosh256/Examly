--formatted liquibase sql

--changeset cephei:1
CREATE TABLE subjects (
  id int generated always as identity primary key ,
  name varchar(128) not null unique
);
