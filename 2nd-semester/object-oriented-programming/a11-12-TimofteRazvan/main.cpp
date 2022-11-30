#include "controller.h"
#include <QtWidgets/QApplication>
#include "repository.h"
#include "cartCSV.h"
#include "gui.h"
#include <memory>
//#include "Assignment_10_11.h"

int main(int argc, char* argv[])
{
	QApplication a(argc, argv);

	Repo repo;
	Cart* cart;
	cart = new CartCSV("CoatsCSV.csv");
	Controller ctrl(repo, cart);
	//Assignment_10_11 gui{ctrl};
	//gui.show();
	SecondaryGUI secgui(ctrl);
	GUI gui(ctrl, secgui);
	gui.show();
	return a.exec();
}