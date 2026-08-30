--formatted liquibase sql

--changeset cephei:1
insert into topics (topic)
values
    ('Right-angled triangle'),
    ('Isosceles triangle'),
    ('General triangles '),
    ('Parallelograms'),
    ('Trapezoid'),
    ('Central and inscribed angles'),
    ('Tangent, chord, secant'),
    ('Inscribed circles'),
    ('Circumscribed circles'),
    ('Vectors and operations with them');

--changeset cephei:2
insert into question_topics(topic_id, question_id)
VALUES
    (1, 1),
    (1, 2),
    (6, 3),
    (9, 3),
    (2, 4),
    (6, 5),
    (7, 5),
    (10,6),
    (10, 7),
    (10, 8);

