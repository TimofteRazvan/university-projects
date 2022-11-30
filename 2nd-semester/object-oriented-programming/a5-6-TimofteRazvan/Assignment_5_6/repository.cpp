#include "repository.h"

Repo::Repo()
{
}

int Repo::addCoat(const Coat& newCoat)
{
	int size = array.getSize();
	Coat* all = array.getAllElems();
	for (int i = 0; i < size; i++)
	{
		if (newCoat.getPhotograph() == all[i].getPhotograph())
			return 1;
	}
	array.add(newCoat);
	return 0;
}

int Repo::deleteCoat(string photo)
{
	int size = array.getSize();
	Coat* all = array.getAllElems();
	for (int i = 0; i < size; i++)
	{
		if (photo == all[i].getPhotograph())
		{
			array.del(i);
			return 0;
		}
	}
	return 1;
}

int Repo::updateCoat(string photo, int _price, int _quantity, string _photo)
{
	int size = array.getSize();
	Coat* all = array.getAllElems();
	for (int i = 0; i < size; i++)
	{
		if (photo == all[i].getPhotograph())
		{
			if (_quantity == 0)
				array.del(i);
			else
			{
				all[i].setPrice(_price);
				all[i].setQuantity(_quantity);
				all[i].setPhotograph(_photo);
			}
			return 0;
		}
	}
	return 1;
}

Coat* Repo::getAll() const
{
	return array.getAllElems();
}

int Repo::getSize() const
{
	return array.getSize();
}

void Repo::testRepo()
{
	Repo repo;
	Coat coat("XXL", "Blue", 100, 15, "Link_1");
	assert(array.getSize() == 0);
	assert(addCoat(coat) == 0);
	assert(array.getSize() == 1);
	Coat coat_copy("XL", "Red", 100, 20, "Link_1");
	assert(addCoat(coat_copy) == 1);
	assert(array.getSize() == 1);

	deleteCoat("Link_1");
	assert(array.getSize() == 0);
	addCoat(coat_copy);
	deleteCoat("Link");
	assert(array.getSize() == 1);
	assert(deleteCoat("Link_1") == 0);
	assert(deleteCoat("Link") == 1);

	addCoat(coat);
	assert(updateCoat("Link_1", 100, 50, "Link") == 0);
	assert(updateCoat("Link_1", 100, 50, "Link") == 1);
}