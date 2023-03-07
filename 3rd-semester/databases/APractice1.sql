IF OBJECT_ID('Transactions', 'U') IS NOT NULL
	DROP TABLE Transactions

IF OBJECT_ID('Cards', 'U') IS NOT NULL
	DROP TABLE Cards

IF OBJECT_ID('ATMs', 'U') IS NOT NULL
	DROP TABLE ATMs

IF OBJECT_ID('BankAccounts', 'U') IS NOT NULL
	DROP TABLE BankAccounts

IF OBJECT_ID('Customers', 'U') IS NOT NULL
	DROP TABLE Customers

CREATE TABLE ATMs (
	atm_id INT PRIMARY KEY,
	atm_location VARCHAR(50)
	)

CREATE TABLE Customers (
	customer_id INT PRIMARY KEY,
	customer_name VARCHAR(50),
	customer_dob DATE,
	)

CREATE TABLE BankAccounts (
	bank_id INT PRIMARY KEY,
	bank_iban VARCHAR(50),
	bank_balance INT,
	customer_id INT REFERENCES Customers(customer_id)
	)

CREATE TABLE Cards (
	card_id INT PRIMARY KEY,
	card_number VARCHAR(25),
	card_ccv INT,
	bank_id INT REFERENCES BankAccounts(bank_id)
	)

CREATE TABLE Transactions (
	transaction_id INT PRIMARY KEY,
	transaction_date DATE,
	transaction_time TIME,
	transaction_balance INT,
	atm_id INT REFERENCES ATMs(atm_id),
	card_id INT REFERENCES Cards(card_id)
	)

INSERT INTO ATMs(
	atm_id,
	atm_location
) VALUES
	(1, 'Loc1'),
	(2, 'Loc2'),
	(3, 'Loc3');

INSERT INTO Customers(
	customer_id,
	customer_name,
	customer_dob
) VALUES
	(1, 'Customer1', '24-feb-2022'),
	(2, 'Customer2', '07-dec-1990'),
	(3, 'Customer3', '13-mar-1890');

INSERT INTO BankAccounts(
	bank_id,
	bank_iban,
	bank_balance,
	customer_id
) VALUES
	(1, 'RO12', 50, 1),
	(2, 'RO24', 100, 1),
	(3, 'HU99', 250, 2),
	(4, 'GE11', 55, 3);

INSERT INTO Cards(
	card_id,
	card_number,
	card_ccv,
	bank_id
) VALUES
	(1, '1234', '234', 1),
	(2, '4567', '567', 1),
	(3, '1111', '111', 3),
	(4, '2222', '222', 2);

INSERT INTO Transactions(
	transaction_id,
	transaction_date,
	transaction_time,
	transaction_balance,
	atm_id,
	card_id
) VALUES
	(1, '01-jan-2023', '20:33', 5, 1, 1),
	(2, '05-jun-2012', '8:00', 15, 1, 3),
	(3, '05-nov-2012', '9:00', 1990, 2, 3),
	(4, '05-oct-2012', '10:00', 20, 3, 3),
	(5, '19-dec-1998', '15:45', 150, 2, 2);