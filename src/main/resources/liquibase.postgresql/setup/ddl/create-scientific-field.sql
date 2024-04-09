create table public.scientific_field
(
    id    bigserial
        primary key,
    field varchar(50)
);

alter table public.scientific_field
    owner to postgres;

