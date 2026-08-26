--formatted liquibase sql

--changeset cephei:1
CREATE TABLE subjects (
    id int generated always as identity primary key ,
    name varchar(128) not null unique
);

--changeset cephei:2
CREATE TABLE task_types (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    subject_id int references subjects,
    number int not null ,
    name varchar(128) not null ,
    unique (subject_id, number)
);

--changeset cephei:3
CREATE TABLE questions (
    id bigint generated always as identity primary key ,
    question TEXT not null ,
    answer TEXT NOT NULL,
    task_type_id bigint references task_types
);

CREATE TABLE topics (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    topic varchar(256) not null
);

CREATE TABLE question_topics (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    topic_id BIGINT REFERENCES topics,
    question_id BIGINT REFERENCES questions
);