#pragma once
#include "Product.h"

class Repository
{
private:
	std::vector<Product> shopping_list;
	std::string fileName;

	void readFromFile();
public:
	Repository(std::string name);
	int getSize();
	std::vector<Product> getAll();
	void sort();
};