--formatted liquibase sql

--changeset cephei:1
CREATE TABLE question_topics (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    topic_id BIGINT REFERENCES topics,
    question_id BIGINT REFERENCES questions
);