#pragma once
#include <string>
#include <sstream>
#include <iostream>
#include <vector>

class Programmer
{
private:
	std::string name;
	int revisedFiles;
	int totalFiles;

public:
	Programmer();
	Programmer(std::string _name, int _revised, int _total);
	std::string getName();
	int getRevised();
	int getTotal();
	// setters?
	static std::vector<std::string> tokenize(std::string str, char delimiter);
	friend std::istream& operator>>(std::istream& is, Programmer& pr);
	~Programmer();
};