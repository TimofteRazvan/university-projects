USE examDatabase

IF OBJECT_ID('PCsInService', 'U') IS NOT NULL
	DROP TABLE PCsInService

IF OBJECT_ID('PCs', 'U') IS NOT NULL
	DROP TABLE PCs

IF OBJECT_ID('PCFactories', 'U') IS NOT NULL
	DROP TABLE PCFactories

IF OBJECT_ID('PCServices', 'U') IS NOT NULL
	DROP TABLE PCServices

IF OBJECT_ID('Locations', 'U') IS NOT NULL
	DROP TABLE Locations

IF OBJECT_ID('PCModels', 'U') IS NOT NULL
	DROP TABLE PCModels

CREATE TABLE PCModels
(
	model_id INT PRIMARY KEY,
	model_name VARCHAR(50),
	model_description VARCHAR(50),
	model_date DATE
)

CREATE TABLE Locations
(
	location_id INT PRIMARY KEY,
	location_name VARCHAR(50),
	location_address VARCHAR(100) UNIQUE,
	location_contact VARCHAR(50)
)

CREATE TABLE PCServices
(
	service_id INT PRIMARY KEY,
	service_name VARCHAR(50),
	location_id INT REFERENCES Locations(location_id)
)

CREATE TABLE PCFactories
(
	factory_id INT PRIMARY KEY,
	service_id INT REFERENCES PCServices(service_id)
)

CREATE TABLE PCs
(
	pc_id INT PRIMARY KEY,
	pc_name VARCHAR(50),
	model_id INT REFERENCES PCModels(model_id),
	factory_id INT REFERENCES PCFactories(factory_id)
)

CREATE TABLE PCsInService
(
	pc_id INT REFERENCES PCs(pc_id),
	service_id INT REFERENCES PCServices(service_id),
	arrival_time DATETIME,
	repair_time DATETIME,
	PRIMARY KEY (pc_id, service_id)
)

INSERT INTO PCModels
(
	model_id,
	model_name,
	model_description,
	model_date
) VALUES
(1, 'NAME', 'DESC', '19900101'),
(2, 'NAME2', 'DESC2', '19900101');

INSERT INTO Locations
(
	location_id,
	location_name,
	location_address,
	location_contact
) VALUES 
	(1, 'NAME', 'ADDRESS', 'CONTACT'),
	(2, 'NAME2', 'ADDRESS2', 'CONTACT2');

INSERT INTO PCServices
(
	service_id,
	service_name,
	location_id
) VALUES
(1, 'NAME', 1),
(2, 'NAME2', 2);

INSERT INTO PCFactories
(
	factory_id,
	service_id
) VALUES 
	(1, 1),
	(2, 2);

INSERT INTO PCs
(
	pc_id,
	pc_name,
	model_id,
	factory_id
) VALUES
	(1, 'NAME', 1, 1),
	(2, 'NAME2', 1, 2),
	(3, 'NAME3', 2, 1),
	(4, 'NAME4', 2, 1);

INSERT INTO PCsInService
(
	pc_id,
	service_id,
	arrival_time,
	repair_time
) VALUES
	(1, 1, '20220101 12:00:00 am', '20230105 12:00:00 am'),
	(2, 1, '20220101 12:00:00 am', '20230105 12:00:00 am'),
	(3, 2, '20220101 12:00:00 am', '20240101 12:00:00 am');

SELECT * FROM PCModels
SELECT * FROM Locations
SELECT * FROM PCFactories
SELECT * FROM PCServices
SELECT * FROM PCs
SELECT * FROM PCsInService

------------------------------------------------------------------------------------------------------

GO
CREATE OR ALTER PROCEDURE addPCToService @factory_id INT, @pc_id INT AS
BEGIN
	DECLARE @pc_model INT
    SELECT @pc_model=P.model_id FROM PCs P WHERE P.pc_id=@pc_id
	
	DECLARE @pcNumber INT
	--SELECT @pcNumber = COUNT(*)

	DECLARE @pcService INT

	SELECT @pcService=PF.service_id FROM PCFactories PF
	WHERE PF.factory_id = @factory_id

	SELECT @pcNumber=COUNT(*)
	FROM (SELECT P.model_id, P.pc_id
		FROM PCs P
		INNER JOIN
		(SELECT PS.pc_id
		FROM PCsInService PS) PS2
		ON P.pc_id = PS2.pc_id AND P.model_id = @pc_model) P1

	IF @pcNumber < 2
	BEGIN
		INSERT INTO PCsInService
		(pc_id, service_id, arrival_time, repair_time)
		VALUES (@pc_id, @pcService, null, null)
	END
END
GO

EXEC addPCToService @factory_id=1, @pc_id=3

SELECT * FROM PCsInService

------------------------------------------------------------------------------------------------------

GO
CREATE OR ALTER VIEW allPCsScheduled AS
	SELECT PS.pc_id
	FROM PCsInService PS
	WHERE PS.repair_time > '20230101 12:00:00 AM' AND PS.repair_time < '20230201 12:00:00 AM'
GO

SELECT * FROM allPCsScheduled

------------------------------------------------------------------------------------------------------

GO
CREATE OR ALTER FUNCTION allServicesMoreThanT (@T INT)
RETURNS TABLE AS
RETURN
	SELECT S.service_name
	FROM PCServices S
	INNER JOIN
	(SELECT PS.service_id
	FROM PCsInService PS
	GROUP BY PS.service_id
	HAVING COUNT(*) > @T) S1
	ON S1.service_id = S.service_id
GO

SELECT * FROM allServicesMoreThanT(0)