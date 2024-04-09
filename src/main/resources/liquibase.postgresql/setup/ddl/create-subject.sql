create table public.subject
(
    espb          integer,
    department_id bigint
        constraint fks2rgt0imytiyktkr2r9yxp9tw
            references public.department,
    id            bigserial
        primary key,
    name          varchar(50)
);

alter table public.subject
    owner to postgres;

