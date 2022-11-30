#pragma once
#include "Programmer.h"
#include <fstream>

class RepoProgrammers
{
private:
	std::vector<Programmer> programmers;
	void loadData();

public:
	RepoProgrammers();
	std::vector<Programmer>& getProgrammers();
	~RepoProgrammers();
};