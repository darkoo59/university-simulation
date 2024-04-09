INSERT INTO public.department (name, short_name, secretary_id, head_of_department_id)
VALUES ('Department of Computer Science', 'CS', 2, 1),
       ('Department of Mathematics', 'Math', 5, 3),
       ('Department of Science', 'Science', 7, 4);

UPDATE public.member
SET department_id = 1
WHERE id = 1;
UPDATE public.member
SET department_id = 1
WHERE id = 2;
UPDATE public.member
SET department_id = 1
WHERE id = 3;
UPDATE public.member
SET department_id = 2
WHERE id = 4;
UPDATE public.member
SET department_id = 2
WHERE id = 5;
UPDATE public.member
SET department_id = 3
WHERE id = 6;
UPDATE public.member
SET department_id = 3
WHERE id = 7;