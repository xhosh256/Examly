--formatted liquibase sql

--changeset cephei:1
alter table task_types
    add column answer_type varchar(64) not null default 'SHORT'
