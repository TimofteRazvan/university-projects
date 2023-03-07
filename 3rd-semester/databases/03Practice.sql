CREATE OR ALTER VIEW actorsInAllProductions AS
	SELECT A.actor_name, A.actor_id
	FROM Actors A
	INNER JOIN
	(SELECT A1.actor_id
	 FROM ActorsInProduction A1
	 GROUP BY A1.actor_id
	 HAVING COUNT(*) = (SELECT COUNT(*) FROM CinemaProductions)) A2
	 ON A.actor_id = A2.actor_id
GO

SELECT * FROM actorsInAllProductions