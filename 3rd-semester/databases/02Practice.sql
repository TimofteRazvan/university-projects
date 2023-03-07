CREATE OR ALTER PROCEDURE addActorToProduction @actor_id INT, @actor_entry TIME, @production_id INT AS
BEGIN
	IF NOT EXISTS (SELECT * FROM ActorsInProduction AP
				WHERE @actor_id = AP.actor_id AND @production_id = AP.production_id)
		BEGIN
			INSERT INTO ActorsInProduction
			(
				actor_id,
				actor_entry,
				production_id
			) VALUES (@actor_id, @actor_entry, @production_id);
		END
END
GO

SELECT * FROM ActorsInProduction

EXEC addActorToProduction @actor_id=1, @actor_entry='10:00:00 AM', @production_id=1

SELECT * FROM ActorsInProduction