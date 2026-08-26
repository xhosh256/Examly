--formatted liquibase sql

--changeset cephei:1
create table users (
    id int generated always as identity primary key ,
    username varchar(128) not null unique ,
    password varchar(128) not null
);

create table profiles (
    id int generated always as identity primary key ,
    firstname varchar(128) not null ,
    lastname varchar(128) ,
    birthDate date,

    user_id int unique references users
);