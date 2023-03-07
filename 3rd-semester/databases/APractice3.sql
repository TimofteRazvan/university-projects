CREATE OR ALTER VIEW cardsAtAll AS
		SELECT C.card_number
		FROM Cards C
	INNER JOIN
		(SELECT T.card_id
		FROM Transactions T
		GROUP BY T.card_id
		HAVING COUNT (*) = (SELECT COUNT(*) FROM ATMs)) C2
	ON C2.card_id = C.card_id
GO

