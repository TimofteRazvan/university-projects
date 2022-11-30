#include "ui.h"
#include <iostream>

UI::UI(Controller controller) : ctrl(controller)
{
}

void UI::addTask()
{
	string description;
	int duration = 0, priority = 0, value = 0;
	cout << "Description: ";
	cin >> description;
	cout << "Duration: ";
	cin >> duration;
	cout << "Priority: ";
	cin >> priority;
	value = ctrl.addTask(description, duration, priority);
	if (value)
	{
		cout << "Invalid task! Already exists.\n";
	}
	else
		cout << "Successfully added!\n";
}

void UI::displayAll()
{
	Task* all = ctrl.getAll();
	int size = ctrl.getSize();
	for (int i = 0; i < size; i++)
		cout << all[i].getDescription() << " " << all[i].getDuration() << " " << all[i].getPriority() << "\n";
}

void UI::displayMenu()
{
	cout << "\n=========================\n";
	cout << "1. Add task\n";
	cout << "2. Display tasks\n";
	cout << "3. Display tasks below a given priority in descending order\n";
	cout << "4. Exit\n";
	cout << "=========================\n";
}

void UI::displayByPriority()
{
	int priority;
	cout << "Priority: ";
	cin >> priority;
	Task* all = ctrl.getAll();
	int size = ctrl.getSize();
	Task* sorted = ctrl.sortDescending();
	for (int i = 0; i < size; i++)
		if (sorted[i].getPriority() < priority)
			cout << sorted[i].getDescription() << " " << sorted[i].getDuration() << " " << sorted[i].getPriority() << "\n";
}

int UI::start()
{
	int option = 0;
	while (true)
	{
		displayMenu();
		cout << "Option: ";
		cin >> option;
		if (option == 1)
			addTask();
		else if (option == 2)
			displayAll();
		else if (option == 3)
			displayByPriority();
		else if (option == 4)
			return 0;
		else if (option < 1 || option > 4)
			cout << "Invalid option!\n";
	}
}
