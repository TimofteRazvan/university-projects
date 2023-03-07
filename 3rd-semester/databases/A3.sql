USE flowerShopDatabase

-- MODIFY COLUMN
GO
CREATE OR ALTER PROCEDURE setVendorSalaryDecimal
AS
    ALTER TABLE Vendor ALTER COLUMN vendor_salary DECIMAL(4,2)
GO

CREATE OR ALTER PROCEDURE setVendorSalaryInteger
AS
	ALTER TABLE Vendor ALTER COLUMN vendor_salary INT
GO

-- ADD/REMOVE COLUMN
CREATE OR ALTER PROCEDURE addFakeFlowerCheck
AS
	ALTER TABLE Flower ADD isFake BIT
GO

CREATE OR ALTER PROCEDURE removeFakeFlowerCheck
AS
	ALTER TABLE Flower DROP COLUMN isFake
GO

-- ADD/REMOVE DEFAULT CONSTRAINT
CREATE OR ALTER PROCEDURE addSalaryDefaultConstraint
AS
	ALTER TABLE Manager ADD CONSTRAINT default_salary DEFAULT(14900) FOR manager_salary
GO

CREATE OR ALTER PROCEDURE removeSalaryDefaultConstraint
AS
	ALTER TABLE Manager DROP CONSTRAINT default_salary
GO

-- CREATE/DROP TABLE
CREATE OR ALTER PROCEDURE addBooster 
AS
	CREATE TABLE Booster (
		booster_id INT,
		booster_name VARCHAR(100) NOT NULL,
		booster_type VARCHAR(50) NOT NULL,
		booster_company VARCHAR(50) NOT NULL,
		booster_price INT NOT NULL
		CONSTRAINT BOOSTER_PRIMARY_KEY PRIMARY KEY(booster_id),
		environment_id INT NOT NULL
	)
GO 

CREATE OR ALTER PROCEDURE dropBooster
AS
	DROP TABLE Booster
GO

-- ADD/REMOVE PRIMARY KEY
CREATE OR ALTER PROCEDURE addBoosterPrimaryKey
AS
	ALTER TABLE Booster
		DROP CONSTRAINT BOOSTER_PRIMARY_KEY
	ALTER TABLE Booster
		ADD CONSTRAINT BOOSTER_PRIMARY_KEY PRIMARY KEY(booster_name, booster_company)
GO 

CREATE OR ALTER PROCEDURE removeBoosterPrimaryKey
AS
	ALTER TABLE Booster
		DROP CONSTRAINT BOOSTER_PRIMARY_KEY
	ALTER TABLE Booster
		ADD CONSTRAINT BOOSTER_PRIMARY_KEY PRIMARY KEY(booster_id)
GO

-- ADD/REMOVE CANDIDATE KEY
CREATE OR ALTER PROCEDURE addFlowerCandidateKey
AS	
	ALTER TABLE Flower
		ADD CONSTRAINT FLOWER_CANDIDATE_KEY UNIQUE(flower_species, flower_color)
GO

CREATE OR ALTER PROCEDURE removeFlowerCandidateKey
AS
	ALTER TABLE Flower
		DROP CONSTRAINT FLOWER_CANDIDATE_KEY
GO

-- ADD/REMOVE FOREIGN KEY
CREATE OR ALTER PROCEDURE addBoosterForeignKey
AS
	ALTER TABLE Booster
		ADD CONSTRAINT BOOSTER_FOREIGN_KEY FOREIGN KEY(environment_id) REFERENCES Environment(environment_id)
GO

CREATE OR ALTER PROCEDURE removeBoosterForeignKey
AS
	ALTER TABLE Booster
		DROP CONSTRAINT BOOSTER_FOREIGN_KEY
GO

-- Table keeping the version (current)

CREATE TABLE versionTable (
	version INT
)

INSERT INTO versionTable 
VALUES
	(1)

-- Table holds the former position, the next position and the procedure that changes it

CREATE TABLE procedureTable (
	initial_version INT,
	final_version INT,
	procedure_name VARCHAR(MAX),
	PRIMARY KEY (initial_version, final_version)
)

INSERT INTO procedureTable
VALUES
	(1, 2, 'setVendorSalaryDecimal'),
	(2, 1, 'setVendorSalaryInteger'),
	(2, 3, 'addFakeFlowerCheck'), 
	(3, 2, 'removeFakeFlowerCheck'),
	(3, 4, 'addSalaryDefaultConstraint'),
	(4, 3, 'removeSalaryDefaultConstraint'),
	(4, 5, 'addBooster'),
	(5, 4, 'dropBooster'),
	(5, 6, 'addBoosterPrimaryKey'),
	(6, 5, 'removeBoosterPrimaryKey'),
	(6, 7, 'addFlowerCandidateKey'),
	(7, 6, 'removeFlowerCandidateKey'),
	(7, 8, 'addBoosterForeignKey'),
	(8, 7, 'removeBoosterForeignKey')

GO
CREATE OR ALTER PROCEDURE goToVersion(@newVersion INT) 
AS
	DECLARE @current_version INT			-- current version for keeping track
	DECLARE @procedureName VARCHAR(MAX)		-- the name of the procedure currently being applied
	SELECT @current_version = version FROM versionTable -- picks the beginning/current version

	IF (@newVersion > (SELECT MAX(final_version) FROM procedureTable) OR @newVersion < 1) -- check if new version bigger than max
		RAISERROR ('Bad version', 1, 1)													  -- or if it's lesser than min
	ELSE
	BEGIN
		IF @newVersion = @current_version -- check if new version is the same version as current
			PRINT('Same version!');
		ELSE
		BEGIN
			IF @current_version > @newVersion		-- if going to the left
			BEGIN
				WHILE @current_version > @newVersion		-- while going to the left
					BEGIN
						-- select the current procedure according to the procedure table
						SELECT @procedureName = procedure_name FROM procedureTable WHERE initial_version = @current_version AND final_version = @current_version-1
						PRINT('Executing ' + @procedureName);
						EXEC (@procedureName)
						-- move current version to the left, repeat until it reaches the 'new version' value
						SET @current_version = @current_version - 1
					END
			END

			IF @current_version < @newVersion			-- otherwise, if going to the right
			BEGIN
				WHILE @current_version < @newVersion		-- while going to the right
					BEGIN
						-- select the current procedure according to the procedure table
						SELECT @procedureName = procedure_name FROM procedureTable WHERE initial_version = @current_version AND final_version = @current_version+1
						PRINT('Executing ' + @procedureName);
						EXEC (@procedureName)
						-- move current version to the right, repeat until it reaches 'new version' value
						SET @current_version = @current_version + 1
					END
			END
			-- update the overall initial version to be the new version
			UPDATE versionTable SET version = @newVersion
		END
	END
GO

EXEC goToVersion 1

SELECT *
FROM versionTable

SELECT *
FROM procedureTable

--DROP TABLE procedureTable
--DROP TABLE versionTable