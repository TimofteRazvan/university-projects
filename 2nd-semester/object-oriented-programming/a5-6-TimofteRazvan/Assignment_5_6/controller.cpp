#include "controller.h"
#include <iostream>

Controller::Controller(Repo repository)
	: repo(repository)
{
}

int Controller::addCoat(string size, string colour, int price, int quantity, string photograph)
{
	Coat newCoat(size, colour, price, quantity, photograph);
	int value = repo.addCoat(newCoat);
	return value;
}

int Controller::deleteCoat(string photo)
{
	int value = repo.deleteCoat(photo);
	return value;
}

int Controller::updateCoat(string photo, int _price, int _quantity, string _photo)
{
	int value = repo.updateCoat(photo, _price, _quantity, _photo);
	return value;
}

void Controller::generateCoats()
{
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
}

Coat* Controller::getAll() const
{
	return repo.getAll();
}

int Controller::getSize() const
{
	return repo.getSize();
}

void Controller::testCtrl()
{
	Repo repo;
	Controller ctrl(repo);
	assert(ctrl.getSize() == 0);
	assert(ctrl.addCoat("XXL", "Blue", 100, 15, "Link_2") == 0);
	assert(ctrl.getSize() == 1);
	assert(ctrl.addCoat("XL", "Red", 100, 20, "Link_2") == 1);
	assert(ctrl.getSize() == 1);

	ctrl.deleteCoat("Link_2");
	assert(ctrl.getSize() == 0);
	ctrl.addCoat("XL", "Red", 100, 20, "Link_2");
	ctrl.deleteCoat("Link");
	assert(ctrl.getSize() == 1);
	assert(ctrl.deleteCoat("Link_2") == 0);
	assert(ctrl.deleteCoat("Link") == 1);

	ctrl.addCoat("XXL", "Blue", 100, 15, "Link_2");
	assert(ctrl.updateCoat("Link_2", 100, 50, "Link") == 0);
	assert(ctrl.updateCoat("Link_2", 100, 50, "Link") == 1);
}