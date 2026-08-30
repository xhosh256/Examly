--formatted liquibase sql

--changeset cephei:1
CREATE TABLE task_types (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    subject_id int references subjects,
    number int not null ,
    name varchar(128) not null ,
    answer_type varchar(64) not null default 'SHORT',
    unique (subject_id, number)
);