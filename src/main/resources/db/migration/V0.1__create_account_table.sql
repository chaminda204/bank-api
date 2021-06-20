create table account
(
    account_number  numeric primary key,
    customer_number numeric not null,
    account_name    varchar(50),
    account_type    varchar(20),
    currency        varchar(4),
    balance_date     date,
    opening_balance  numeric(10, 2)
);

create sequence if not exists account_seq start with 1010101111 increment by 50;

create index if not exists  customer_id_idx on account (customer_number);