create table event
(
    id                    uuid         not null
        primary key,
    title                 varchar(255) not null,
    description           text,
    reward_type           varchar(20)  not null,
    reward_quantity       integer      not null,
    max_attempts_per_user integer      not null,
    start_at              timestamp    not null,
    end_at                timestamp    not null,
    status                varchar(20)  not null,
    created_at            timestamp    not null,
    updated_at            timestamp    not null
);

alter table event
    owner to root;
