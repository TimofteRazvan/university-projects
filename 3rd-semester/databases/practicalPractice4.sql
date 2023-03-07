CREATE OR ALTER FUNCTION moreThanR (@R INT)
	RETURNS TABLE AS
	RETURN
		SELECT S.station_name
		FROM Stations S
		INNER JOIN
		(SELECT RS.sid
			FROM RoutesStations RS
			GROUP BY RS.sid
			HAVING COUNT(*) > @R) IJ
		ON S.sid = IJ.sid
GO

SELECT * FROM moreThanR(2)