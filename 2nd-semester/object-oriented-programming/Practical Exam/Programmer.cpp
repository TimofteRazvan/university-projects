#include "Programmer.h"

Programmer::Programmer()
{
}

Programmer::Programmer(std::string _name, int _revised, int _total)
	: name{ _name }, revisedFiles{ _revised }, totalFiles{ _total }
{
}

Programmer::~Programmer()
{

}

std::string Programmer::getName()
{
	return this->name;
}

int Programmer::getRevised()
{
	return this->revisedFiles;
}

int Programmer::getTotal()
{
	return this->totalFiles;
}

std::vector<std::string> Programmer::tokenize(std::string str, char delimiter)
{
	std::vector<std::string> tokens;
	std::stringstream ss(str);
	std::string line;
	while (getline(ss, line, delimiter))
	{
		tokens.push_back(line);
	}
	return tokens;
}

std::istream& operator>>(std::istream& is, Programmer& pr)
{
	std::string line;
	getline(is, line);
	std::vector<std::string> tokens = Programmer::tokenize(line, ';');
	if (tokens.size() != 3)
		return is;
	pr.name = tokens[0];
	pr.revisedFiles = stoi(tokens[1]);
	pr.totalFiles = stoi(tokens[2]);
	return is;
}