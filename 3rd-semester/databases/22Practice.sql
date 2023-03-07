CREATE OR ALTER PROCEDURE addCakeToOrder @orderId INT, @cakeName VARCHAR(50), @P INT AS
BEGIN
	IF EXISTS (SELECT C.cakeName FROM Cakes C
				WHERE @cakeName = C.cakeName)
		BEGIN
			DECLARE @cakeId INT
			SELECT @cakeId=C.cakeId FROM Cakes C WHERE @cakeName = C.cakeName
			IF EXISTS (SELECT OC.cakeId FROM OrderedCakes OC
						WHERE OC.cakeId = @cakeId)
				BEGIN
					UPDATE OrderedCakes
					SET pieces = @P
					WHERE cakeId = @cakeId
				END
			ELSE
				INSERT INTO OrderedCakes
				(
				    orderId,
					cakeId,
					pieces
				) VALUES (@orderId, @cakeId, @P);
		END
				
END
GO

SELECT * FROM OrderedCakes

EXEC addCakeToOrder @orderId=1, @cakeName='Cheesecake1', @P=5

SELECT * FROM OrderedCakes