--formatted liquibase sql

--changeset cephei:1
update task_types
set answer_type = 'DETAILED'
where subject_id = 13
and number in (13, 14, 15, 16, 17, 18, 19);

--changeset cephei:2
insert into questions (question, answer, task_type_id, imageurl)
values
    ('',
     '',
     13,
     '/storage/questions/ur13_1'
     ),
    (
     '',
     '',
     13,
     '/storage/questions/ur13_2'
    ),
    (
        '',
        '',
        13,
        '/storage/questions/ur13_3'
    ),
    (
     'Основанием треугольной призмы ABC1B1C1 является правильный треугольник ABC со стороной 1, а боковое ребро равно  Диагональ боковой грани A1B перпендикулярна плоскости основания. Точка M — середина стороны ВС.' ||
     'а)Докажите, что прямые АМ и A1C перпендикулярны.' ||
     'б)Найдите расстояние между прямыми A1C и BC1.',
     '',
     14,
     null
    ),
    (
        'Дана прямая треугольная призма ABCA1B1C1. Известно, что AB=BC. Точка K — середина ребра A1B1, а точка M лежит на ребре AC и делит его в отношении AM:MC = 1:3.' ||
        'а)Докажите, что прямая KM перпендикулярна прямой AC .' ||
        'б)Найдите расстояние между прямыми KM и A1C1, если AB=10, AC=8 и AA1=3.',
     '',
     14,
     null
    ),
    (
     'Решите неравенство',
     '',
     15,
     '/storage/questions/nr15_1'
    )