#include "controller.h"
#include <assert.h>

Controller::Controller(Repository repository) : repo(repository)
{
}

int Controller::addTask(string description, int duration, int priority)
{
	int value;
	Task task(description, duration, priority);
	value = repo.addTask(task);
	return value;
}

Task* Controller::getAll()
{
	return repo.getAll();
}

int Controller::getSize()
{
	return repo.getSize();
}

Task* Controller::sortDescending()
{
    return repo.sortDescending();
}

void Controller::testCtrl()
{
    Repository repo;
    Controller ctrl(repo);
    assert(ctrl.getSize() == 0);
    ctrl.addTask("Task1", 25, 3);
    assert(ctrl.getSize() == 1);
    ctrl.addTask("Task2", 25, 3);
    assert(ctrl.getSize() == 2);
    ctrl.addTask("Task2", 25, 3);
    assert(ctrl.getSize() == 2);
    Task* all = ctrl.getAll();
    assert(all[0].getDescription() == "Task1");
}
