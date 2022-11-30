#include "Repository.h"
#include <fstream>

void Repository::readFromFile()
{
	std::ifstream file(this->fileName);
	if (!file.is_open())
	{
		throw std::exception{ "File not opened!" };
	}
	Product product;
	while (file >> product)
	{
		this->shopping_list.push_back(product);
	}
	file.close();
}

Repository::Repository(std::string name)
{
	this->fileName = name;
	this->readFromFile();
}

int Repository::getSize()
{
	return shopping_list.size();
}

std::vector<Product> Repository::getAll()
{
	return this->shopping_list;
}

void Repository::sort()
{
	for (int i = 0; i < this->shopping_list.size() - 1; i++)
		for (int j = i + 1; j < this->shopping_list.size(); j++)
			if (this->shopping_list[i].getCategory() > this->shopping_list[j].getCategory())
			{
				Product aux;
				aux = this->shopping_list[i];
				this->shopping_list[i] = this->shopping_list[j];
				this->shopping_list[j] = aux;
			}
	for (int i = 0; i < this->shopping_list.size() - 1; i++)
		for (int j = i + 1; j < this->shopping_list.size(); j++)
			if (this->shopping_list[i].getCategory() == this->shopping_list[j].getCategory())
				if (this->shopping_list[i].getName() > this->shopping_list[j].getName())
				{
					Product aux;
					aux = this->shopping_list[i];
					this->shopping_list[i] = this->shopping_list[j];
					this->shopping_list[j] = aux;
				}
}
