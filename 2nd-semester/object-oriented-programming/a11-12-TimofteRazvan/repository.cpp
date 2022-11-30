#include "repository.h"
#include <algorithm>
#include <fstream>

Repo::Repo()
{
	this->readFromFile();
}

void Repo::readFromFile()
{
	std::ifstream file("Coats.txt");
	if (!file.is_open())
	{
		throw std::exception{ "File not opened!" };
	}
	Coat coat;
	while (coat.operator>>(file))
	{
		this->array.push_back(coat);
	}
	file.close();
}

void Repo::addCoat(const Coat& newCoat)
{
	//int size = array.getSize();
	int size = array.size();
	//Coat* all = array.getAllElems();

	//if (array.size() == array.capacity())
	//	array.resize(array.capacity() * 2);
	for (auto x : array)
	{
		if (newCoat.getPhotograph() == x.getPhotograph())
			return;
	}

	/*
	for (int i = 0; i < size; i++)
	{
		if (newCoat.getPhotograph() == array[i].getPhotograph())
			return 1;
	}
	*/
	//array + newCoat;
	//newCoat + array;
	//array.operator+(newCoat);
	try 
	{
		array.push_back(newCoat);
	}
	catch (RepositoryException& e)
	{
		std::cout << "Exception caught: " << std::endl;
		std::cout << e.what() << std::endl;
	}
	catch (std::exception& e)
	{
		std::cout << "Exception caught: " << std::endl;
		std::cout << e.what() << std::endl;
	}
}

void Repo::addFilter(const Coat& newCoat)
{
	this->filter.push_back(newCoat);
}

Coat Repo::getCoat(std::string photo)
{
	for (auto x : array)
		if (x.getPhotograph() == photo)
			return x;
	Coat bad("S", "Red", 1, 1, "Link");
	return bad;
}

void Repo::deleteCoat(std::string photo)
{
	//int size = array.getSize();
	int size = array.size();
	//Coat* all = array.getAllElems();
	std::vector<Coat>::iterator it;

	it = array.begin();
	int i = 0;
	for (auto x : array)
	{
		if (photo == x.getPhotograph())
		{
			it += i;
			try 
			{
				array.erase(it);
			}
			catch (RepositoryException& e)
			{
				std::cout << "Exception caught: " << std::endl;
				std::cout << e.what() << std::endl;
			}
			catch (std::exception& e)
			{
				std::cout << "Exception caught: " << std::endl;
				std::cout << e.what() << std::endl;
			}
		}
		i += 1;
	}
	/*
	for (int i = 0; i < size; i++)
	{
		if (photo == array[i].getPhotograph())
		{
			it += i;
			array.erase(it);
			return 0;
		}
	}
	*/
}

void Repo::updateCoat(std::string photo, std::string _size, std::string _colour, int _price, int _quantity, std::string _photo)
{
	//int size = array.getSize();
	int size = array.size();
	//Coat* all = array.getAllElems();
	std::vector<Coat>::iterator it;

	it = array.begin();
	int i = 0;
	for (auto x : array)
	{
		if (photo == x.getPhotograph())
		{
			if (_quantity == 0)
			{
				it += i;
				array.erase(it);
			}
			else
			{
				array[i].setSize(_size);
				array[i].setColour(_colour);
				array[i].setPrice(_price);
				array[i].setQuantity(_quantity);
				array[i].setPhotograph(_photo);
			}
		}
		i += 1;
	}
	/*
	for (int i = 0; i < size; i++)
	{
		if (photo == array[i].getPhotograph())
		{
			if (_quantity == 0)
			{
				it += i;
				array.erase(it);
			}
			else
			{
				array[i].setSize(_size);
				array[i].setColour(_colour);
				array[i].setPrice(_price);
				array[i].setQuantity(_quantity);
				array[i].setPhotograph(_photo);
			}
			return 0;
		}
	}
	*/
}

std::vector<Coat> Repo::getAll()
{
	return array;
}

std::vector<Coat> Repo::getCoats() const
{
	return array;
}

std::vector<Coat> Repo::getCart() const
{
	return cart;
}

std::vector<Coat> Repo::getFilter()
{
	return filter;
}

int Repo::getSize() const
{
	return array.size();
}

int Repo::getCartSize() const
{
	return cart.size();
}

int Repo::getFilterSize() const
{
	return filter.size();
}

void Repo::filterSize(std::string size)
{
	/*
	std::vector<Coat> all;
	all = getAll();
	std::vector<Coat> filtered(all.size());

	auto it = std::copy_if(all.begin(), all.end(), filtered.begin(), [size](Coat coat) {return coat.getSize().find(size) == NULL; });
	filtered.resize(std::distance(filtered.begin(), it));
	std::copy(filtered.begin(), filtered.end(), std::back_inserter(filter));
	*/
	int capacity = array.size();
	std::vector<Coat> all;
	std::copy(array.begin(), array.end(), std::back_inserter(all));
	
	


	//Coat* all = array.getAllElems();
	
	// Coat* all = array.data();
	if (size != "null")
	{
		for (auto x : array)
		{
			if (size == x.getSize())
				filter.push_back(x);
		}
		for (int i = 0; i < capacity; i++)
		{
			if (size == all[i].getSize())
				filter.push_back(all[i]);
		}
	}
	else
	{
		for (auto x : array)
		{
			filter.push_back(x);
		}
		for (int i = 0; i < capacity; i++)
		{
			filter.push_back(all[i]);
		}
	}
}

void Repo::addToCart(const Coat& newCoat)
{
	//int size = cart.getSize();
	int size = cart.size();
	//Coat* all = cart.getAllElems();
	Coat* all = array.data();
	cart.push_back(newCoat);
}

void Repo::deleteFromCart(const Coat& coat)
{
	int size = array.size();
	std::string photo = coat.getPhotograph();
	std::vector<Coat>::iterator it;

	it = array.begin();
	int i = 0;
	for (auto x : array)
	{
		if (photo == x.getPhotograph())
		{
			it += i;
			try
			{
				array.erase(it);
			}
			catch (RepositoryException& e)
			{
				std::cout << "Exception caught: " << std::endl;
				std::cout << e.what() << std::endl;
			}
			catch (std::exception& e)
			{
				std::cout << "Exception caught: " << std::endl;
				std::cout << e.what() << std::endl;
			}
		}
		i += 1;
	}
}

void Repo::filterClear()
{
	this->filter.clear();
}

/*
template<typename T1>
void sortAll(std::vector<T1> elements, Comparator<T1>* typecomp)
{
	for (int i = 0; i < elements.size() - 1; ++i)
		for (int j = i + 1; i < elements.size(); ++j)
			if (typecomp->compare(elements[i], elements[j]))
			{
				elements.push_back(elements[i]);
				elements[i] = elements[j];
				elements[j] = elements[elements.size() - 1];
				elements.pop_back();
			}
}
*/

/*
void Repo::testRepo()
{
	//Repo repo;
	Coat coat("XXL", "Blue", 100, 15, "Link_1");
	assert(array.size() == 0);
	//assert(addCoat(coat) == 0);
	assert(array.size() == 1);
	Coat coat_copy("XL", "Red", 100, 20, "Link_1");
	//assert(addCoat(coat_copy) == 1);
	assert(array.size() == 1);

	deleteCoat("Link_1");
	assert(array.size() == 0);
	addCoat(coat_copy);
	deleteCoat("Link");
	assert(array.size() == 1);
	//assert(deleteCoat("Link_1") == 0);
	//assert(deleteCoat("Link") == 1);

	addCoat(coat);
	//assert(updateCoat("Link_1", "XL", "Blue", 100, 50, "Link") == 0);
	//assert(updateCoat("Link_1", "L", "Red", 100, 50, "Link") == 1);

	std::string red = "Red", yellow = "Yellow", blue = "Blue", black = "Black";
	std::string s = "S", l = "L", x = "X", xl = "XL", xxl = "XXL";
	Coat coat1(xxl, red, 250, 30, "Link_1");
	Coat coat2(xxl, yellow, 250, 30, "Link_2");
	Coat coat3(xxl, blue, 250, 30, "Link_3");
	Coat coat4(xxl, black, 250, 30, "Link_4");
	Coat coat5(xl, blue, 350, 30, "Link_5");
	Coat coat6(xl, red, 350, 30, "Link_6");
	Coat coat7(xl, yellow, 350, 30, "Link_7");
	Coat coat8(l, red, 300, 30, "Link_8");
	Coat coat9(l, black, 300, 30, "Link_9");
	Coat coat10(s, yellow, 300, 30, "Link_10");
	addCoat(coat1);
	addCoat(coat2);
	addCoat(coat3);
	addCoat(coat4);
	addCoat(coat5);
	addCoat(coat6);
	addCoat(coat7);
	addCoat(coat8);
	addCoat(coat9);
	addCoat(coat10);
	
	std::vector<Coat> cart = getCart();
	std::vector<Coat> filter = getFilter();
	std::vector<Coat> all = getAll();
	std::string none = "\0";
	assert(getCartSize() == 0);
	assert(getFilterSize() == 0);
	assert(getSize() != 0);
	addToCart(coat7);
	assert(getCartSize() == 1);

	filterSize(xxl);
	assert(getFilterSize() == 4);
}
*/