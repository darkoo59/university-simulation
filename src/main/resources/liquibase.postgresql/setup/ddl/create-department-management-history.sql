create table public.department_management_history
(
    end_date      date,
    start_date    date,
    department_id bigint
        constraint fkqdw3lrj5xiwpmro1mxxh06nb2
            references public.department,
    head_of_department_id   bigint
        constraint fksr2war74kyt3kay1053co0lst
            references public.member,
    id            bigserial
        primary key,
    secretary_id  bigint
        constraint fk4v9fgiu1tleuxwwwp16tpkecl
            references public.member
);

alter table public.department_management_history
    owner to postgres;

