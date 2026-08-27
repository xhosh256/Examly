--formatted liquibase sql

--changeset cephei:1
insert into task_types (subject_id, number, name)
VALUES (14, 1, 'The simplest text problems'),
       (14, 2, 'Dimensions and units of measurement'),
       (14, 3, 'Reading graphs and charts'),
       (14, 4, 'Expression transformations'),
       (14, 5, 'The beginnings of probability theory'),
       (14, 6, 'Choosing the best option'),
       (14, 7, 'Analysis of graphs and charts'),
       (14, 8, 'Analysis of statements'),
       (14, 9, 'Problems on a square grid'),
       (14, 10, 'Applied Geometry'),
       (14, 11, 'Applied stereometry'),
       (14, 12, 'Planimetry'),
       (14, 13, 'Problems in stereometry'),
       (14, 14, 'Calculations'),
       (14, 15, 'The simplest text problems'),
       (14, 16, 'Calculations and transformations'),
       (14, 17, 'The simplest equations'),
       (14, 18, 'Inequalities'),
       (14, 19, 'Numbers and their properties'),
       (14, 20, 'Word problems'),
       (14, 21, 'Tasks for quick wits');