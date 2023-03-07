IF OBJECT_ID('Tc', 'U') IS NOT NULL
	DROP TABLE Tc;

IF OBJECT_ID('Tb', 'U') IS NOT NULL
	DROP TABLE Tb;

IF OBJECT_ID('Ta', 'U') IS NOT NULL
	DROP TABLE Ta;

-- Tables creation
CREATE TABLE Ta (
	aid INT PRIMARY KEY,
	a2 INT UNIQUE,
	a3 INT
)

CREATE TABLE Tb (
	bid INT PRIMARY KEY,
	b2 INT
)

CREATE TABLE Tc (
	cid INT PRIMARY KEY,
	aid INT FOREIGN KEY REFERENCES Ta(aid),
	bid INT FOREIGN KEY REFERENCES Tb(bid)
)

INSERT INTO Ta (
	aid,
	a2,
	a3
) VALUES
	(1, 100, 1000),
	(2, 101, 1001),
	(3, 102, 1002),
	(4, 103, 1003),
	(5, 104, 1004);

INSERT INTO Tb (
	bid,
	b2
) VALUES
	(1, 10),
	(2, 11),
	(3, 12),
	(4, 13);

INSERT INTO Tc (
	cid,
	aid,
	bid
) VALUES
	(1, 1, 1),
	(2, 2, 1),
	(3, 3, 3),
	(4, 4, 4),
	(5, 5, 2);

SELECT *
FROM Ta

SELECT *
FROM Tb

SELECT *
FROM Tc

-- a) Write queries on Ta such that their execution plans contain the following operators:
-- clustered index scan - scan the entire table 
-- Completion time: 2022-12-21T17:09:45.5352280+02:00
SELECT *
FROM Ta

-- clustered index seek - return a specific subset of rows from a clustered index
-- Completion time: 2022-12-21T17:09:36.2168356+02:00
SELECT *
FROM Ta
WHERE aid = 1

-- nonclustered index scan - scan the entire nonclustered index
-- Completion time: 2022-12-21T17:09:23.9989190+02:00
SELECT a2
FROM Ta
ORDER BY a2

-- nonclustered index seek - specific subset of rows from an unclustered index
-- Completion time: 2022-12-21T17:09:14.0081701+02:00
SELECT a2
FROM Ta
WHERE a2 BETWEEN 100 AND 103

-- key lookup - basically an index seek but doesn't have all the columns, so it goes back
-- Completion time: 2022-12-21T17:09:02.0726019+02:00
SELECT a3, a2
FROM Ta
WHERE a2 = 100

-- b) Write a query on table Tb with a WHERE clause of the form WHERE b2 = value and analyze its execution plan. Create a nonclustered index that can speed up the query. Examine the execution plan again.
SELECT *
FROM Tb
WHERE b2 = 12
-- 2022-12-21T17:04:56.8078723+02:00

DROP INDEX Tb_b2_index ON Tb
CREATE NONCLUSTERED INDEX Tb_b2_index ON Tb(b2)
-- 2022-12-21T17:07:16.7796930+02:00

-- c) Create a view that joins at least 2 tables. Check whether existing indexes are helpful; if not, reassess existing indexes / examine the cardinality of the tables.

GO
CREATE OR ALTER VIEW View1 AS
	SELECT A.aid, A.a3, B.bid, B.b2, C.cid
	FROM Tc C
	INNER JOIN Ta A ON A.aid = C.aid
	INNER JOIN Tb B ON B.bid = C.bid
	WHERE B.b2 > 10 AND A.a3 < 1003
GO

SELECT *
FROM View1

DROP INDEX Ta_a3_index ON Ta
CREATE NONCLUSTERED INDEX Ta_a3_index ON Ta(a3)

DROP INDEX Tc_index ON Tc
CREATE NONCLUSTERED INDEX Tc_index ON Tc(aid, bid)