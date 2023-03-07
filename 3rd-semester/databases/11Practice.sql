IF OBJECT_ID('OrderedCakes', 'U') IS NOT NULL
	DROP TABLE OrderedCakes

IF OBJECT_ID('Orders', 'U') IS NOT NULL
	DROP TABLE Orders

IF OBJECT_ID('Cakes', 'U') IS NOT NULL
	DROP TABLE Cakes

IF OBJECT_ID('ChefSpecializations', 'U') IS NOT NULL
	DROP TABLE ChefSpecializations

IF OBJECT_ID('CakeTypes', 'U') IS NOT NULL
	DROP TABLE CakeTypes

IF OBJECT_ID('Chefs', 'U') IS NOT NULL
	DROP TABLE Chefs

CREATE TABLE Chefs
(
	chefId INT PRIMARY KEY,
	chefName VARCHAR(50),
	chefGender VARCHAR(20),
	chefDob DATE
)

CREATE TABLE CakeTypes
(
	typeId INT PRIMARY KEY,
	typeName VARCHAR(50),
	typeDescription VARCHAR(100),
)

CREATE TABLE ChefSpecializations
(
	chefId INT,
	typeId INT,
	PRIMARY KEY (chefId, typeId)
)

CREATE TABLE Cakes
(
	cakeId INT PRIMARY KEY,
	cakeName VARCHAR(50),
	cakeShape VARCHAR(20),
	cakeWeight INT,
	cakePrice INT,
	typeId INT REFERENCES CakeTypes(typeId)
)

CREATE TABLE Orders
(
	orderId INT PRIMARY KEY,
	orderDate DATETIME
)

CREATE TABLE OrderedCakes
(
	orderId INT,
	cakeId INT,
	pieces INT,
	PRIMARY KEY (orderId, cakeId)
)

INSERT INTO CakeTypes
(
	typeId,
	typeName,
	typeDescription
) VALUES (1, 'Cheesecake', 'Description1');

INSERT INTO Chefs
(
	chefId,
	chefName,
	chefGender,
	chefDob
) VALUES (1, 'Razvan', 'Male', '20020526'), (2, 'Robert', 'Male', '20020514');

INSERT INTO ChefSpecializations
(
	chefId,
	typeId
) VALUES (1, 1);

INSERT INTO Cakes
(
	cakeId,
	cakeName,
	cakeShape,
	cakeWeight,
	cakePrice,
	typeId
) VALUES (1, 'Cheesecake1', 'Square',  50, 2500, 1);