#pragma once
#include "coat.h"
#include "dynamicVector.h"
#include <assert.h>
#include <vector>
#include "errors.h"

class Repo 
{
private:
	//DynamicVector<Coat> array;
	//DynamicVector<Coat> cart;
	//DynamicVector<Coat> filter;
	std::vector<Coat> array;
	std::vector<Coat> cart;
	std::vector<Coat> filter;

public:
	// Repo creator
	Repo();

	// Adds a coat
	// Returns 1 if already exists, 0 otherwise
	void addCoat(const Coat& newCoat);

	// Deletes a coat
	// Returns 1 if doesn't exist, 0 otherwise
	void deleteCoat(std::string photo);

	// Updates a coat
	// Returns 1 if doesn't exist, 0 otherwise
	void updateCoat(std::string photo, std::string _size, std::string _colour, int _price, int _quantity, std::string _photo);

	// Returns the whole repo of coats
	std::vector<Coat> getAll();

	// Returns the whole cart
	std::vector<Coat> getCart();

	// Returns the filtered repo
	std::vector<Coat> getFilter();

	// Returns the size of the repo of coats
	int getSize() const;

	// Returns the size of the cart
	int getCartSize() const;

	// Returns the size of the filtered repo
	int getFilterSize() const;

	// Returns the filtered repo based upon size
	void filterSize(std::string size);

	// Adds to the shopping cart the coat
	void addToCart(const Coat& newCoat);
	
	// Tests the repository
	void testRepo();
};