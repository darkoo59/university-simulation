INSERT INTO academic_title (id, title)
VALUES (1, 'Professor'),
       (2, 'Associate Professor'),
       (3, 'Assistant Professor'),
       (4, 'Researcher'),
       (5, 'Lecturer'),
       (6, 'Secretary'),
       (7, 'Research Assistant');

INSERT INTO education_title (id, title)
VALUES (1, 'PhD'),
       (2, 'Master'),
       (3, 'Bachelor'),
       (4, 'High School'),
       (5, 'Primary School'),
       (6, 'Associate Degree'),
       (7, 'Diploma');

INSERT INTO scientific_field (id, field)
VALUES (1, 'Computer Science'),
       (2, 'Mathematics'),
       (3, 'Physics'),
       (4, 'Biology'),
       (5, 'Chemistry'),
       (6, 'History'),
       (7, 'Psychology'),
       (8, 'Economics'),
       (9, 'Literature'),
       (10, 'Engineering'),
       (11, 'Geology'),
       (12, 'Environmental Science');

INSERT INTO member (id, firstname, lastname, academic_title_id, education_title_id, scientific_field_id)
VALUES (1, 'John', 'Doe', 1, 1, 1),
       (2, 'Alice', 'Johanson', 6, 4, 1),
       (3, 'Emily', 'Smith', 3, 2, 2),
       (4, 'Michael', 'Johnson', 4, 3, 3),
       (5, 'Jessica', 'Brown', 5, 4, 4),
       (6, 'William', 'Anderson', 6, 5, 5),
       (7, 'Sophia', 'Martinez', 7, 6, 6);

INSERT INTO department (id, name, short_name, secretary_id, head_of_department_id)
VALUES (1, 'Department of Computer Science', 'CS', 2, 1),
       (2, 'Department of Mathematics', 'Math', 5, 3),
       (3, 'Department of Science', 'Science', 7, 4);

UPDATE member SET department_id = 1 WHERE id = 1;
UPDATE member SET department_id = 1 WHERE id = 2;
UPDATE member SET department_id = 1 WHERE id = 3;
UPDATE member SET department_id = 2 WHERE id = 4;
UPDATE member SET department_id = 2 WHERE id = 5;
UPDATE member SET department_id = 3 WHERE id = 6;
UPDATE member SET department_id = 3 WHERE id = 7;

INSERT INTO subject (id, name, espb, department_id)
VALUES (1, 'Introduction to Computer Science', 6, 1),
       (2, 'Calculus', 8, 2),
       (3, 'Quantum Mechanics', 7, 3),
       (4, 'Genetics', 6, 3),
       (5, 'Organic Chemistry II', 7, 3),
       (6, 'European History', 6, 3),
       (7, 'Developmental Psychology', 6, 3);

INSERT into academic_title_history (id, start_date, end_date, member_id, academic_title_id, scientific_field_id)
VALUES (1, '2020-01-01', '2020-12-31', 1,  2, 1),
       (2, '2021-01-01', null, 1, 1, 1),
       (3, '2020-01-01', null, 2, 6, 1),
       (4, '2019-01-01', '2019-12-31', 3, 2, 2),
       (5, '2020-01-01', NULL, 3, 3, 2),
       (6, '2018-01-01', '2019-12-31', 4, 3, 3),
       (7, '2020-01-01', NULL, 4, 4, 3),
       (8, '2017-01-01', '2018-12-31', 5, 4, 4),
       (9, '2019-01-01', NULL, 5, 5, 4),
       (10, '2016-01-01', NULL, 6, 5, 5),
       (11, '2018-01-01', NULL, 7, 6, 6);

INSERT INTO department_management_history (id, start_date, end_date, secretary_id, head_of_department_id, department_id)
VALUES (1, '2020-01-01', null, 2, 1, 1),
       (2, '2019-01-01', NULL, 5, 3, 2),
       (3, '2018-01-01', '2019-12-31', 7, 4, 3),
       (4, '2017-01-01', NULL, 7, 4, 3);