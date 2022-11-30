#pragma once
#include "controller.h"
#include <iostream>

class UI
{
private:
	Controller ctrl;

public:
	// UI creator
	UI(Controller controller);

	// Displays all elements of the repository
	void displayAll();

	// Displays the privileges selection
	void displayPrivileges();

	// Displays the menu for admin privileges
	void displayAdminMenu();

	// Adds a coat to the repo
	// Prints if successful or not
	void addCoat();

	// Deletes a coat from the repo
	// Prints if successful or not
	void deleteCoat();

	// Updates a coat from the repo
	// Prints if successful or not
	void updateCoat();

	// The big boy start function that we know and love
	void start();
};