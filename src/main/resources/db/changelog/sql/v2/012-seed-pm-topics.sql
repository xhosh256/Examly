--formatted liquibase sql

--changeset cephei:1
insert into topics (topic)
values
    ('Right triangle'),
    ('Isosceles and equilateral triangle'),
    ('Parallelograms'),
    ('Trapezoid'),
    ('Inscribed circles'),
    ('Circumscribed circles'),
    ('Central and inscribed angles'),
    ('Tangent, chord, secant');

--changeset cephei:2
insert into topics (topic)
values
    ('Vectors and operations on them');