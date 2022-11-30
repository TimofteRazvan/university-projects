#pragma once
#include <string>
#include <iostream>
#include <vector>
#include <sstream>

class SourceFile
{
private:
	std::string name;
	std::string status;
	std::string creator;
	std::string reviewer;

public:
	SourceFile();
	SourceFile(std::string _name, std::string _status, std::string _creator, std::string _reviewer);
	std::string getName();
	std::string getStatus();
	std::string getCreator();
	std::string getReviewer();
	void setStatus(std::string _status);
	void setCreator(std::string _creator);
	void setReviewer(std::string _reviewer);
	static std::vector<std::string> tokenize(std::string str, char delimiter);
	friend std::istream& operator>>(std::istream& is, SourceFile& sf);
	friend std::ostream& operator<<(std::ostream& os, const SourceFile& sf);
	~SourceFile();
};