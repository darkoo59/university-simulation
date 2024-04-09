create table public.education_title
(
    id    bigserial
        primary key,
    title varchar(20)
);

alter table public.education_title
    owner to postgres;

