#pragma once
#include "SourceFile.h"
#include "Observer.h"
#include "Programmer.h"
#include <fstream>

class SourceRepo : public Observable
{
private:
	std::vector<SourceFile> files;
	
	void loadData();
	void saveData();

public:
	SourceRepo();
	void addSourceFile(SourceFile sf);
	void setStatusFile(SourceFile sf, std::string str);
	std::vector<SourceFile>& getSourceFiles();
	~SourceRepo();
};