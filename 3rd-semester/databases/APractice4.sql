CREATE OR ALTER FUNCTION greaterSum ()
	RETURNS TABLE
	AS
	RETURN 
		SELECT C.card_number, C.card_ccv
		FROM Cards C
		INNER JOIN
		(SELECT T.card_id
		FROM Transactions T
		GROUP BY T.card_id
		HAVING SUM(T.transaction_balance) > 2000) C1
		ON C1.card_id = C.card_id
GO

SELECT * FROM greaterSum()