CREATE OR ALTER PROCEDURE deleteTransactionsOfCard @card INT AS
BEGIN
		DELETE
		FROM Transactions
		WHERE Transactions.card_id like @card
END
GO

EXEC deleteTransactionsOfCard @card=2