#include "SourceRepo.h"

void SourceRepo::loadData()
{
	std::ifstream file("sources.txt");
	SourceFile sf;
	this->files.clear();
	while (file >> sf)
	{
		this->files.push_back(sf);
	}
	file.close();
}

void SourceRepo::saveData()
{
	std::ofstream file("sources.txt");
	for (auto x : this->files)
		file << x << "\n";
	file.close();
}

SourceRepo::SourceRepo()
{
	this->loadData();
	for (int i = 0; i < this->files.size() - 1; i++)
		for (int j = i; j < this->files.size(); j++)
			if (this->files[i].getName() > this->files[j].getName())
			{
				SourceFile aux = this->files[i];
				this->files[i] = this->files[j];
				this->files[j] = aux;
			}
}

void SourceRepo::addSourceFile(SourceFile sf)
{
	for (auto x : this->files)
		if (x.getName() == sf.getName())
			throw std::exception("Already exists!");
	this->files.push_back(sf);
	this->saveData();
	this->notify();
}

void SourceRepo::setStatusFile(SourceFile sf, std::string str)
{
	for (auto x : this->getSourceFiles())
	{
		if (x.getName() == sf.getName())
		{
			x.setStatus("revised");
			x.setReviewer(str);
			
		}
	}
	this->saveData();
	this->notify();
}

std::vector<SourceFile>& SourceRepo::getSourceFiles()
{
	return this->files;
}

SourceRepo::~SourceRepo()
{
}
