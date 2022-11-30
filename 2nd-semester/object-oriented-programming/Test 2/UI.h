#pragma once
#include "Service.h"

class UI
{
private:
	Service serv;
public:
	UI(Service service);
	void addDepartment();
	void displayDepartments();
	void displayEfficientDepartments();
	void writeToFile();
	void start();
};