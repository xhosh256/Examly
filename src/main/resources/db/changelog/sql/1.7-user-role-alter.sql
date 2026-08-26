--formatted liquibase sql

--changeset cephei:1
alter table users add column role varchar(128) not null