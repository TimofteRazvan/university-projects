#include "Product.h"
#include <sstream>

Product::Product(std::string& _category, std::string& _name, int _quantity)
	: category{ _category }, name{ _name }, quantity{ _quantity }
{
}

std::string Product::getCategory() const
{
	return this->category;
}

std::string Product::getName() const
{
	return this->name;
}

int Product::getQuantity() const
{
	return this->quantity;
}

std::vector<std::string> tokenize(std::string& str, char delimiter)
{
	std::string token;
	std::stringstream ss(str);
	std::vector<std::string> result;
	while (getline(ss, token, delimiter))
		result.push_back(token);
	return result;
}

std::istream& operator>>(std::istream& is, Product& prod)
{
	std::string line;
	getline(is, line);

	std::vector<std::string> tokens = tokenize(line, ',');
	if (tokens.size() != 3)
		return is;
	prod.category = tokens[0];
	prod.name = tokens[1];
	prod.quantity = stoi(tokens[2]);
	return is;
}

std::ostream& operator<<(std::ostream& os, const Product prod)
{
	os << prod.category << "," << prod.name << "," << prod.quantity << std::endl;
	return os;
}
