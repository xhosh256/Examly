--formatted liquibase sql

--changeset cephei:1
create table profiles (
  id int generated always as identity primary key ,
  firstname varchar(128) not null ,
  lastname varchar(128) ,
  birthDate date,

  user_id int unique references users
);