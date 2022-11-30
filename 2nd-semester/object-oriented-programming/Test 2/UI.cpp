#include "UI.h"
#include <iostream>

UI::UI(Service service) : serv {service}
{
}

void UI::addDepartment()
{
	string name, type;
	int nrDoc;
	cout << "Hospital name: ";
	cin >> name;
	cout << "Number of doctors: ";
	cin >> nrDoc;
	cout << "Type of department: ";
	cin >> type;
	if (type == "NeonatalUnit")
	{
		int nrMothers, nrNewborns;
		double grade;
		cout << "Number of mothers: ";
		cin >> nrMothers;
		cout << "Number of newborns: ";
		cin >> nrNewborns;
		cout << "Average grade: ";
		cin >> grade;
		HospitalDepartment* department = new NeonatalUnit{name, nrDoc, nrMothers, nrNewborns, grade};
		serv.addDepartment(department);
	}
	else if (type == "Surgery")
	{
		int nrPatients;
		cout << "Number of patients: ";
		cin >> nrPatients;
		HospitalDepartment* department = new Surgery{name, nrDoc, nrPatients};
		serv.addDepartment(department);
	}
	else
		cout << "Invalid department type.";
	
}

void UI::displayDepartments()
{
	vector<HospitalDepartment*> departments = serv.getAllDepartments();
	for (auto x : departments)
	{
		cout << x->toString() << "\n";
	}
}

void UI::displayEfficientDepartments()
{
	vector<HospitalDepartment*> departments = serv.getAllEfficientDepartments();
	for (auto x : departments)
	{
		cout << x->toString() << "\n";
	}
}

void UI::writeToFile()
{
	string fileName;
	cout << "File name: ";
	cin >> fileName;
	serv.writeToFile(fileName);
}

void UI::start()
{
	int option;
	serv.initDepartments();
	while (true)
	{
		cout << "Option: ";
		cin >> option;
		if (option == 1)
		{
			addDepartment();
		}
		else if (option == 2)
		{
			displayDepartments();
		}
		else if (option == 3)
		{
			displayEfficientDepartments();
		}
		else if (option == 4)
		{
			writeToFile();
		}
		else
			return;
	}
}
