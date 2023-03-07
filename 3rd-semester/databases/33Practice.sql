CREATE OR ALTER FUNCTION allTypeChefs ()
	RETURNS TABLE AS
	RETURN
		SELECT C.chefId, C.chefName
		FROM Chefs C
		INNER JOIN
		(SELECT CS.chefId
		FROM ChefSpecializations CS
		GROUP BY CS.chefId
		HAVING COUNT(*) = (SELECT COUNT(*) FROM CakeTypes)) C1
		ON C1.chefId = C.chefId
GO

SELECT * FROM allTypeChefs()