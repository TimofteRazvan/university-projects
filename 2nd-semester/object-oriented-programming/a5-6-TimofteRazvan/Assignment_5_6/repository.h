#pragma once
#include "coat.h"
#include "dynamicVector.h"
#include <assert.h>

class Repo 
{
private:
	DynamicVector array;

public:
	// Repo creator
	Repo();

	// Adds a coat
	// Returns 1 if already exists, 0 otherwise
	int addCoat(const Coat& newCoat);

	// Deletes a coat
	// Returns 1 if doesn't exist, 0 otherwise
	int deleteCoat(string photo);

	// Updates a coat
	// Returns 1 if doesn't exist, 0 otherwise
	int updateCoat(string photo, int _price, int _quantity, string _photo);

	// Returns the whole repo of coats
	Coat* getAll() const;

	// Returns the size of the repo of coats
	int getSize() const;
	
	// Tests the repository
	void testRepo();
};