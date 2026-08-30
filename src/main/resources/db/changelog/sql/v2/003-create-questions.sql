--formatted liquibase sql

--changeset cephei:1
CREATE TABLE questions (
   id bigint generated always as identity primary key ,
   question TEXT not null ,
   imageUrl varchar(1024) ,
   answer TEXT NOT NULL,
   task_type_id bigint references task_types
);

