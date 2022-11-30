#pragma once
#include "Repository.h"

class Controller
{
private:
	Repository repo;
public:
	Controller(Repository repo);
	int getSize();
	std::vector<Product> getAll();
	void sort();
};