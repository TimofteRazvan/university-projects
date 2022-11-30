#include "RepoProgrammers.h"

void RepoProgrammers::loadData()
{
	std::ifstream file("programmers.txt");
	Programmer pro;
	this->programmers.clear();
	while (file >> pro)
	{
		this->programmers.push_back(pro);
	}
	file.close();
}

RepoProgrammers::RepoProgrammers()
{
	this->loadData();
}

std::vector<Programmer>& RepoProgrammers::getProgrammers()
{
	return this->programmers;
}

RepoProgrammers::~RepoProgrammers()
{
}
