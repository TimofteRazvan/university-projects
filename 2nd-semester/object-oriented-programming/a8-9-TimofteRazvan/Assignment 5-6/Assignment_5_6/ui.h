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

	// Displays the menu for the user
	void displayUserMenu();

	// Displays one-by-one all the coats with a given size
	void displaySize();

	// Displays the shopping cart's contents
	void displayCart();

	// Adds a coat to the repo
	// Prints if successful or not
	void addCoat();

	// Deletes a coat from the repo
	// Prints if successful or not
	void deleteCoat();

	// Updates a coat from the repo
	// Prints if successful or not
	void updateCoat();

	// Using given fileName, writes the current Repository to the given file
	// Inside the function, it uses ostream, loops through the stream with and the overwritted operator '<<'
	void writeToFile(std::string fileName);

	// The big boy start function that we know and love
	void start();
};