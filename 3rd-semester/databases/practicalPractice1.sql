IF OBJECT_ID('RoutesStations', 'U') IS NOT NULL
	DROP TABLE RoutesStations;

IF OBJECT_ID('Routes', 'U') IS NOT NULL
	DROP TABLE Routes;

IF OBJECT_ID('Stations', 'U') IS NOT NULL
	DROP TABLE Stations;

IF OBJECT_ID('Trains', 'U') IS NOT NULL
	DROP TABLE Trains;

IF OBJECT_ID('TrainTypes', 'U') IS NOT NULL
	DROP TABLE TrainTypes;

CREATE TABLE TrainTypes 
	(
	ttid INT PRIMARY KEY,
	type_name VARCHAR(50),
	type_description VARCHAR(250)
	)

CREATE TABLE Trains 
(
	tid INT PRIMARY KEY,
	train_name VARCHAR(50),
	ttid INT REFERENCES TrainTypes(ttid)
)

CREATE TABLE Stations
(
	sid INT PRIMARY KEY,
	station_name VARCHAR(50) UNIQUE
)

CREATE TABLE Routes
(
	rid INT PRIMARY KEY,
	route_name VARCHAR(50),
	tid INT REFERENCES Trains(tid)
)

CREATE TABLE RoutesStations
(
	rid INT REFERENCES Routes(rid),
	sid INT REFERENCES Stations(sid),
	arrival TIME,
	departure TIME,
	PRIMARY KEY(rid, sid)
)

INSERT INTO TrainTypes (
	ttid,
	type_name,
	type_description
) VALUES
	(1, 'TYPE1', 'First Type'),
	(2, 'TYPE2', 'Second Type'),
	(3, 'TYPE3', 'Third Type')

INSERT INTO Trains (
	tid,
	train_name,
	ttid
) VALUES
	(1, 'TRAIN1', 1),
	(2, 'TRAIN2', 1),
	(3, 'TRAIN3', 3)

INSERT INTO Stations (
	sid,
	station_name
) VALUES
	(1, 'STATION ONE'),
	(2, 'STATION TWO'),
	(3, 'STATION THREE'),
	(4, 'STATION FOUR')

INSERT INTO Routes (
	rid,
	route_name,
	tid
) VALUES
	(1, 'ROUTE ONE', 1),
	(2, 'ROUTE TWO', 2),
	(3, 'ROUTE THREE', 3)

INSERT INTO RoutesStations (
	rid,
	sid,
	arrival,
	departure
) VALUES
	(1, 1, '12:30', '15:30'),
	(2, 1, '14:00', '19:00')