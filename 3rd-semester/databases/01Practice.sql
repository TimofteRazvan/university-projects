IF OBJECT_ID('ActorsInProduction', 'U') IS NOT NULL
	DROP TABLE ActorsInProduction

IF OBJECT_ID('CinemaProductions', 'U') IS NOT NULL
	DROP TABLE CinemaProductions

IF OBJECT_ID('Movies', 'U') IS NOT NULL
	DROP TABLE Movies

IF OBJECT_ID('Actors', 'U') IS NOT NULL
	DROP TABLE Actors

IF OBJECT_ID('StageDirectors', 'U') IS NOT NULL
	DROP TABLE StageDirectors

IF OBJECT_ID('Companies', 'U') IS NOT NULL
	DROP TABLE Companies

CREATE TABLE Companies
(
	company_id INT PRIMARY KEY,
	company_name VARCHAR(50)
)

CREATE TABLE StageDirectors
(
	director_id INT PRIMARY KEY,
	director_name VARCHAR(50),
	director_awards INT
)

CREATE TABLE Actors
(
	actor_id INT PRIMARY KEY,
	actor_name VARCHAR(50),
	actor_ranking INT
)

CREATE TABLE Movies
(
	movie_id INT PRIMARY KEY,
	movie_name VARCHAR(50),
	movie_date DATE,
	company_id INT REFERENCES Companies(company_id),
	director_id INT REFERENCES StageDirectors(director_id)
)

CREATE TABLE CinemaProductions
(
	production_id INT PRIMARY KEY,
	production_title VARCHAR(50),
	movie_id INT REFERENCES Movies(movie_id)
)

CREATE TABLE ActorsInProduction
(
	actor_id INT REFERENCES Actors(actor_id),
	actor_entry TIME,
	production_id INT REFERENCES CinemaProductions(production_id),
	PRIMARY KEY(actor_id, production_id)
)

INSERT INTO Actors
(
	actor_id,
	actor_name,
	actor_ranking
) VALUES (1, 'Razvan', 1);

INSERT INTO Companies
(
	company_id,
	company_name
) VALUES (1, 'COMPANY');

INSERT INTO StageDirectors
(
	director_id,
	director_name,
	director_awards
) VALUES (1, 'TIMOFTE', 100);

INSERT INTO Movies
(
	movie_id,
	movie_name,
	movie_date,
	company_id,
	director_id
) VALUES (1, 'MOVIE', '20180102', 1, 1);

INSERT INTO CinemaProductions
(
	production_id,
	production_title,
	movie_id
) VALUES(1, 'PRODUCTION', 1);

SELECT * FROM Actors
SELECT * FROM Movies
SELECT * FROM StageDirectors
SELECT * FROM CinemaProductions
SELECT * FROM Companies
SELECT * FROM ActorsInProduction