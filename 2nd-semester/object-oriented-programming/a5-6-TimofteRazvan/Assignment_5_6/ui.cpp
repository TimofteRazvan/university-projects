#include "ui.h"

UI::UI(Controller controller)
	: ctrl(controller)
{
}

void UI::displayAll()
{
	Coat* all;
	all = ctrl.getAll();

	int size = ctrl.getSize();

	cout << "Here are all the coats registered in our database:\n";
	for (int i = 0; i < size; i++)
	{
		string size, colour, photograph;
		int price, quantity;
		size = all[i].getSize();
		colour = all[i].getColour();
		price = all[i].getPrice();
		quantity = all[i].getQuantity();
		photograph = all[i].getPhotograph();
		cout << size << " " << colour << " " << price << " " << quantity << " " << photograph << "\n";
	}

}

void UI::displayPrivileges()
{
	cout << "=================================\n";
	cout << "Choose privileges:\n";
	cout << "1. Administrator\n";
	cout << "2. User\n";
	cout << "=================================\n";
}

void UI::displayAdminMenu()
{
	cout << "=================================\n";
	cout << "1. Add a coat\n";
	cout << "2. Delete a coat\n";
	cout << "3. Update a coat\n";
	cout << "4. Display coats in store\n";
	cout << "5. Exit\n";
	cout << "=================================\n";
}

void UI::addCoat()
{
	string size, colour, photograph;
	int price, quantity;
	cout << "Size: ";
	cin >> size;
	if (size != "XXL" && size != "XL" && size != "L" && size != "S")
	{
		cout << "Invalid size!\n";
		return;
	}
	cout << "Colour: ";
	cin >> colour;
	cout << "Price: ";
	cin >> price;
	while (cin.fail())
	{
		cin.clear();
		cin.ignore(INT_MAX, '\n');
		cout << "Price must be an integer.\n";
		return;
	}
	if (price < 1)
	{
		cout << "Price must be higher than 0.\n";
		return;
	}

	cout << "Quantity: ";
	cin >> quantity;
	while (cin.fail())
	{
		cin.clear();
		cin.ignore(INT_MAX, '\n');
		cout << "Quantity must be an integer.\n";
		return;
	}
	if (quantity < 0)
	{
		cout << "Quantity must be positive.\n";
		return;
	}
	cout << "Photograph: ";
	cin >> photograph;
	int value = ctrl.addCoat(size, colour, price, quantity, photograph);
	if (value)
		cout << "Coat already exists.\n";
	else
		cout << "Coat successfully added.\n";
}

void UI::deleteCoat()
{
	string photo;
	cout << "Photograph of the coat to be deleted: ";
	cin >> photo;
	int value = ctrl.deleteCoat(photo);
	if (value)
		cout << "Coat doesn't exist.\n";
	else
		cout << "Coat successfully deleted.\n";
}

void UI::updateCoat()
{
	string photo, _photo;
	int _price, _quantity;
	cout << "Photograph of the coat to be updated: ";
	cin >> photo;
	cout << "New price: ";
	cin >> _price;
	cout << "New quantity: ";
	cin >> _quantity;
	cout << "New photo: ";
	cin >> _photo;
	int value = ctrl.updateCoat(photo, _price, _quantity, _photo);
	if (value)
		cout << "Coat doesn't exist.\n";
	else
		cout << "Coat successfully updated.\n";
}

void UI::start()
{
	ctrl.generateCoats();
	int privileges;
	displayPrivileges();
	cout << ">>> ";
	cin >> privileges;
	while (privileges == 1)
	{
		int option;
		displayAdminMenu();
		cout << "Option: ";
		cin >> option;
		if (option == 1)
			addCoat();
		if (option == 2)
			deleteCoat();
		if (option == 3)
			updateCoat();
		if (option == 4)
			displayAll();
		if (option == 5)
			return;
		if (option < 1 || option > 5)
			cout << "Invalid option.\n";
	}
	while (privileges == 2)
		{
			int option;
			// displayUserMenu();
			return;
		}
	cout << "Invalid option.\n";
}