#include "ui.h"
#include <iostream>

int main()
{
    Repository repo;
    Controller ctrl(repo);
    UI ui(ctrl);

    cout << "Testing repo...\n";
    repo.testRepo();
    cout << "Repo tested!\n";

    cout << "Testing controller...\n";
    ctrl.testCtrl();
    cout << "Controller tested!\n";

    ui.start();
}