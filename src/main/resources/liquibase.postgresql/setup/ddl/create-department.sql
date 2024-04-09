create table if not exists public.department
(
    head_of_department_id bigint
    constraint fkcwifsb1iyobcln6aktp1j1jcp
    references public.member,
    id                    bigserial
    primary key,
    secretary_id          bigint
    constraint fk7xcwo82n6el9fji2wumw63c1v
    references public.member,
    short_name            varchar(10),
    name                  varchar(30)
    );

ALTER TABLE public.member
    ADD COLUMN department_id bigint;

ALTER TABLE public.member
    ADD CONSTRAINT fklmd4h7lh9acdyvi0xxbvsqrmk
        FOREIGN KEY (department_id)
            REFERENCES public.department (id);

alter table public.department
    owner to postgres;

