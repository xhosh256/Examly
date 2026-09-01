--formatted liquibase sql

--changeset cephei:1
insert into questions (question, imageurl, answer, task_type_id)
values
    (
     'In triangle 𝐴𝐵𝐶, angle 𝐶 is 90°, 𝐴𝐵 = 20, 𝐴𝐶 = 2√19. Find cos𝐵',
     '/storage/questions/profile_math/PM-1-1.png',
     '0.9',
     1
    ),
    (
     'In triangle 𝐴𝐵𝐶, it is known that 𝐴𝐵 = 𝐵𝐶 = 15, 𝐴𝐶 = 24. Find the length of the median 𝐵𝑀.',
     '/storage/questions/profile_math/PM-1-2.png',
     '9',
     1
    ),
    (
     'In triangle 𝐴𝐵𝐶, angle 𝐶 is 90°, 𝐴𝐵 =5, 𝐴𝐶 =√21. Find sin𝐴.',
     '/storage/questions/profile_math/PM-1-3.png',
     '0.4',
     1
    ),
    (
     'In triangle 𝐴𝐵𝐶, it is known that 𝐴𝐵 =𝐵𝐶 =25, 𝐴𝐶 =14. Find the area of triangle 𝐴𝐵𝐶.',
     '/storage/questions/profile_math/PM-1-4.png',
     '168',
     1
    ),
    (
     'Segments 𝐴𝐶 and 𝐵𝐷 are diameters of a circle with center 𝑂. Angle 𝐴𝐶𝐵 is equal to 41°. Find the measure of angle 𝐴𝑂𝐷. Give the answer in degrees.',
     '/storage/questions/profile_math/PM-1-5.png',
     '98',
     1
    ),
    (
        'Find the measure of angle 𝐴𝐶𝑂 if its side 𝐶𝐴 is tangent to the circle with center 𝑂, segment 𝐶𝑂 intersects the circle at point 𝐵 (see figure), and the arc 𝐴𝐵 of the circle enclosed within this angle is 66°. Give the answer in degrees.',
        '/storage/questions/profile_math/PM-1-6.png',
        '24',
        1
    );

--changeset cephei:1
insert into question_topics (topic_id, question_id)
values
    (1, 1),
    (2, 2),
    (1, 3),
    (2, 4),
    (2, 5),
    (7, 5),
    (1, 6),
    (7, 6),
    (8, 6)

