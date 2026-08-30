--formatted liquibase sql

--changeset cephei:1
insert into subjects (name)
values
    ('PROFILE_MATH'),
    ('BASE_MATH');