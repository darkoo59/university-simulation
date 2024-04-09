create table if not exists public.academic_title
(
    id    bigserial
    primary key,
    title varchar(20)
    );

alter table public.academic_title
    owner to postgres;

