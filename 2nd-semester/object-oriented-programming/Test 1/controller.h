#pragma once
#include "repository.h"

class Controller
{
private:
	Repository repo;
	
public:
	Controller(Repository repository);

	// This function communicates with the repository to add a task
	// First it checks if it exists IN THE REPO FUNCTION, and if it doesn't, then it gets added and returns a 0
	// If it does exist, it returns a 1 FROM THE REPO FUNCTION and doesn't get added
	int addTask(string description, int duration, int priority);
	Task* getAll();
	int getSize();
	// Returns the sorted dynamic array, sorted descendingly by duration
	Task* sortDescending();
	void testCtrl();
};