INSERT INTO account (account_number, customer_number, account_name, account_type, currency, balance_date, opening_balance)
VALUES (1234563445, 150034523, 'Savings123US', 'Savings', 'USD', now(), '12335.67');
INSERT INTO account (account_number, customer_number, account_name, account_type, currency, balance_date, opening_balance)
VALUES (1234597922, 150034523, 'Savings566GB', 'Savings', 'GBP', now(), '344.33');
INSERT INTO account (account_number, customer_number, account_name, account_type, currency, balance_date, opening_balance)
VALUES (1234534523, 150034523, 'Cheque234AU', 'Cheque', 'AUD', now(), '1233.39');
INSERT INTO account (account_number, customer_number, account_name, account_type, currency, balance_date, opening_balance)
VALUES (1234555545, 150034534, 'Cheque788AU', 'Cheque', 'AUD', now(), '566.53');
INSERT INTO account (account_number, customer_number, account_name, account_type, currency, balance_date, opening_balance)
VALUES (1234590444, 150034566, 'Savings555US', 'Savings', 'USD', now(), '45654.99');
INSERT INTO account (account_number, customer_number, account_name, account_type, currency, balance_date, opening_balance)
VALUES (1234797966, 150034906, 'Savings890SG', 'Savings', 'SGD', now(), '55.44');
INSERT INTO account (account_number, customer_number, account_name, account_type, currency, balance_date, opening_balance)
VALUES (1234538944, 150034656, 'Cheque976AU', 'Cheque', 'AUD', now(), '5554.60');
INSERT INTO account (account_number, customer_number, account_name, account_type, currency, balance_date, opening_balance)
VALUES (1234553444, 150034455, 'Cheque673AU', 'Cheque', 'AUD', now(), '332233.11');

-- insert data into transaction table
INSERT INTO transaction (transaction_id, customer_number, account_number, date, credit, debit, operation, transaction_narrative)
VALUES (nextval('transaction_seq'), 150034523, 1234563445, now(), '4250.00', null, 'credit', 'Salary');
INSERT INTO transaction (transaction_id, customer_number, account_number, date, credit, debit, operation, transaction_narrative)
VALUES (nextval('transaction_seq'), 150034523, 1234563445, now(), null, '300', 'debit', 'ATM withdrawal');
INSERT INTO transaction (transaction_id, customer_number, account_number, date, credit, debit, operation, transaction_narrative)
VALUES (nextval('transaction_seq'), 150034523, 1234563445, now(), '125.00', null, 'credit', 'Deposit');
INSERT INTO transaction (transaction_id, customer_number, account_number, date, credit, debit, operation, transaction_narrative)
VALUES (nextval('transaction_seq'), 150034523, 1234563445, now(), null, '32.50', 'debit', 'Purchases');
INSERT INTO transaction (transaction_id, customer_number, account_number, date, credit, debit, operation, transaction_narrative)
VALUES (nextval('transaction_seq'), 150034523, 1234563445, now(), null, '127.32', 'debit', 'Woolworths');
INSERT INTO transaction (transaction_id, customer_number, account_number, date, credit, debit, operation, transaction_narrative)
VALUES (nextval('transaction_seq'), 150034523, 1234563445, now(), '63.25', null, 'debit', 'Shell');
INSERT INTO transaction (transaction_id, customer_number, account_number, date, credit, debit, operation, transaction_narrative)
VALUES (nextval('transaction_seq'), 150034523, 1234563445, now(), '88.45', null, 'debit', 'Aldi');