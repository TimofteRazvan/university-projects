IF OBJECT_ID('ShoesInShops', 'U') IS NOT NULL
	DROP TABLE ShoesInShops

IF OBJECT_ID('WomenBuyShoes', 'U') IS NOT NULL
	DROP TABLE WomenBuyShoes

IF OBJECT_ID('Shoes', 'U') IS NOT NULL
	DROP TABLE Shoes

IF OBJECT_ID('ShoeModels', 'U') IS NOT NULL
	DROP TABLE ShoeModels

IF OBJECT_ID('Women', 'U') IS NOT NULL
	DROP TABLE Women

IF OBJECT_ID('PresentationShops', 'U') IS NOT NULL
	DROP TABLE PresentationShops

CREATE TABLE PresentationShops
(
	shop_id INT PRIMARY KEY,
	shop_name VARCHAR(50),
	shop_city VARCHAR(50)
)

CREATE TABLE Women
(
	woman_id INT PRIMARY KEY,
	woman_name VARCHAR(50),
	woman_budget INT
)

CREATE TABLE ShoeModels
(
	model_id INT PRIMARY KEY,
	model_name VARCHAR(50),
	model_season VARCHAR(20)
)

CREATE TABLE Shoes
(
	shoe_id INT PRIMARY KEY,
	shoe_price INT,
	model_id INT REFERENCES ShoeModels(model_id)
)

CREATE TABLE ShoesInShops
(
	shoe_id INT REFERENCES Shoes(shoe_id),
	shop_id INT REFERENCES PresentationShops(shop_id),
	shoes_available INT,
	PRIMARY KEY(shoe_id, shop_id)
)

CREATE TABLE WomenBuyShoes
(
	woman_id INT REFERENCES Women(woman_id),
	shoe_id INT REFERENCES Shoes(shoe_id),
	shoes_bought INT,
	spent_amount INT,
	PRIMARY KEY(woman_id, shoe_id)
)

INSERT INTO PresentationShops
(	
	shop_id,
	shop_name,
	shop_city
) VALUES
	(1, 'Shop1', 'City1'),
	(2, 'Shop2', 'City2'),
	(3, 'Shop3', 'City3');

INSERT INTO Women
(
	woman_id,
	woman_name,
	woman_budget
) VALUES
	(1, 'Woman1', 100),
	(2, 'Woman2', 200),
	(3, 'Woman3', 50);

INSERT INTO ShoeModels
(
	model_id,
	model_name,
	model_season
) VALUES
	(1, 'Model1', 'Season1'),
	(2, 'Model2', 'Season2'),
	(3, 'Model3', 'Season3');

INSERT INTO Shoes
(
	shoe_id,
	shoe_price,
	model_id
) VALUES
	(1, 25, 1),
	(2, 100, 2),
	(3, 200, 3),
	(4, 50, 1);

INSERT INTO ShoesInShops
(
	shoe_id,
	shop_id,
	shoes_available
) VALUES	
	(1, 1, 150),
	(1, 2, 50),
	(1, 3, 500),
	(2, 1, 75),
	(2, 2, 150),
	(3, 2, 75),
	(3, 3, 1000);

INSERT INTO WomenBuyShoes
(
	woman_id,
	shoe_id,
	shoes_bought,
	spent_amount
) VALUES
	(1, 2, 20, 100),
	(2, 3, 21, 214),
	(3, 1, 53, 316);
