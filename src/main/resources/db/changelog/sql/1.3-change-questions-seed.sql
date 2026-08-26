alter table questions
add column imageUrl varchar(1024);

insert into questions (question, imageUrl, answer, task_type_id)
values (
        'In a triangle ABC corner C equal 90°,AB =10, BC =√19 Find cos𝐴.',
        '/storage/questions/p1.png',
        '0.9',
        1
       );