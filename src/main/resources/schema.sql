create table messages (
    id uuid primary key,
    text text,
    created_at timestamp default now()
);