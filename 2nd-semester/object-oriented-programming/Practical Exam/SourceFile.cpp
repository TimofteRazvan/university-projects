#include "SourceFile.h"

SourceFile::SourceFile()
{
}

SourceFile::SourceFile(std::string _name, std::string _status, std::string _creator, std::string _reviewer)
	: name{ _name }, status{ _status }, creator{ _creator }, reviewer{ _reviewer }
{
}

std::string SourceFile::getName()
{
	return this->name;
}

std::string SourceFile::getStatus()
{
	return this->status;
}

std::string SourceFile::getCreator()
{
	return this->creator;
}

std::string SourceFile::getReviewer()
{
	return this->reviewer;
}

void SourceFile::setStatus(std::string _status)
{
	this->status = _status;
}

void SourceFile::setCreator(std::string _creator)
{
	this->creator = _creator;
}

void SourceFile::setReviewer(std::string _reviewer)
{
	this->reviewer = _reviewer;
}

std::vector<std::string> SourceFile::tokenize(std::string str, char delimiter)
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

SourceFile::~SourceFile()
{
}

std::istream& operator>>(std::istream& is, SourceFile& sf)
{
	std::string line;
	getline(is, line);
	std::vector<std::string> tokens = SourceFile::tokenize(line, ';');
	if (tokens.size() != 4)
		return is;
	sf.name = tokens[0];
	sf.status = tokens[1];
	sf.creator = tokens[2];
	sf.reviewer = tokens[3];
	return is;
}

std::ostream& operator<<(std::ostream& os, const SourceFile& sf)
{
	os << sf.name << ";" << sf.status << ";" << sf.creator << ";" << sf.reviewer;
	return os;
}
