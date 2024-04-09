create table public.academic_title_history
(
    end_date            date,
    start_date          date not null,
    academic_title_id   bigint
        constraint fkigtqrowvcj759fok0v7raknb2
            references public.academic_title,
    id                  bigserial
        primary key,
    member_id           bigint
        constraint fk3xnichhbrwr6g9mokuqetgxyy
            references public.member,
    scientific_field_id bigint
        constraint fkrqmxk4j1kw9n76lame50eejx6
            references public.scientific_field
);

alter table public.academic_title_history
    owner to postgres;

