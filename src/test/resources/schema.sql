DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;

CREATE TABLE transactions (
	id INTEGER NOT NULL AUTO_INCREMENT,
	reference VARCHAR(10),
	account_iban VARCHAR(24) NOT NULL,
	transaction_date  TIMESTAMP NOT NULL,
	amount FLOAT NOT NULL,
	fee FLOAT NOT NULL,
	description VARCHAR(1024)
);

CREATE TABLE accounts (
		account_iban VARCHAR(24) PRIMARY KEY NOT NULL,
		balance float NOT NULL
);