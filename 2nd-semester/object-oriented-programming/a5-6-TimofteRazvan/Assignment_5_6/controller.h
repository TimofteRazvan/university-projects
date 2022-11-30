#pragma once
#include "repository.h"

class Controller
{
private:
	Repo repo;

public:
	// Creates the controller
	Controller(Repo repository);

	// Adds a coat
	// Returns 1 if already found, 0 otherwise
	int addCoat(string size, string colour, int price, int quantity, string photograph);

	// Deletes a coat
	// Returns 1 if none found, 0 otherwise
	int deleteCoat(string photo);

	// Updates a coat
	// Returns 1 if none found, 0 otherwise
	int updateCoat(string photo, int _price, int _quantity, string _photo);

	// Generates 10 programmer-inputted coats
	void generateCoats();

	// Returns the repo of coats
	Coat* getAll() const;

	// Returns the size of the coats repo
	int getSize() const;

	// Tests the controller
	void testCtrl();
};