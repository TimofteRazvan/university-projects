#pragma once
#include "controller.h"

class UI
{
private:
	Controller ctrl;

public:
	UI(Controller controller);

	void addTask();
	void displayAll();
	void displayMenu();
	void displayByPriority();
	int start();
};