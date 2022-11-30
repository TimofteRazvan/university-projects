#include "controller.h"
#include "validators.h"
#include <iostream>

Controller::Controller(Repo repository)
	: repo(repository)
{
}

void Controller::addCoat(std::string size, std::string colour, int price, int quantity, std::string photograph)
{
	Coat newCoat(size, colour, price, quantity, photograph);
	CoatValidator::validate(newCoat);
	RepositoryValidator::validate_addCoat(this->repo, newCoat);
	repo.addCoat(newCoat);
}

void Controller::deleteCoat(std::string photo)
{
	RepositoryValidator::validate_deleteCoat(repo, photo);
	repo.deleteCoat(photo);
}

void Controller::updateCoat(std::string photo, std::string _size, std::string _colour, int _price, int _quantity, std::string _photo)
{
	Coat newCoat(_size, _colour, _price, _quantity, _photo);
	CoatValidator::validate(newCoat);
	RepositoryValidator::validate_updateCoat(this->repo, photo);
	repo.updateCoat(photo, _size, _colour, _price, _quantity, _photo);
}

void Controller::readCoats(std::string fileName)
{
	std::ifstream f{ fileName };
	Coat coat{};
	while (coat.operator>>(f))
	{
		repo.addCoat(coat);
	}
	/*
	string red = "Red", yellow = "Yellow", blue = "Blue", black = "Black";
	string s = "S", l = "L", x = "X", xl = "XL", xxl = "XXL";
	Coat coat1(xxl, red, 250, 30, "Link_1");
	Coat coat2(xxl, yellow, 250, 30, "Link_2");
	Coat coat3(xxl, blue, 250, 30, "Link_3");
	Coat coat4(xxl, black , 250, 30, "Link_4");
	Coat coat5(xl, blue, 350, 30, "Link_5");
	Coat coat6(xl, red, 350, 30, "Link_6");
	Coat coat7(xl, yellow, 350, 30, "Link_7");
	Coat coat8(l, red, 300, 30, "Link_8");
	Coat coat9(l, black, 300, 30, "Link_9");
	Coat coat10(s, yellow, 300, 30, "Link_10");
	repo.addCoat(coat1);
	repo.addCoat(coat2);
	repo.addCoat(coat3);
	repo.addCoat(coat4);
	repo.addCoat(coat5);
	repo.addCoat(coat6);
	repo.addCoat(coat7);
	repo.addCoat(coat8);
	repo.addCoat(coat9);
	repo.addCoat(coat10);
	*/
}

std::vector<Coat> Controller::getAll()
{
	return repo.getAll();
}

std::vector<Coat> Controller::getCart()
{
	return repo.getCart();
}

std::vector<Coat> Controller::getFilter()
{
	return repo.getFilter();
}

int Controller::getSize() const
{
	return repo.getSize();
}

int Controller::getCartSize() const
{
	return repo.getCartSize();
}

int Controller::getFilterSize() const
{
	return repo.getFilterSize();
}

void Controller::filterSize(std::string size)
{
	repo.filterSize(size);
}

void Controller::addToCart(std::string size, std::string colour, int price, int quantity, std::string photograph)
{
	Coat newCoat(size, colour, price, quantity, photograph);
	repo.addToCart(newCoat);
}

void Controller::testCtrl()
{
	Repo repo;
	Controller ctrl(repo);
	assert(ctrl.getSize() == 0);
	//assert(ctrl.addCoat("XXL", "Blue", 100, 15, "Link_2") == 0);
	assert(ctrl.getSize() == 1);
	//assert(ctrl.addCoat("XL", "Red", 100, 20, "Link_2") == 1);
	assert(ctrl.getSize() == 1);

	ctrl.deleteCoat("Link_2");
	assert(ctrl.getSize() == 0);
	ctrl.addCoat("XL", "Red", 100, 20, "Link_2");
	ctrl.deleteCoat("Link");
	assert(ctrl.getSize() == 1);
	//assert(ctrl.deleteCoat("Link_2") == 0);
	//assert(ctrl.deleteCoat("Link") == 1);

	ctrl.addCoat("XXL", "Blue", 100, 15, "Link_2");
	//assert(ctrl.updateCoat("Link_2", "XL", "Blue", 100, 50, "Link") == 0);
	//assert(ctrl.updateCoat("Link_2", "L", "Magenta", 100, 50, "Link") == 1);

	std::string red = "Red", yellow = "Yellow", blue = "Blue", black = "Black";
	std::string s = "S", l = "L", x = "X", xl = "XL", xxl = "XXL";
	addCoat(xxl, red, 250, 30, "Link_1");
	addCoat(xxl, yellow, 250, 30, "Link_2");
	addCoat(xxl, blue, 250, 30, "Link_3");
	addCoat(xxl, black, 250, 30, "Link_4");
	addCoat(xl, blue, 350, 30, "Link_5");
	addCoat(xl, red, 350, 30, "Link_6");
	addCoat(xl, yellow, 350, 30, "Link_7");
	addCoat(l, red, 300, 30, "Link_8");
	addCoat(l, black, 300, 30, "Link_9");
	addCoat(s, yellow, 300, 30, "Link_10");

	std::vector<Coat> cart = getCart();
	std::vector<Coat> filter = getFilter();
	std::vector<Coat> all = getAll();
	std::string none = "\0";
	
	assert(getCartSize() == 0);
	assert(getFilterSize() == 0);
	assert(getSize() != 0);

	addToCart(xl, yellow, 350, 30, "Link_7");
	assert(getCartSize() == 1);

	filterSize(xxl);
	assert(getFilterSize() == 4);
}