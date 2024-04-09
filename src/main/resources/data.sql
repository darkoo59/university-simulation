INSERT INTO academic_title (title)
VALUES ('Professor'),
       ('Associate Professor'),
       ('Assistant Professor'),
       ('Researcher'),
       ('Lecturer'),
       ('Secretary'),
       ('Research Assistant');

INSERT INTO education_title (title)
VALUES ('PhD'),
       ('Master'),
       ('Bachelor'),
       ('High School'),
       ('Primary School'),
       ('Associate Degree'),
       ('Diploma');

INSERT INTO scientific_field (field)
VALUES ('Computer Science'),
       ('Mathematics'),
       ('Physics'),
       ('Biology'),
       ('Chemistry'),
       ('History'),
       ('Psychology'),
       ('Economics'),
       ('Literature'),
       ('Engineering'),
       ('Geology'),
       ('Environmental Science');

INSERT INTO member (firstname, lastname, academic_title_id, education_title_id, scientific_field_id)
VALUES ('John', 'Doe', 1, 1, 1),
       ('Alice', 'Johanson', 6, 4, 1),
       ('Emily', 'Smith', 3, 2, 2),
       ('Michael', 'Johnson', 4, 3, 3),
       ('Jessica', 'Brown', 5, 4, 4),
       ('William', 'Anderson', 6, 5, 5),
       ('Sophia', 'Martinez', 7, 6, 6);

INSERT INTO department (name, short_name, secretary_id, head_of_department_id)
VALUES ('Department of Computer Science', 'CS', 2, 1),
       ('Department of Mathematics', 'Math', 5, 3),
       ('Department of Science', 'Science', 7, 4);

UPDATE member
SET department_id = 1
WHERE id = 1;
UPDATE member
SET department_id = 1
WHERE id = 2;
UPDATE member
SET department_id = 1
WHERE id = 3;
UPDATE member
SET department_id = 2
WHERE id = 4;
UPDATE member
SET department_id = 2
WHERE id = 5;
UPDATE member
SET department_id = 3
WHERE id = 6;
UPDATE member
SET department_id = 3
WHERE id = 7;

INSERT INTO subject (name, espb, department_id)
VALUES ('Introduction to Computer Science', 6, 1),
       ('Calculus', 8, 2),
       ('Quantum Mechanics', 7, 3),
       ('Genetics', 6, 3),
       ('Organic Chemistry II', 7, 3),
       ('European History', 6, 3),
       ('Developmental Psychology', 6, 3);

INSERT into academic_title_history (start_date, end_date, member_id, academic_title_id, scientific_field_id)
VALUES ('2020-01-01', '2020-12-31', 1, 2, 1),
       ('2021-01-01', null, 1, 1, 1),
       ('2020-01-01', null, 2, 6, 1),
       ('2019-01-01', '2019-12-31', 3, 2, 2),
       ('2020-01-01', NULL, 3, 3, 2),
       ('2018-01-01', '2019-12-31', 4, 3, 3),
       ('2020-01-01', NULL, 4, 4, 3),
       ('2017-01-01', '2018-12-31', 5, 4, 4),
       ('2019-01-01', NULL, 5, 5, 4),
       ('2016-01-01', NULL, 6, 5, 5),
       ('2017-01-01', NULL, 7, 6, 6);

INSERT INTO department_management_history (start_date, end_date, secretary_id, head_of_department_id, department_id)
VALUES ('2020-01-01', null, 2, 1, 1),
       ('2019-01-01', NULL, 5, 3, 2),
       ('2018-01-01', '2019-12-31', 7, 4, 3),
       ('2017-01-01', NULL, 7, 4, 3);