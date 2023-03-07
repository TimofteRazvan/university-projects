USE flowerShopDatabase;

INSERT INTO Manager (
	manager_id,
	manager_name,
	manager_phone_number,
	manager_salary
) VALUES 
	(100, 'Joan Stacey', '0770805482', 30000),
	(101, 'Linzi Knight', '0770584204', 31500),
	(102, NULL, '0770942743', 30000),
	(103, 'Martyn Reeves', '0774829313', 30500);

INSERT INTO Vendor (
	vendor_id,
	manager_id,
	vendor_name,
	vendor_phone_number,
	vendor_salary
) VALUES
	(200, 100, 'Grog Grognak', '0765839992', 21000),
	(201, 101, 'Greg Kilmore', '0778693342', 22600),
	(202, 101, 'Jalvin Kelvin', '0774445657', 20000),
	(203, 103, 'James Jonah', '0779989843', 19000),
	(204, 102, 'Carmen Karen', '0788493342', 23000),
	(205, 103, 'Cloyster McCoy', '0777777771', 35000);

INSERT INTO Vendor (
	vendor_id,
	manager_id,
	vendor_name,
	vendor_phone_number,
	vendor_salary
) VALUES
	(NULL, 100, 'Illegal Entree', '0999999999', 100000);

INSERT INTO Customer (
	customer_id,
	customer_name,
	customer_age
) VALUES
	(5000, 'Grog Grognak', 30),
	(5001, 'James Jonah', 20),
	(5002, 'Lombaric Lambasto', 55),
	(5003, 'Clementin Claurentiu', 71),
	(5004, 'Donald Biden', 99),
	(5005, 'Razvan Borza', 25),
	(5006, 'Linzi Knight', 19);

INSERT INTO Bouquet (
	bouquet_id,
	bouquet_occassion,
	bouquet_price
) VALUES
	(300, 'Wedding', 1000),
	(301, 'Funeral', 300),
	(302, 'Wedding', 1500),
	(303, 'Wedding', 750),
	(304, 'Wedding', 2000),
	(305, 'Casual', 150),
	(306, 'Casual', 100),
	(307, 'Birthday', 300),
	(308, 'Birthday', 250),
	(309, 'Funeral', 450),
	(310, 'Casual', 50),
	(311, 'Birthday', 2100),
	(312, 'Funeral', 3000),
	(313, 'Casual', 2000);

INSERT INTO Flower (
	flower_id,
	flower_species,
	flower_price,
	flower_color,
	flower_meaning
) VALUES
	(1000, 'Hibiscus', 5, 'Red', 'Health'),
	(1001, 'Tulip', 5, 'Red', 'Like'),
	(1002, 'Tulip', 5, 'Yellow', 'Luck'),
	(1003, 'Tulip', 5, 'Pink', 'Friendship'),
	(1004, 'Rose', 8, 'Red', 'Love'),
	(1005, 'Rose', 8, 'Yellow', 'Misfortune'),
	(1006, 'Rose', 8, 'Black', 'Mystery'),
	(1007, 'Rose', 8, 'Pink', 'Family'),
	(1008, 'Rose', 8, 'White', 'Marriage'),
	(1009, 'Crysanthemum', 10, 'Pink', 'Adoration'),
	(1010, 'Crysanthemum', 10, 'Yellow', 'Happiness'),
	(1011, 'Crysanthemum', 10, 'White', 'Purity'),
	(1012, 'Carnation', 4, 'Pink', 'Life'),
	(1013, 'Carnation', 4, 'White', 'Death'),
	(1023, 'Grass', 4, 'Green', NULL);

INSERT INTO Creates (
	flower_id,
	bouquet_id
) VALUES
	(1001, 304), (1002, 304), (1011, 304),
	(1002, 305), (1007, 305),
	(1013, 309), (1006, 309),
	(1009, 310), (1010, 310),
	(1010, 307), (1009, 307), (1012, 307),
	(1009, 308), (1012, 308),
	(1006, 306), (1007, 306),
	(1001, 300), (1004, 300), (1012, 300),
	(1007, 301), (1005, 301), (1013, 301),
	(1002, 303), (1011, 303),
	(1001, 302), (1002, 302), (1013, 302);

INSERT INTO Sells (
	vendor_id,
	bouquet_id
) VALUES
	(200, 301),
	(200, 300),
	(200, 304),
	(201, 302),
	(201, 303),
	(201, 310),
	(202, 309),
	(202, 308),
	(203, 305),
	(204, 306),
	(204, 307);

INSERT INTO Environment (
	environment_id,
	flower_id,
	environment_soil,
	environment_type,
	environment_boosts
) VALUES
	(2000, 1000, 'Clay', 'Pot', NULL),
	(2001, 1001, 'Sandy', 'Pot', 'Root'),
	(2002, 1002, 'Silt', 'Greenhouse', 'Root'),
	(2003, 1003, 'Loamy', 'Garden', 'Bloom'),
	(2004, 1004, 'Sandy', 'Greenhouse', 'Bloom'),
	(2005, 1005, 'Sandy', 'Garden', 'All'),
	(2006, 1006, 'Loamy', 'Pot', NULL),
	(2007, 1007, 'Silt', 'Pot', NULL),
	(2008, 1008, 'Clay', 'Greenhouse', 'Nitrogen'),
	(2009, 1009, 'Clay', 'Greenhouse', 'Bloom'),
	(2010, 1010, 'Silt', 'Greenhouse', 'Nitrogen'),
	(2011, 1011, 'Loamy', 'Garden', 'Root'),
	(2012, 1012, 'Silt', 'Garden', NULL);

INSERT INTO Special_Collection (
	collection_id,
	collection_occassion
) VALUES
	(0, 'Summer'),
	(1, 'Autumn'),
	(2, 'Winter'),
	(3, 'Spring'),
	(4, 'Christmas'),
	(5, 'Valentines'),
	(6, 'St.Patrick'),
	(7, 'Wedding'),
	(8, 'Funeral'),
	(9, 'Special');

INSERT INTO Customer_of_Collection (
	customer_id,
	collection_id,
	favourite_collection
) VALUES
	(5000, 0, 'Special'),
	(5000, 3, 'Special'),
	(5001, 3, 'Special'),
	(5003, 5, 'Spring'),
	(5002, 9, 'St.Patrick'),
	(5003, 3, 'Spring'),
	(5004, 5, 'Valentines'),
	(5005, 6, 'Autumn'),
	(5006, 8, 'Christmas');

INSERT INTO Flower_in_Collection (
	flower_id,
	collection_id
) VALUES
	(1000, 0),
	(1001, 1),
	(1002, 3),
	(1003, 4),
	(1004, 2),
	(1005, 0),
	(1013, 0),
	(1006, 1),
	(1007, 7),
	(1008, 8),
	(1009, 9),
	(1010, 2),
	(1011, 3),
	(1012, 6),
	(1012, 7),
	(1012, 3),
	(1011, 9),
	(1011, 1),
	(1000, 5),
	(1000, 9),
	(1001, 9),
	(1001, 5),
	(1002, 5),
	(1002, 8),
	(1004, 7),
	(1004, 9),
	(1013, 3);

SELECT *
FROM Manager;
UPDATE Manager SET manager_name='Kyrie Kalnson' WHERE manager_name IS NULL
SELECT *
FROM Manager;
-- '=', 'where', 'is null'
-- UPDATE 1

SELECT *
FROM Bouquet;
UPDATE Bouquet SET bouquet_price=bouquet_price+100 WHERE bouquet_occassion IN ('Wedding', 'Funeral') 
AND bouquet_price BETWEEN 400 AND 1200
SELECT *
FROM Bouquet;
-- 'and', 'in', 'between'
-- UPDATE 2

SELECT *
FROM Vendor;
UPDATE Vendor SET vendor_salary=29999 WHERE vendor_id=203
SELECT *
FROM Vendor;
-- UPDATE 3

SELECT *
FROM Environment;
UPDATE Environment SET environment_type='Garden' WHERE environment_boosts='Root'
SELECT *
FROM Environment;

INSERT INTO Flower (
	flower_id,
	flower_species,
	flower_price,
	flower_color,
	flower_meaning
) VALUES
	(1020, 'Glories', 10, 'Rainbow', 'Victory');

DELETE
FROM Flower
WHERE flower_color LIKE '%Rainbow%' 
-- 'LIKE'
-- DELETE 1

INSERT INTO Bouquet (
	bouquet_id,
	bouquet_occassion,
	bouquet_price
) VALUES
	(350, 'Deletion', 5),
	(351, 'Deletion', 100000);

DELETE
FROM Bouquet
WHERE bouquet_price < 10 OR bouquet_price > 99999;
-- '<', '>', 'OR'
-- DELETE 2

--------------------------------------------------------
-- a. 2 queries with the union operation; use UNION [ALL] and OR;

SELECT V1.vendor_name 
FROM Vendor V1
WHERE V1.manager_id = 101
UNION
SELECT V2.vendor_name
FROM Vendor V2
WHERE V2.manager_id = 101
-- 'UNION'

SELECT DISTINCT  M.manager_name
FROM Manager M, Vendor V
WHERE (M.manager_id = V.manager_id AND V.vendor_salary < 25000) OR (M.manager_id = V.manager_id AND V.vendor_salary > 30000)
-- 'OR', 'DISTINCT'

------------------------------------------------------
-- b. 2 queries with the intersection operation; use INTERSECT and IN;

SELECT V.vendor_name
FROM Vendor V
INTERSECT
SELECT C.customer_name 
FROM Customer C
-- 'INTERSECT'

SELECT M.manager_name
FROM Manager M
WHERE M.manager_name IN (SELECT C.customer_name FROM Customer C)
-- 'IN'

--------------------------------------------------------
-- c. 2 queries with the difference operation; use EXCEPT and NOT IN;

SELECT C.customer_name
FROM Customer C
WHERE C.customer_age < 40
EXCEPT
SELECT V.vendor_name
FROM Vendor V
-- 'EXCEPT'

SELECT C.customer_name
FROM Customer C
WHERE (C.customer_age > 25) AND C.customer_name NOT IN (SELECT M.manager_name FROM Manager M)
-- 'NOT IN'

----------------------------------------------------------
-- d. 4 queries with INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN (one query per operator); one query will join 
-- at least 3 tables, while another one will join at least two many-to-many relationships;

SELECT manager_name, vendor_name
FROM Manager M INNER JOIN Vendor V on V.manager_id = M.[manager_id]
-- INNER JOIN

SELECT V.vendor_name, S.bouquet_id, B.bouquet_price
FROM Vendor V
LEFT JOIN Sells S ON V.vendor_id = S.vendor_id
LEFT JOIN Bouquet B ON B.bouquet_id = S.bouquet_id
-- LEFT JOIN
-- prints the vendor who sells a bouquet and the price with which said bouquet is sold
-- joins 3 tables

SELECT B.bouquet_occassion, F.flower_meaning
FROM Bouquet B
RIGHT JOIN Creates C ON B.bouquet_id = C.bouquet_id
RIGHT JOIN Flower F ON F.flower_id = C.flower_id
-- RIGHT JOIN
-- prints the bouquet occassion and what the usual meanings are for each bouquet databased

SELECT V.vendor_name, B.bouquet_price, F.flower_price
FROM Vendor V
FULL JOIN Sells S ON V.vendor_id = S.vendor_id
FULL JOIN Bouquet B ON B.bouquet_id = S.bouquet_id
FULL JOIN Creates C ON C.bouquet_id = B.bouquet_id 
FULL JOIN Flower F ON F.flower_id = C.flower_id
-- FULL JOIN
-- 2 many-to-many

----------------------------------------------------------------------------------
-- e. 2 queries with the IN operator and a subquery in the WHERE clause; in at least one case, the subquery must include a 
-- subquery in its own WHERE clause;

INSERT INTO Vendor (
	vendor_id,
	manager_id,
	vendor_name,
	vendor_phone_number,
	vendor_salary
) VALUES
	(217, 103, 'Testy McTestson', '0777000777', 20000);

SELECT V.vendor_name
FROM Vendor V
WHERE V.vendor_id IN (
	SELECT s.vendor_id
	FROM Sells S
	WHERE s.bouquet_id IN (
		SELECT B.bouquet_id
		FROM Bouquet B
	)
)
-- All vendors who have sold at least one bouquet

SELECT C.customer_name
FROM Customer C
WHERE C.customer_id IN (
	SELECT CC.customer_id
	FROM Customer_of_Collection CC
	WHERE CC.favourite_collection LIKE '%Spring%' AND CC.collection_id IN (
		SELECT FC.collection_id
		FROM Flower_in_Collection FC
		WHERE FC.flower_id IN (
			SELECT F.flower_id
			FROM Flower F
			WHERE F.flower_color LIKE '%White%'
		)
	)
)
-- All customers whose favourite collection is spring and who have bought white flowers

---------------------------------------------------------------------
-- f. 2 queries with the EXISTS operator and a subquery in the WHERE clause

INSERT INTO Flower (
	flower_id,
	flower_species,
	flower_price,
	flower_color,
	flower_meaning
) VALUES
	(1014, 'Snowbell', 7, 'White', 'Spring');

SELECT F.flower_species
FROM Flower F
WHERE EXISTS(
	SELECT *
	FROM Flower F1 
	INNER jOIN Flower_in_Collection FC ON F1.flower_id = FC.flower_id
	INNER JOIN Special_Collection SC ON SC.collection_id = FC.collection_id
	WHERE F1.flower_id = F.flower_id
)
-- Prints only the flowers that are in a collection

SELECT C.customer_name 
FROM Customer C
WHERE EXISTS(
	SELECT * 
	FROM Vendor V
	WHERE C.customer_name = V.vendor_name
)
-- Prints only the customers who are also vendors

-------------------------------------------------------------------------
-- g. 2 queries with a subquery in the FROM clause

SELECT v.vendor_name, v.vendor_salary + 167 AS bonus
FROM (
	SELECT *
	FROM Vendor V
	WHERE NOT V.vendor_salary >= 22000
)v WHERE v.vendor_id IN (
	SELECT S.vendor_id
	FROM Sells S
) ORDER BY bonus ASC
-- Bonuses to vendors who have actually sold anything and are paid less than 22000

SELECT f.flower_species, f.flower_price + 4 AS inflation
FROM (
	SELECT *
	FROM Flower F
	WHERE NOT F.flower_price >= 10
)f WHERE f.flower_id IN (
	SELECT FC.flower_id
	FROM Flower_in_Collection FC
) ORDER BY inflation DESC
-- Inflation on flowers that cost below 10 and are in collections

------------------------------------------------------------------------
-- h. 4 queries with the GROUP BY clause, 3 of which also contain the HAVING clause; 2 of the latter will also have a subquery 
-- in the HAVING clause; use the aggregation operators: COUNT, SUM, AVG, MIN, MAX

SELECT B.bouquet_id, B.bouquet_occassion, COUNT(*) AS flowers
FROM Bouquet B 
INNER JOIN Creates C ON C.bouquet_id = B.bouquet_id
INNER JOIN Flower F ON F.flower_id = C.flower_id
GROUP BY B.bouquet_id, B.bouquet_occassion
HAVING COUNT(*) = (
	SELECT MAX (SEC.CO)
	FROM (
		SELECT COUNT(*) CO
		FROM Bouquet B1 
		INNER JOIN Creates C1 ON B1.bouquet_id = C1.bouquet_id 
		INNER JOIN Flower F1 ON C1.flower_id = F1.flower_id
		GROUP BY B1.bouquet_id, B1.bouquet_occassion
	)SEC
)
-- Prints bouquets with most flowers in them
-- Contains HAVING, Subquery in HAVING, + COUNT & MAX

SELECT F.flower_color, F.flower_species, SUM(F.flower_price) AS total_price
FROM Flower F
GROUP BY F.flower_color, F.flower_species
HAVING SUM(F.flower_price) = (
	SELECT MIN(T.s)
	FROM (
		SELECT SUM(F2.flower_price) s
		FROM Flower F2
		GROUP BY F2.flower_color, F2.flower_species
	)T
)
-- Prints lowest priced flowers (species and colour) for making cheap bouquets
-- Contains HAVING, Subquery in HAVING, + MIN & SUM

SELECT F.flower_color, AVG(F.flower_price) AS average_price
FROM Flower F
GROUP BY F.flower_color
-- Prints average price of flowers based on colour
-- Contains AVG

SELECT COC.favourite_collection, COUNT(*) as customers
FROM Customer_of_Collection COC
GROUP BY COC.favourite_collection
HAVING COUNT(*) > 1
-- Prints favourite collections with multiple customers
-- Contains HAVING, COUNT

------------------------------------------------------------------------
-- i. 4 queries using ANY and ALL to introduce a subquery in the WHERE clause (2/operator); 
-- rewrite 2 of them with aggregation operators, and the other 2 with IN/[NOT] IN

SELECT TOP 5 V.*
FROM Vendor V
WHERE V.vendor_salary > ANY (
	SELECT V2.vendor_salary
	FROM Vendor V2)
ORDER BY V.vendor_salary DESC
-- rewritten with aggr.
-- MIN instead of ANY
SELECT TOP 5 V.*
FROM Vendor V
WHERE V.vendor_salary > (
	SELECT MIN(V2.vendor_salary)
	FROM Vendor V2)
ORDER BY V.vendor_salary DESC
-- Prints TOP 5 highest paid vendors

SELECT TOP 3 B.*
FROM Bouquet B
WHERE B.bouquet_price >= ALL (
	SELECT B2.bouquet_price
	FROM Bouquet B2
	WHERE B2.bouquet_occassion LIKE '%Wedding%')
ORDER BY B.bouquet_price ASC
-- rewritten with aggr.
-- MAX instead of ALL
SELECT TOP 3 B.*
FROM Bouquet B
WHERE B.bouquet_price >= (
	SELECT MAX(B2.bouquet_price)
	FROM Bouquet B2
	WHERE B2.bouquet_occassion LIKE '%Wedding%')
ORDER BY B.bouquet_price ASC

SELECT COC.favourite_collection
FROM Customer_of_Collection COC
WHERE COC.customer_id = ANY (
	SELECT C.customer_id
	FROM Customer C
	WHERE C.customer_age >= 18 AND C.customer_age <= 30)
-- rewritten with IN
SELECT COC.favourite_collection
FROM Customer_of_Collection COC
WHERE COC.customer_id IN(
	SELECT C.customer_id
	FROM Customer C
	WHERE C.customer_age >= 18 AND C.customer_age <= 30)
-- Prints the favourite collections of young adult customers

SELECT F.*
FROM Flower F
WHERE F.flower_id <> ALL (
	SELECT C.flower_id
	FROM Creates C)
-- rewritten with NOT IN
SELECT F.*
FROM Flower F
WHERE F.flower_id NOT IN (
	SELECT C.flower_id
	FROM Creates C)
-- Prints all flowers not used in any bouquets



DROP TABLE Customer_of_Collection
DROP TABLE Customer
DROP TABLE Flower_in_Collection
DROP TABLE Special_Collection
DROP TABLE Environment
DROP TABLE Creates
DROP TABLE Sells
DROP TABLE Bouquet
DROP TABLE Vendor
DROP TABLE Flower
DROP TABLE Manager


DROP TABLE TestRunTables
DROP TABLE TestRunViews
DROP TABLE TestViews
DROP TABLE TestTables
DROP TABLE Views
DROP TABLE Tables
DROP TABLE Tests
DROP TABLE TestRuns