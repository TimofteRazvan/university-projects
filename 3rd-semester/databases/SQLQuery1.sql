USE flowerShopDatabase;

CREATE TABLE Manager(
	manager_id INT PRIMARY KEY NOT NULL,
	manager_name VARCHAR(50),
	manager_phone_number VARCHAR(10),
	manager_salary INT
);
	
CREATE TABLE Flower(
	flower_id INT PRIMARY KEY NOT NULL,
	flower_species VARCHAR(50),
	flower_price INT,
	flower_color VARCHAR(50),
	flower_meaning VARCHAR(50)
);

--1(manager):n(vendors)
CREATE TABLE Vendor(
	vendor_id INT PRIMARY KEY NOT NULL,
	manager_id INT FOREIGN KEY REFERENCES Manager(manager_id) ON DELETE CASCADE ON UPDATE CASCADE,
	vendor_name VARCHAR(50),
	vendor_phone_number VARCHAR(10),
	vendor_salary INT
);

CREATE TABLE Bouquet(
	bouquet_id INT PRIMARY KEY NOT NULL,
	bouquet_occassion VARCHAR(50),
	bouquet_price INT
);

--m(vendors):n(bouquets)
CREATE TABLE Sells(
	vendor_id INT FOREIGN KEY REFERENCES Vendor(vendor_id) ON DELETE CASCADE ON UPDATE CASCADE,
	bouquet_id INT FOREIGN KEY REFERENCES Bouquet(bouquet_id) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (vendor_id, bouquet_id)
);

--m(flowers):n(bouquets)
CREATE TABLE Creates(
	flower_id INT FOREIGN KEY REFERENCES Flower(flower_id) ON DELETE CASCADE ON UPDATE CASCADE,
	bouquet_id INT FOREIGN KEY REFERENCES Bouquet(bouquet_id) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (flower_id, bouquet_id)
);

--1(environment):n(flowers)
CREATE TABLE Environment(
	environment_id INT PRIMARY KEY NOT NULL,
	flower_id INT FOREIGN KEY REFERENCES Flower(flower_id) ON DELETE CASCADE ON UPDATE CASCADE,
	environment_soil VARCHAR(50),
	environment_type VARCHAR(50),
	environment_boosts VARCHAR(50)
);

CREATE TABLE Special_Collection (
	collection_id INT PRIMARY KEY NOT NULL,
	collection_occassion VARCHAR(100)
);

--m(flowers):n(collections)
CREATE TABLE Flower_in_Collection (
	collection_id INT FOREIGN KEY REFERENCES Special_Collection(collection_id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	flower_id INT FOREIGN KEY REFERENCES Flower(flower_id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	PRIMARY KEY (collection_id, flower_id)
);

CREATE TABLE Customer(
	customer_id INT PRIMARY KEY NOT NULL,
	customer_name VARCHAR(50),
	customer_age INT
);

--m(customers):n(collections)
CREATE TABLE Customer_of_Collection(
	customer_id INT FOREIGN KEY REFERENCES Customer(customer_id) ON DELETE CASCADE ON UPDATE CASCADE,
	collection_id INT FOREIGN KEY REFERENCES Special_Collection(collection_id) ON DELETE CASCADE ON UPDATE CASCADE,
	favourite_collection VARCHAR(50),
	PRIMARY KEY (customer_id, collection_id)
);
