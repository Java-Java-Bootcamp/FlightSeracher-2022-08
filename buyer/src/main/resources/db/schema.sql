create table if not exists buyer
(
    id         serial primary key,
    first_name varchar(64),
    is_bot     boolean,
    last_name  varchar(64),
    user_name  varchar(64)
);

create table if not exists ticket
(
    id                serial primary key,
    depart_city       varchar(32),
    arrive_city       varchar(32),
    site              varchar(32),
    price             bigint,
    depart_date       timestamptz,
    return_date       timestamptz,
    number_of_changes int,
    duration          bigint,
    distance          bigint,
    buyer_ticket_id   varchar(16) references buyer (id)
);



