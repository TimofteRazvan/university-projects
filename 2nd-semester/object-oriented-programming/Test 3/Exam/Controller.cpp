#include "Controller.h"

Controller::Controller(Repository repo) : repo{ repo }
{
}

int Controller::getSize()
{
	return this->repo.getSize();
}

std::vector<Product> Controller::getAll()
{
	return this->repo.getAll();
}

void Controller::sort()
{
	this->repo.sort();
}
