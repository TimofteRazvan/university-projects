CREATE OR ALTER PROCEDURE addStationToRoute (@rid INT, @sid INT, @arrival TIME, @departure TIME) AS
	BEGIN
	IF NOT EXISTS (
				SELECT *
				FROM RoutesStations RS
				WHERE RS.rid = @rid AND RS.sid = @sid
				  )
		BEGIN
			INSERT INTO RoutesStations (
				rid, sid, arrival, departure
			) VALUES (@rid, @sid, @arrival, @departure)
		END

		ELSE
			UPDATE RoutesStations
			SET arrival = @arrival,
			    departure = @departure
			WHERE rid = @rid AND sid = @sid
	END
GO

EXEC addStationToRoute @rid=3, @sid=3, @arrival='14:00', @departure='19:00'