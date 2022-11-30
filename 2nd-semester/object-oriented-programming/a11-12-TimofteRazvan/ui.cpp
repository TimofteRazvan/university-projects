#include "ui.h"
#include "validators.h"
using namespace std;

UI::UI(Controller controller)
	: ctrl(controller)
{
}

void UI::displayAll()
{
	vector<Coat> all;
	all = ctrl.getAll();

	int capacity = ctrl.getSize();

	cout << "Here are all the coats registered in our database:\n";
	for (auto x : all)
	{
		string size, colour, photograph;
		int price, quantity;
		size = x.getSize();
		colour = x.getColour();
		price = x.getPrice();
		quantity = x.getQuantity();
		photograph = x.getPhotograph();
		cout << size << " " << colour << " " << price << " " << quantity << " " << photograph << "\n";
	}
	/*
	for (int i = 0; i < capacity; i++)
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
	*/
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

void UI::displayUserMenu()
{
	cout << "1. Add to cart\n";
	cout << "2. Next coat\n";
	cout << "3. View cart\n";
	cout << "4. Exit\n";
}

// TODO: NULL SHOWS ALL COATS

void UI::displaySize()
{
	string filter_size;
	int option = 0, capacity = 0, i = 0, total_price = 0;
	cout << "Size to peruse: ";
	cin >> filter_size;
	if (filter_size != "XXL" && filter_size != "XL" && filter_size != "L" && filter_size != "S" && filter_size != "null")
	{
		cout << "Invalid size!\n";
		return;
	}
	vector<Coat> filtered;
	ctrl.filterSize(filter_size);
	filtered = ctrl.getFilter();
	capacity = ctrl.getFilterSize();
	while (i < capacity)
	{
		string size, colour, photograph;
		int price, quantity, option;
		size = filtered[i].getSize();
		colour = filtered[i].getColour();
		price = filtered[i].getPrice();
		quantity = filtered[i].getQuantity();
		photograph = filtered[i].getPhotograph();
		cout << "<<< CURRENTLY VIEWING >>>\n";
		cout << "Size: " << size << "\n" << 
			"Colour: " << colour << "\n" << 
			"Price: " << price << "\n" << 
			"Quantity: " << quantity << "\n" << 
			"Photograph: " << photograph << "\n";
		displayUserMenu();
		cout << ">>> ";
		cin >> option;
		while (cin.fail())
		{
			cin.clear();
			cin.ignore(INT_MAX, '\n');
			cout << "Option must be an integer.\n";
		}
		if (option == 1)
		{
			Coat newCoat(size, colour, price, quantity, photograph);
			ctrl.addToCart(size, colour, price, quantity, photograph);
			ctrl.addToAbsCart(newCoat);
			total_price += price;
			cout << "Total Price: " << total_price << "\n";
		}
		else if (option == 2)
			i++;
		else if (option == 3)
		{
			ctrl.addToCart("XXL", "Secret", total_price, 1, "Zelda");
			displayCart();
		}
		else if (option == 4)
			return;
		else if (option <= 0 || option > 4)
			cout << "Invalid option!";
		if (i == capacity - 1)
			i = 0;
	}
}

void UI::displayCart()
{
	/*
	vector<Coat> cart;
	cart = ctrl.getCart();
	int cart_size = ctrl.getCartSize();
	cout << "=================================\n";
	cout << "Here are all the coats in your shopping cart:\n";
	for (auto x : cart)
	{
		string size, colour, photograph;
		int price, quantity;
		size = x.getSize();
		colour = x.getColour();
		price = x.getPrice();
		quantity = x.getQuantity();
		photograph = x.getPhotograph();
		cout << size << " " << colour << " " << price << " " << quantity << " " << photograph << "\n";
	}
	for (int i = 0; i < cart_size - 1; i++)
	{
		string size, colour, photograph;
		int price, quantity;
		size = cart[i].getSize();
		colour = cart[i].getColour();
		price = cart[i].getPrice();
		quantity = cart[i].getQuantity();
		photograph = cart[i].getPhotograph();
		cout << size << " " << colour << " " << price << " " << quantity << " " << photograph << "\n";
	}
	cout << "Total Price: " << cart[cart_size - 1].getPrice();
	cout << "\n=================================\n";
	*/
	ctrl.writeCartToFile();
	ctrl.readCartFromFile();
}

void UI::addCoat()
{
	string size, colour, photograph;
	int price, quantity;
	cout << "Size: ";
	cin >> size;

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

	cout << "Quantity: ";
	cin >> quantity;
	while (cin.fail())
	{
		cin.clear();
		cin.ignore(INT_MAX, '\n');
		cout << "Quantity must be an integer.\n";
		return;
	}

	cout << "Photograph: ";
	cin >> photograph;

	try
	{
		ctrl.addCoat(size, colour, price, quantity, photograph);
		cout << "Successfully added!\n";
	}
	catch (ValidException& err)
	{
		cout << "Errors occurred.\n";
		cout << err.get_message() << "\n";
	}
}

void UI::deleteCoat()
{
	string photo;
	cout << "Photograph of the coat to be deleted: ";
	cin >> photo;
	try
	{
		ctrl.deleteCoat(photo);
		cout << "Coat successfully deleted!\n";
	}
	catch (ValidException& err)
	{
		cout << "Errors occured.\n";
		cout << err.get_message() << "\n";
	}
	
}

void UI::updateCoat()
{
	string photo, _size, _colour, _photo;
	int _price, _quantity;
	cout << "Photograph of the coat to be updated: ";
	cin >> photo;

	cout << "New size: ";
	cin >> _size;

	cout << "New colour: ";
	cin >> _colour;

	cout << "New price: ";
	cin >> _price;
	while (cin.fail())
	{
		cin.clear();
		cin.ignore(INT_MAX, '\n');
		cout << "Price must be an integer.\n";
		return;
	}

	cout << "New quantity: ";
	cin >> _quantity;
	while (cin.fail())
	{
		cin.clear();
		cin.ignore(INT_MAX, '\n');
		cout << "Quantity must be an integer.\n";
		return;
	}

	cout << "New photo: ";
	cin >> _photo;
	try
	{
		ctrl.updateCoat(photo, _size, _colour, _price, _quantity, _photo);
		cout << "Coat successfully updated!\n";
	}
	catch (ValidException& err)
	{
		cout << "Errors occured.\n";
		cout << err.get_message() << "\n";
	}
	
}

void UI::writeToFile(string fileName)
{
	ofstream g{ fileName };
	vector<Coat> all = ctrl.getAll();
	for (auto coat : all)
	{
		coat.operator<<(g);
	}
}

template<typename T1>
void sortAll(std::vector<T1> elements, Comparator<T1>* typecomp)
{
	for (int i = 0; i < elements.size() - 1; ++i)
		for (int j = i + 1; j < elements.size(); ++j)
			if (typecomp->compare(elements[i], elements[j]))
			{
				elements.push_back(elements[i]);
				elements[i] = elements[j];
				elements[j] = elements[elements.size() - 1];
				elements.pop_back();
			}
	for (int i = 0; i < elements.size(); ++i)
		cout << elements[i].getSize() << " " << elements[i].getColour() << " " << elements[i].getPrice() << "\n";
}

void testSortAll()
{
	Comparator<Coat>* comp;
	comp = new ComparatorByAscendingPrice<Coat>;
	std::vector<Coat> elements;
	Coat coat1("XXL", "Yellow", 250, 30, "Link_2");
	Coat coat2("XXL", "Biggest", 300, 30, "Link_3");
	Coat coat3("XXL", "Black", 200, 30, "Link_4");
	Coat coat4("XL", "Smallest", 30, 30, "Link_5");
	Coat coat5("XL", "Red", 50, 30, "Link_6");
	elements.push_back(coat1);
	elements.push_back(coat2);
	elements.push_back(coat3);
	elements.push_back(coat4);
	elements.push_back(coat5);
	sortAll(elements, comp);
	assert(elements[3].getColour() == coat4.getColour());
	assert(elements[1].getColour() == coat2.getColour());
}

void UI::start()
{
	string fileName = "Coats.txt";
	ctrl.readCoats(fileName);
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
		while (cin.fail())
		{
			cin.clear();
			cin.ignore(INT_MAX, '\n');
			cout << "Option must be an integer.\n";
		}
		if (option == 1)
			addCoat();
		if (option == 2)
			deleteCoat();
		if (option == 3)
			updateCoat();
		if (option == 4)
			displayAll();
		if (option == 5)
		{
			writeToFile("Coats.txt");
			ctrl.writeCartToFile();
			return;
		}
		if (option < 1 || option > 5)
			cout << "Invalid option.\n";
	}
	while (privileges == 2)
		{
			displaySize();
			return;
		}
	while (privileges == 3)
	{
		Comparator<Coat>* comp;
		comp = new ComparatorByAscendingPrice<Coat>;
		std::vector<Coat> elements = ctrl.getAll();
		cout << "Testing sort:\n";
		testSortAll();
		cout << "Success!";
		return;
	}
	cout << "Invalid option.\n";
}