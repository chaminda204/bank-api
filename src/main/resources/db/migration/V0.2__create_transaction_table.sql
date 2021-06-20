create table transaction
(
    transaction_id        varchar(50) primary key,
    account_number        numeric not null,
    customer_number       numeric not null,
    date                  timestamp,
    credit                numeric(10, 2),
    debit                 numeric(10, 2),
    operation             varchar(30),
    transaction_narrative varchar(30)
);

create sequence if not exists transaction_seq start with 10000000000 increment by 50;

create index if not exists  transaction_id_idx on transaction (account_number);