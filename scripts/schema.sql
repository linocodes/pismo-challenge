create table account
(
    account_id     bigint auto_increment primary key,
    document_number varchar(255) not null
);

create table transaction
(
    transaction_id bigint auto_increment primary key,
    account_id     bigint         not null,
    amount         decimal(10, 2) not null,
    event_date     datetime       not null,
    type_operation TINYINT        not null
);

ALTER TABLE transaction
    ADD CONSTRAINT account_transaction
        FOREIGN KEY(account_id)
            REFERENCES account(account_id);