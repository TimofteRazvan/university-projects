CREATE OR ALTER FUNCTION atLeastTShops (@T INT)
	RETURNS TABLE
	AS
	RETURN
		SELECT SS.shoe_id
		FROM ShoesInShops SS
		GROUP BY SS.shoe_id
		HAVING COUNT(*) >= @T
GO

SELECT * FROM atLeastTShops(3)