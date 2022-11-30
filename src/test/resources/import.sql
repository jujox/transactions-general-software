insert into accounts values ('IBAN001', 1000);
insert into accounts values ('IBAN002', 100);

insert into transactions (id, reference, account_iban, transaction_date, amount, fee) values (1001, 'TESTREF1', 'IBAN002', '2020-11-29T00:00:00.000Z', 10, 1);

insert into transactions (id, reference, account_iban, transaction_date, amount, fee) values (1002, 'TESTREF2', 'IBAN002', '2023-11-29T00:00:00.000Z', 20, 2);

