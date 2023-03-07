CREATE OR ALTER FUNCTION allMoviesAfterDate (@P INT)
RETURNS TABLE AS
RETURN
	SELECT M.movie_id, M.movie_date
	FROM Movies M
	INNER JOIN
	(SELECT CP.movie_id 
	FROM CinemaProductions CP
	GROUP BY CP.movie_id
	HAVING COUNT(*) >= @P) P
	ON P.movie_id = M.movie_id AND M.movie_date > '20180101'
GO

SELECT * FROM allMoviesAfterDate(1)