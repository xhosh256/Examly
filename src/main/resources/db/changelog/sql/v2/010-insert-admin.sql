--formatted liquibase sql

--changeset cephei:1
insert into users (username, password, role)
values ('admin', '$2a$12$9NxOvck0bEFAA1kwltTTce56HtqSZHX1gVPO/iPhPRixkS0KIjdu.', 'ADMIN');

insert into profiles (firstname, lastname, birthdate, user_id)
values ('admin', 'admin', '10-09-2008', 1)