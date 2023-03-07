CREATE OR ALTER PROCEDURE addShoe @shoe INT, @shop INT, @nrShoes INT AS
BEGIN
	IF NOT EXISTS (SELECT * FROM ShoesInShops SIS
					WHERE sis.shoe_id=@shoe AND sis.shop_id=@shop)
		BEGIN
		INSERT INTO ShoesInShops (
			shoe_id,
			shop_id,
			shoes_available
		) VALUES
			(@shoe, @shop, @nrShoes);
		END
	ELSE
		UPDATE ShoesInShops
		SET shoes_available=shoes_available + @nrShoes
		WHERE shoe_id=@shoe AND shop_id=@shop
END
GO

EXEC addShoe @shoe=3, @shop=1, @nrShoes=10