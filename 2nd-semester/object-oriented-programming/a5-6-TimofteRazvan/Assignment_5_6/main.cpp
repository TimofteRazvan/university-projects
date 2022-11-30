#include "ui.h"

int main()
{
	Repo repo;
	Controller ctrl(repo);
	UI ui(ctrl);
	
	cout << "Beginning Repository tests...\n";
	cout << "...\n";
	repo.testRepo();
	cout << "...\n";
	cout << "Repository successfully tested.\n";

	cout << "\nBeginning Controller tests...\n";
	cout << "...\n";
	ctrl.testCtrl();
	cout << "...\n";
	cout << "Controller successfully tested.\n\n";

	ui.start();

	return 0;
}
