#include "coat.h"

Coat::Coat()
	: size(""), colour(""), price(), quantity(), photograph("")
{
}

Coat::Coat(string _size, string _colour, int _price, int _quantity, string _photograph)
	: size(_size), colour(_colour), price(_price), quantity(_quantity), photograph(_photograph)
{
}

Coat::Coat(const Coat& rhs)
	: size(rhs.size), colour(rhs.colour), price(rhs.price), quantity(rhs.quantity), photograph(rhs.photograph)
{
}

string Coat::getSize() const
{
	return size;
}

string Coat::getColour() const
{
	return colour;
}

int Coat::getPrice() const
{
	return price;
}

int Coat::getQuantity() const
{
	return quantity;
}

string Coat::getPhotograph() const
{
	return photograph;
}

void Coat::setSize(string _size)
{
	size = _size;
}

void Coat::setColour(string _colour)
{
	colour = _colour;
}

void Coat::setPrice(int _price)
{
	price = _price;
}

void Coat::setQuantity(int _quantity)
{
	quantity = _quantity;
}

void Coat::setPhotograph(string _photograph)
{
	photograph = _photograph;
}