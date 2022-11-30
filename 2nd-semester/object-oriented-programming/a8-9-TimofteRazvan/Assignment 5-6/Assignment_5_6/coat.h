#pragma once
#include <string>
#include <iostream>
// delete using namespace if issues arise

class Coat 
{
private:
	std::string size;
	std::string colour;
	int price;
	int quantity;
	std::string photograph;

public:
	// Basic create function
	Coat();

	// Creates the coat object
	Coat(std::string, std::string, int, int, std::string);
	Coat(const Coat& rhs);

	// Gets the size
	std::string getSize() const;

	// Gets the colour
	std::string getColour() const;

	// Gets the price
	int getPrice() const;

	// Gets the quantity
	int getQuantity() const;

	// Gets the photograph
	std::string getPhotograph() const;

	// Setter for size
	void setSize(std::string _size);
	
	// Setter for colour
	void setColour(std::string _colour);

	// Setter for price
	void setPrice(int _price);

	// Setter for quantity
	void setQuantity(int _quantity);

	// Setter for photograph link
	void setPhotograph(std::string _photograph);

	// Function for reading from a file (input stream) and assigning values to Coat based on the 'tokenize' function and delimiter
	std::istream& read(std::istream& is);

	// Overloads '>>' operator, uses 'read' function to allow getting values from a file directly as Coat objects
	std::istream& operator>>(std::istream& is);

	// Overloads '<<' operator, allows for easy outputting of Coat objects in the desired format
	std::ostream& operator<<(std::ostream& os);

	// Allows equating two Coat objects directly, by equating every value within the function
	bool operator==(const Coat& c);

	// Tests the coat;
	void testCoat();
};