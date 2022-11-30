#pragma once
#include <string>
using namespace std;
// delete using namespace if issues arise

class Coat 
{
private:
	string size;
	string colour;
	int price;
	int quantity;
	string photograph;

public:
	// Basic create function
	Coat();

	// Creates the coat object
	Coat(string, string, int, int, string);
	Coat(const Coat& rhs);

	// Gets the size
	string getSize() const;

	// Gets the colour
	string getColour() const;

	// Gets the price
	int getPrice() const;

	// Gets the quantity
	int getQuantity() const;

	// Gets the photograph
	string getPhotograph() const;

	// Setter for size
	void setSize(string _size);
	
	// Setter for colour
	void setColour(string _colour);

	// Setter for price
	void setPrice(int _price);

	// Setter for quantity
	void setQuantity(int _quantity);

	// Setter for photograph link
	void setPhotograph(string _photograph);
};