--formatted liquibase sql

--changeset cephei:1
insert into task_types (subject_id, number, name, answer_type)
values
    (1, 1, 'Planimetry', 'SHORT'),
    (1, 2, 'Vectors', 'SHORT'),
    (1, 3, 'Stereometry', 'SHORT'),
    (1, 4, 'The beginnings of probability theory', 'SHORT'),
    (1, 5, 'Probabilities of complex events', 'SHORT'),
    (1, 6, 'The simplest equations', 'SHORT'),
    (1, 7, 'Calculations and transformations', 'SHORT'),
    (1, 8, 'Derivative and antiderivative', 'SHORT'),
    (1, 9, 'Problems with applied content', 'SHORT'),
    (1, 10, 'Word problems', 'SHORT'),
    (1, 11, 'Graphs of functions', 'SHORT'),
    (1, 12, 'The greatest and least value of functions', 'SHORT'),
    (1, 13, 'Equations', 'DETAILED'),
    (1, 14, 'Stereometric problem', 'DETAILED'),
    (1, 15, 'Inequalities', 'DETAILED'),
    (1, 16, 'Financial mathematics', 'DETAILED'),
    (1, 17, 'Planimetric problem', 'DETAILED'),
    (1, 18, 'Problem with a parameter', 'DETAILED'),
    (1, 19, 'Numbers and their properties', 'DETAILED');

--changeset cephei:2
insert into task_types (subject_id, number, name)
VALUES (2, 1, 'The simplest text problems'),
       (2, 2, 'Dimensions and units of measurement'),
       (2, 3, 'Reading graphs and charts'),
       (2, 4, 'Expression transformations'),
       (2, 5, 'The beginnings of probability theory'),
       (2, 6, 'Choosing the best option'),
       (2, 7, 'Analysis of graphs and charts'),
       (2, 8, 'Analysis of statements'),
       (2, 9, 'Problems on a square grid'),
       (2, 10, 'Applied Geometry'),
       (2, 11, 'Applied stereometry'),
       (2, 12, 'Planimetry'),
       (2, 13, 'Problems in stereometry'),
       (2, 14, 'Calculations'),
       (2, 15, 'The simplest text problems'),
       (2, 16, 'Calculations and transformations'),
       (2, 17, 'The simplest equations'),
       (2, 18, 'Inequalities'),
       (2, 19, 'Numbers and their properties'),
       (2, 20, 'Word problems'),
       (2, 21, 'Tasks for quick wits');