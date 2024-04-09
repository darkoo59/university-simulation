create table public.member
(
    academic_title_id   bigint
        constraint fk3gnt6x3taisad9a38g223qabm
            references public.academic_title,
    education_title_id  bigint
        constraint fkousd9shldpot38vj7yepnuq83
            references public.education_title,
    id                  bigserial
        primary key,
    scientific_field_id bigint
        constraint fkkarn111hkvnoujdvxd60d4tc8
            references public.scientific_field,
    firstname           varchar(30),
    lastname            varchar(30)
);

alter table public.member
    owner to postgres;

