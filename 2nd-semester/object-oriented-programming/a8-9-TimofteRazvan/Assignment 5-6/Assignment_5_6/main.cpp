#include "ui.h"

int main()
{
	Repo repo;
	Controller ctrl(repo);
	UI ui(ctrl);

	std::cout << "Beginning Domain tests...\n";
	std::cout << "...\n";
	//coat.testCoat();
	std::cout << "...\n";
	std::cout << "Domain successfully tested.\n";

	std::cout << "\nBeginning Dynamic Vector tests...\n";
	std::cout << "...\n";
	//dynamic.testDynamicVector();
	std::cout << "...\n";
	std::cout << "Dynamic Vector successfully tested.\n";
	
	std::cout << "\nBeginning Repository tests...\n";
	std::cout << "...\n";
	//repo.testRepo();
	std::cout << "...\n";
	std::cout << "Repository successfully tested.\n";

	std::cout << "\nBeginning Controller tests...\n";
	std::cout << "...\n";
	//ctrl.testCtrl();
	std::cout << "...\n";
	std::cout << "Controller successfully tested.\n\n";

	ui.start();

	return 0;
}
