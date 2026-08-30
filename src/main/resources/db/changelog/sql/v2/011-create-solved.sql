--formatted liquibase sql

--changeset cephei:1
create table solves (
    id bigint generated always as identity primary key ,
    user_id int references users,
    question_id bigint references questions
)