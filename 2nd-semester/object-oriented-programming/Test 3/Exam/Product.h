#pragma once
#include <string>
#include <iostream>
#include <vector>

class Product
{
private:
	std::string category;
	std::string name;
	int quantity;
public:
	Product() {};
	Product(std::string& _category, std::string& _name, int _quantity);
	std::string getCategory() const;
	std::string getName() const;
	int getQuantity() const;
	friend std::istream& operator>>(std::istream& is, Product& prod);
	friend std::ostream& operator<<(std::ostream& os, const Product prod);
};