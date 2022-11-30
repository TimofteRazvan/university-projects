#include "coat.h"
#include <assert.h>
#include <vector>
#include <sstream>

Coat::Coat()
	: size(""), colour(""), price(), quantity(), photograph("")
{
}

Coat::Coat(std::string _size, std::string _colour, int _price, int _quantity, std::string _photograph)
	: size(_size), colour(_colour), price(_price), quantity(_quantity), photograph(_photograph)
{
}

Coat::Coat(const Coat& rhs)
	: size(rhs.size), colour(rhs.colour), price(rhs.price), quantity(rhs.quantity), photograph(rhs.photograph)
{
}

std::string Coat::getSize() const
{
	return size;
}

std::string Coat::getColour() const
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

std::string Coat::getPhotograph() const
{
	return photograph;
}

void Coat::setSize(std::string _size)
{
	size = _size;
}

void Coat::setColour(std::string _colour)
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

void Coat::setPhotograph(std::string _photograph)
{
	photograph = _photograph;
}

std::vector<std::string> tokenize(std::string str, char delimiter)
{
	std::vector<std::string> result{};
	std::stringstream ss{ str };
	std::string token{};
	while (getline(ss, token, delimiter))
		result.push_back(token);
	return result;
}

std::istream& Coat::read(std::istream& is)
{
	std::string line;
	getline(is, line);
	std::vector<std::string> tokens = tokenize(line, ',');
	if (tokens.size() != 5)
		return is;
	this->size = tokens[0];
	this->colour = tokens[1];
	this->price = stoi(tokens[2]);
	this->quantity = stoi(tokens[3]);
	this->photograph = tokens[4];
	return is;
}

std::istream& Coat::operator>>(std::istream& is)
{
	return read(is);
}

std::ostream& Coat::operator<<(std::ostream& os)
{
	os << getSize() << "," << getColour() << "," << getPrice() << "," << getQuantity() << "," << getPhotograph() << "," << std::endl;
	return os;
}

bool Coat::operator==(const Coat& c) {
	if (c.size != size || c.colour != colour || c.photograph != photograph || c.quantity != quantity || c.price != price)
		return false;
	return true;
}


void Coat::testCoat()
{
	std::string red = "Red", yellow = "Yellow", blue = "Blue", black = "Black";
	std::string s = "S", l = "L", x = "X", xl = "XL", xxl = "XXL";
	Coat coat1(xxl, red, 250, 30, "Link_1");
	Coat coat2(xxl, yellow, 250, 30, "Link_2");
	assert(coat1.getColour() == red);
	assert(coat2.getPhotograph() == "Link_2");
	assert(coat1.getPrice() == 250);
	assert(coat1.getQuantity() == 30);
	assert(coat2.getSize() == xxl);
	coat1.setColour(black);
	coat1.setPhotograph("Link_6");
	coat1.setPrice(300);
	coat1.setQuantity(5);
	coat1.setSize(xl);
	assert(coat1.getColour() == black);
	assert(coat1.getPhotograph() == "Link_6");
	assert(coat1.getPrice() == 300);
	assert(coat1.getQuantity() == 5);
	assert(coat1.getSize() == xl);
}