#pragma once
#include <assert.h>
#include <vector>
#include "errors.h"
#include "comparator.h"
#include <algorithm>

class Repo 
{
protected:
	//DynamicVector<Coat> array;
	//DynamicVector<Coat> cart;
	//DynamicVector<Coat> filter;
	std::vector<Coat> array;
	std::vector<Coat> cart;
	std::vector<Coat> filter;

public:
	// Repo creator
	Repo();

	void readFromFile();

	// Adds a coat
	// Returns 1 if already exists, 0 otherwise
	void addCoat(const Coat& newCoat);

	void addFilter(const Coat& newCoat);

	Coat getCoat(std::string photo);

	// Deletes a coat
	// Returns 1 if doesn't exist, 0 otherwise
	void deleteCoat(std::string photo);

	// Updates a coat
	// Returns 1 if doesn't exist, 0 otherwise
	void updateCoat(std::string photo, std::string _size, std::string _colour, int _price, int _quantity, std::string _photo);

	//void sort(std::vector<Coat>, Comparator& comparator);

	// Returns the whole repo of coats
	std::vector<Coat> getAll();

	std::vector<Coat> getCoats() const;

	// Returns the whole cart
	std::vector<Coat> getCart() const;

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

	void deleteFromCart(const Coat& coat);

	void filterClear();
	
	// Tests the repository
	void testRepo();
};

// Sorting function templated for anything
//template<typename T1>
//void sortAll(std::vector<T1> elements, Comparator<T1>* typecomp);