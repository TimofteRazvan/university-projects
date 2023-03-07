USE flowerShopDatabase

--------------------------------------------------------------------------
-- ADDERS
--------------------------------------------------------------------------

-- Adds a Table in Tables
GO
CREATE OR ALTER PROCEDURE addToTables (@tableName VARCHAR(50)) AS
BEGIN
	-- Checks if Table is already in the Tables Table
	IF @tableName IN (SELECT [Name] from [Tables]) 
	BEGIN
		PRINT 'Table already present in [Tables]!!!'
		RETURN
	END
	-- Checks if Table exists in the first place in the whole Database
	IF @tableName NOT IN (SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES)
	BEGIN
		PRINT 'Table not present in flowerShopDatabase!!!'
		RETURN
	END
	-- If no exceptions were triggered, adds the new Table to the Tables Table
	INSERT INTO [Tables] ([Name]) 
	VALUES
		(@tableName)
END
GO

-- Adds a Test in Tests
CREATE OR ALTER PROCEDURE addToTests (@testName VARCHAR(50)) AS
BEGIN
	-- Checks if the Test is already in the Tests Table
	IF @testName IN (SELECT [Name] from [Tests]) 
	BEGIN
		PRINT 'Test already present in [Tests]!!!'
		RETURN
	END
	-- If no exceptions were triggered, adds the new Test into the Tests Table
	INSERT INTO [Tests] ([Name]) 
	VALUES
		(@testName)
END
GO

-- Adds a View in Views
CREATE OR ALTER PROCEDURE addToViews (@viewName VARCHAR(50)) AS
BEGIN
	-- Checks if the View is already in the Views Table
	IF @viewName IN (SELECT [Name] from [Views]) 
	BEGIN
		PRINT 'View already present in [Views]!!!'
		RETURN
	END
	-- Checks if the View is already in the whole Database
	IF @viewName NOT IN (SELECT TABLE_NAME FROM INFORMATION_SCHEMA.VIEWS)
	BEGIN
		PRINT 'View not present in flowerShopDatabase!!!'
		RETURN
	END
	-- If no exceptions were triggered, adds to the Views Table
	INSERT INTO [Views] ([Name]) 
	VALUES
		(@viewName)
END
GO

--------------------------------------------------------------------------
-- CONNECTORS
--------------------------------------------------------------------------

-- Connects a given Table to a given Test via TestTables
CREATE OR ALTER PROCEDURE connectTableToTest (@tableName VARCHAR(50), @testName VARCHAR(50), @rows INT, @pos INT) AS
BEGIN
	-- Checks if the Table hasn't been inserted in the Tables Table
	IF @tableName NOT IN (SELECT [Name] FROM [Tables]) 
		BEGIN
			PRINT 'Table not present in [Tables]!!!'
			RETURN
		END
	-- Checks if the Test hasn't been inserted into the Tests Table
	IF @testName NOT IN (SELECT [Name] FROM [Tests])
		BEGIN
			PRINT 'Test not present in [Tests]!!!'
			RETURN
		END

	IF EXISTS(	-- Checks if Test w/ same name and position already there
		SELECT * 
		FROM TestTables T1 JOIN Tests T2 ON T1.TestID = T2.TestID
		WHERE T2.[Name] = @testName AND Position = @pos
	)
		BEGIN
			PRINT 'Conflicting positions!!!'
			RETURN
		END
	-- If no exceptions were triggered, inserts both into their respective tables
	INSERT INTO [TestTables] (TestID, TableID, NoOfRows, Position) 
	VALUES (
		(SELECT [Tests].TestID FROM [Tests] WHERE [Name] = @testName),
		(SELECT [Tables].TableID FROM [Tables] WHERE [Name] = @tableName),
		@rows,
		@pos
	)
END
GO

-- Connects a given View to a given Test via TestViews
CREATE OR ALTER PROCEDURE connectViewToTest (@viewName VARCHAR(50), @testName VARCHAR(50)) AS
BEGIN
	-- Checks if the View doesn't exist in Views
	IF @viewName NOT IN (SELECT [Name] FROM [Views]) 
		BEGIN
			PRINT 'View not present in [Views]!!!'
			RETURN
		END
	-- Checks if the Test doesn't exist in Tests
	IF @testName NOT IN (Select [Name] FROM [Tests]) 
		BEGIN
			PRINT 'Test not present in [Tests]!!!'
			RETURN
		END
	-- If no exceptions were triggered, inserts both in their respective tables
	INSERT INTO [TestViews] (TestID, ViewID)
	VALUES(
		(SELECT [Tests].TestID FROM [Tests] WHERE [Name] = @testName),
		(SELECT [Views].ViewID FROM [Views] WHERE [Name] = @viewName)
	)
END
GO

--------------------------------------------------------------------------
-- DELETERS
--------------------------------------------------------------------------

-- Deletes from the given Table  its data
CREATE OR ALTER PROCEDURE deleteDataFromTable (@tableID INT) AS
BEGIN
	-- Checks if the Table is in the Tables table
	IF @tableID NOT IN (SELECT [TableID] FROM [Tables])
	BEGIN
		PRINT 'Table not present in [Tables]!!!'
		RETURN
	END
	-- Retreives the tableName
	DECLARE @tableName NVARCHAR(50) = (SELECT [Name] FROM [Tables] WHERE TableID = @tableID)
	PRINT 'Delete data from table ' + @tableName
	-- Deletes the data from the Table
	DECLARE @query NVARCHAR(100) = N'DELETE FROM ' + @tableName
	PRINT @query
	EXEC sp_executesql @query
END
GO

-- Deletes from all the tables the data in descending order
CREATE OR ALTER PROCEDURE deleteDataFromAllTables (@testID INT) AS
BEGIN
	-- Checks if the Test is in the Tests table
	IF @testID NOT IN (SELECT [TestID] FROM [Tests])
	BEGIN
		PRINT 'Test not present in [Tests]!!!'
		RETURN
	END
	PRINT 'Delete data from all tables for Test' + CONVERT(VARCHAR, @testID)
	-- @pos for finding which one to delete first
	DECLARE @tableID INT
	DECLARE @pos INT
	-- Descending index ordered by POSITION (@pos) in TestTables
	DECLARE allTableCursorDesc CURSOR LOCAL FOR
		SELECT T1.TableID, T1.Position 
		FROM TestTables T1 
		INNER JOIN Tests T2 ON T2.TestID = T1.TestID
		WHERE T2.TestID = @testID
		ORDER BY T1.Position DESC

	OPEN allTableCursorDesc
	FETCH allTableCursorDesc INTO @tableID, @pos
	-- Keeps doing deleteDataFromTable for every tableID, taking the biggest POSITION every time
	WHILE @@FETCH_STATUS = 0 
	BEGIN
		EXEC deleteDataFromTable @tableID
		FETCH NEXT FROM allTableCursorDesc INTO @tableID, @pos
	END
	CLOSE allTableCursorDesc
	DEALLOCATE allTableCursorDesc
END
GO

--------------------------------------------------------------------------
-- INSERTERS
--------------------------------------------------------------------------

-- Insert data into individual Table & TestRunTables based on the connected TestRun, Test & Table
CREATE OR ALTER PROCEDURE insertDataIntoTable (@testRunID INT, @testID INT, @tableID INT) AS
BEGIN
	-- Checks if Test doesn't exist in Tests table
	IF @testID NOT IN (SELECT [TestID] FROM [Tests])
	BEGIN
		PRINT 'Test not present in [Tests]!!!'
		RETURN
	END
	-- Checks if TestRun doesn't exist in TestRuns table
	IF @testRunID NOT IN (SELECT [TestRunID] FROM [TestRuns])
	BEGIN
		PRINT 'Test run not present in [TestRuns]!!!'
		RETURN
	END
	-- Checks if Table doesn't exist in Tables table
	IF @tableID NOT IN (SELECT [TableID] FROM [Tables])
	BEGIN
		PRINT 'Table not present in [Tables]!!!'
		RETURN
	END
	-- If they all exist, we can go and fill in TestRunTables
	DECLARE @startTime DATETIME = SYSDATETIME()
	DECLARE @tableName VARCHAR(50) = (
		SELECT [Name] 
		FROM [Tables] 
		WHERE TableID = @tableID
	)
	PRINT 'Insert data into table ' + @tableName
	DECLARE @numberOfRows INT = (
		SELECT [NoOfRows] 
		FROM [TestTables]
		WHERE TestID = @testID AND TableID = @tableID
	)
	-- Populates the given Table so we can actually work with it
	-- Generator for random data based on the table's parameters
	EXEC generateRandomDataForTable @tableName, @numberOfRows

	DECLARE @endTime DATETIME = SYSDATETIME()
	-- Inserts the data acquired into TestRunTables (the TestRunID, the Table, the start time and end time)
	INSERT INTO TestRunTables(TestRunID, TableID, StartAt, EndAt)
	VALUES (@testRunID, @tableID, @startTime, @endTime)
END
GO

-- Insert data into all Tables of the TestRunTables via TestRun, Test
CREATE OR ALTER PROCEDURE insertDataIntoAllTables (@testRunID INT, @testID INT) AS
BEGIN
	-- Checks if the Test doesn't exist in Tests table
	IF @testID NOT IN (SELECT [TestID] FROM [Tests])
	BEGIN
		PRINT 'Test not present in [Tests]!!!'
		RETURN
	END
	-- Checks if TestRun doesn't exist in TestRuns table
	IF @testRunID NOT IN (SELECT [TestRunID] FROM [TestRuns])
	BEGIN
		PRINT 'TestRun not present in [TestRuns]!!!'
		RETURN
	END

	PRINT 'Insert data in all the tables for the test ' + CONVERT(VARCHAR, @testID)
	-- @pos POSITION so we can insert data in the order required
	DECLARE @tableID INT
	DECLARE @pos INT
	-- Index that goes ASCENDINGLY based on the position, opposite of the DELETION
	DECLARE allTableCursorAsc CURSOR LOCAL FOR
		SELECT T1.TableID, T1.Position
		FROM TestTables T1
		INNER JOIN Tests T2 ON T2.TestID = T1.TestID
		WHERE T1.TestID = @testID
		ORDER BY T1.Position ASC

	OPEN allTableCursorAsc
	FETCH allTableCursorAsc INTO @tableID, @pos
	-- While loop for all the Tables, inserts data into every Table and changes TestRunTables based on it too
	WHILE @@FETCH_STATUS = 0
	BEGIN
		EXEC insertDataIntoTable @testRunID, @testID, @tableID
		FETCH NEXT FROM allTableCursorAsc INTO @tableID, @pos
	END
	CLOSE allTableCursorAsc
	DEALLOCATE allTableCursorAsc
END
GO

--------------------------------------------------------------------------
-- SELECTORS
--------------------------------------------------------------------------

-- Select data from given View, insert into TestRunViews
CREATE OR ALTER PROCEDURE selectDataFromView (@viewID INT, @testRunID INT) AS
BEGIN
	-- Checks if the View doesn't exist in the Views table
	IF @viewID NOT IN (SELECT [ViewID] FROM [Views])
	BEGIN
		PRINT 'View not present in [Views]!!!'
		RETURN
	END
	-- Checks if the TestRun doesn't exist in the TestRuns table
	IF @testRunID NOT IN (SELECT [TestRunID] FROM [TestRuns])
	BEGIN
		PRINT 'TestRun not present in [TestRuns]!!!'
		RETURN
	END

	DECLARE @startTime DATETIME = SYSDATETIME()
	DECLARE @viewName VARCHAR(100) = (
		SELECT [Name]
		FROM [Views]
		WHERE ViewID = @viewID
	)
	PRINT 'Selecting data from View ' + @viewName
	-- Selects the data from the given view
	DECLARE @query NVARCHAR(150) = N'SELECT * FROM ' + @viewName
	EXEC sp_executesql @query

	DECLARE @endTime DATETIME = SYSDATETIME()
	-- Insert the TestRun, the View we selected data from, the starting time and the end time of the operation
	INSERT INTO TestRunViews(TestRunID, ViewID, StartAt, EndAt)
	VALUES (@testRunID, @viewID, @startTime, @endTime)
END
GO

-- Select data from all Views, iterates through instances of selectDataFromView
CREATE OR ALTER PROCEDURE selectAllViews (@testRunID INT, @testID INT) AS
BEGIN
	-- Checks if the Test doesn't exist in the Tests table
	IF @testID NOT IN (SELECT [TestID] FROM [Tests])
	BEGIN
		PRINT 'Test not present in [Tests]!!!'
		RETURN
	END
	-- Checks if the TestRun doesn't exist in the TestRun table
	IF @testRunID NOT IN (SELECT [TestRunID] FROM [TestRuns])
	BEGIN
		PRINT 'TestRun not present in [TestRuns]!!!'
		RETURN
	END

	PRINT 'Selecting data from all the views from the test ' + CONVERT(VARCHAR, @testID)
	DECLARE @viewID INT
	-- Index that interates through views regardless of order but regarding the current Test
	DECLARE selectAllViewsCursor CURSOR LOCAL FOR
		SELECT T1.ViewID FROM TestViews T1
		INNER JOIN Tests T2 ON T2.TestID = T1.TestID
		WHERE T1.TestID = @testID

	OPEN selectAllViewsCursor
	FETCH selectAllViewsCursor INTO @viewID
	-- Iterates through the views, selects the data from every single one
	WHILE @@FETCH_STATUS = 0 
	BEGIN
		EXEC selectDataFromView @viewID, @testRunID
		FETCH NEXT FROM selectAllViewsCursor INTO @viewID
	END
	CLOSE selectAllViewsCursor
	DEALLOCATE selectAllViewsCursor
END
GO

--------------------------------------------------------------------------
-- RUNNERS
--------------------------------------------------------------------------

-- Runs a test
CREATE OR ALTER PROCEDURE runTest (@testID INT, @description VARCHAR(MAX)) AS
BEGIN
	-- Checks if the Test has been logged into the Tests table
	IF @testID NOT IN (SELECT [TestID] FROM [Tests])
	BEGIN
		PRINT 'Test not in Tests'
		RETURN
	END
	
	PRINT 'Running Test' + CONVERT(VARCHAR, @testID)
	INSERT INTO TestRuns([Description])
	VALUES (@description)
	-- Gets the latest created TestRun ID
	DECLARE @testRunID INT = (
		SELECT MAX(TestRunID)
		FROM TestRuns
	)
	DECLARE @startTime DATETIME = SYSDATETIME()
	-- Inserts the data into the appropriate tables within the TEst
	EXEC insertDataIntoAllTables @testRunID, @testID
	-- Selects all the relevant views within the Test
	EXEC selectAllViews @testRunID, @testID
	DECLARE @endTime DATETIME = SYSDATETIME()
	-- Deletes all the inserted data from the appropriate tables within the Test
	EXEC deleteDataFromAllTables @testID

	-- Updates the TestRuns table's newest TestRun with the new startTime & endTime
	UPDATE [TestRuns] SET StartAt = @startTime, EndAt = @endTime
	DECLARE @timeDifference INT = DATEDIFF(SECOND, @startTime, @endTime)
	PRINT 'Test' + CONVERT(VARCHAR, @testID) + ' took ' + CONVERT(VARCHAR, @timeDifference) + ' seconds.'
END
GO

-- Runs all the tests
CREATE OR ALTER PROCEDURE runAllTests AS
BEGIN
	DECLARE @testName VARCHAR(50)
	DECLARE @testID INT
	DECLARE @description VARCHAR(2000)
	-- Index that iterates through all the tests
	DECLARE allTestsCursor CURSOR LOCAL FOR
		SELECT *
		FROM Tests

	OPEN allTestsCursor
	FETCH allTestsCursor INTO @testID, @testName
	-- While there are Tests we haven't run, iterates through them and does runTest for every one of them
	WHILE @@FETCH_STATUS = 0
	BEGIN
		PRINT 'Running ' + @testName
		SET @description = 'Test results for Test' + CAST(@testID AS VARCHAR(2))
		EXEC runTest @testID, @description
		FETCH NEXT FROM allTestsCursor INTO @testID, @testName
	END
	CLOSE allTestsCursor
	DEALLOCATE allTestsCursor
END
GO

--------------------------------------------------------------------------
-- TESTS
--------------------------------------------------------------------------

--------------------------------------------------------------------------
-- TEST 1
--------------------------------------------------------------------------

-- VIEW: SELECT on ONE Table
CREATE OR ALTER VIEW adultCustomers AS
	SELECT C.customer_name, C.customer_age
	FROM Customer C
	WHERE C.customer_age > 20
GO

-- TABLE: ONE PRIMARY KEY COLUMN & NO FOREIGN KEY
EXEC addToViews 'adultCustomers'
EXEC addToTests 'Test1'
EXEC addToTables 'Customer'
EXEC connectTableToTest 'Customer', 'Test1', 500, 1
EXEC connectViewToTest 'adultCustomers', 'Test1'

--------------------------------------------------------------------------
-- TEST 2
--------------------------------------------------------------------------

-- VIEW: SELECT on at least TWO Tables
GO
CREATE OR ALTER VIEW managersWithVendors AS
	SELECT V.vendor_name, M.manager_name
	FROM Vendor V INNER JOIN Manager M on M.manager_id = V.[manager_id]
	WHERE M.manager_salary > 100
GO

-- TABLES: 1 with NO FOREIGN KEY & single-column PRIMARY KEY
--         1 with FOREIGN KEY
EXEC addToViews 'managersWithVendors'
EXEC addToTests 'Test2'
EXEC addToTables 'Manager'
EXEC connectTableToTest 'Manager', 'Test2', 500, 1
EXEC addToTables 'Vendor'
EXEC connectTableToTest 'Vendor', 'Test2', 500, 2
EXEC connectViewToTest 'managersWithVendors', 'Test2'

--------------------------------------------------------------------------
-- TEST 3
--------------------------------------------------------------------------

-- VIEW: SELECT on at least TWO Tables w/ GROUP BY clause
GO
CREATE OR ALTER VIEW groupCollectionsByCustomers AS
	SELECT SC.collection_id, SC.collection_occassion, COUNT(*) AS customers
	FROM Special_Collection SC
	INNER JOIN Customer_of_Collection CC ON SC.collection_id = CC.collection_id
	GROUP BY SC.collection_id, SC.collection_occassion
GO

-- TABLES: 1 with MULTI-COLUMN PRIMARY KEY
--         2 with SINGLE-COLUMN PRIMARY KEY & NO FOREIGN KEY
EXEC addToViews 'groupCollectionsByCustomers'
EXEC addToTests 'Test3'
EXEC addToTables 'Customer'
EXEC connectTableToTest 'Customer', 'Test3', 500, 1
EXEC addToTables 'Special_Collection'
EXEC connectTableToTest 'Special_Collection', 'Test3', 500, 2
EXEC addToTables 'Customer_of_Collection'
EXEC connectTableToTest 'Customer_of_Collection', 'Test3', 250, 3
EXEC insertDataIntoTable 10, 3, 5
EXEC connectViewToTest 'groupCollectionsByCustomers', 'Test3'




--------------------------------------------------------------------------
EXEC runAllTests
--------------------------------------------------------------------------

--DROP TABLE TestRuns
--DROP TABLE TestRunTables
--DROP TABLE TestRunViews
--DROP TABLE TestViews
--DROP TABLE TestTables
--DROP TABLE Views
--DROP TABLE Tables
--DROP TABLE Tests

SELECT *
FROM [Views]

SELECT *
FROM [Tables]

SELECT *
FROM [Tests]

SELECT *
FROM [TestTables]

SELECT *
FROM [TestViews]

SELECT *
FROM [TestRuns]

SELECT *
FROM [TestRunTables]

SELECT *
FROM [TestRunViews]