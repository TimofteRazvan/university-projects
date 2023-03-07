CREATE OR ALTER VIEW allStations AS
	SELECT R.route_name
	FROM Routes R
	INNER JOIN
	(SELECT rid
		FROM RoutesStations RS
		GROUP BY rid
		HAVING COUNT(*) = (SELECT COUNT(*) FROM Stations)) R2
	ON R2.rid = R.rid
GO

/*
SELECT rid
		FROM RoutesStations RS
		GROUP BY rid
		HAVING COUNT(*) = (SELECT COUNT(*) FROM Stations)
*/