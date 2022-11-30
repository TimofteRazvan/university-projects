#pragma once
#include "repository.h"
#include <fstream>
#include "cart.h"
#include "undoredo.h"

class Controller
{
private:
	Repo repo;
	Cart* cart;
	std::vector<std::shared_ptr<UndoRedoAction>> undoAction;
	std::vector<std::shared_ptr<UndoRedoAction>> redoAction;

public:
	// Creates the controller
	Controller(Repo repository, Cart* cart);

	// Adds a coat
	// Returns 1 if already found, 0 otherwise
	void addCoat(std::string size, std::string colour, int price, int quantity, std::string photograph);

	// Deletes a coat
	// Returns 1 if none found, 0 otherwise
	void deleteCoat(std::string photo);

	// Updates a coat
	// Returns 1 if none found, 0 otherwise
	void updateCoat(std::string photo, std::string _size, std::string _colour, int _price, int _quantity, std::string _photo);

	// Using the given fileName, initializes the Repository with the Coat objects from within the file
	// Inside the function, it uses istream, loops through the stream and uses the overwritten operator '>>'
	void readCoats(std::string fileName);

	// Returns the repo of coats
	std::vector<Coat> getAll();

	// Returns the shopping cart
	std::vector<Coat> getCart();

	// Returns the filtered repo
	std::vector<Coat> getFilter();

	// Returns the size of the coats repo
	int getSize() const;

	// Returns the size of the cart
	int getCartSize() const;

	// Returns the size of the filtered repo
	int getFilterSize() const;

	void addFilter(const Coat& newCoat);

	// Returns a filtered repo of coats based upon the given size
	void filterSize(std::string size);

	// Adds to the shopping cart a certain coat
	void addToCart(std::string size, std::string colour, int price, int quantity, std::string photograph);

	void writeCartToFile();

	void readCartFromFile();

	// Adds a coat to the cart
	void addToAbsCart(Coat& coat);

	void filterClear();

	void undo();
	void redo();
	//void clearUndoRedo();

	void sortCtrl();

	// Tests the controller
	void testCtrl();
};