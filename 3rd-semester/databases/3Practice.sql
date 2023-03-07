CREATE OR ALTER VIEW boughtMoreThan2 AS
	SELECT W.woman_name
	FROM Women W
	INNER JOIN
	(SELECT WS2.shoe_id, woman_id
	FROM WomenBuyShoes WS2
	INNER JOIN
	(SELECT S.shoe_id
	FROM Shoes S
	INNER JOIN
	(SELECT WS.shoe_id FROM WomenBuyShoes WS
	WHERE WS.shoes_bought > 2) S1
	ON S.shoe_id = S1.shoe_id AND S.model_id = 2) WS1
	ON WS1.shoe_id = WS2.shoe_id) WS3
	ON WS3.woman_id = W.woman_id
	
GO

-- Multiple views, maybe? Don't think it's possible with only 1 view.
-- I need to INNER JOIN Shoes & WomenBuyShoes to find the ShoeIDs with the appropriate Shoe Models
-- I need to INNER JOIN Women and the above to find the Women's Names associated with the ShoeIDs

/*
	SELECT W.woman_name
		FROM Women W
	INNER JOIN
		(SELECT WS.woman_id
		FROM WomenBuyShoes WS
		WHERE WS.shoes_bought > 2) W1
	ON W.woman_id = W1.woman_id
*/