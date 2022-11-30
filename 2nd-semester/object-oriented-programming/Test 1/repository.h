#pragma once
#include "dynamicVector.h"
#include "task.h"

class Repository
{
private:
	DynamicVector<Task> array;

public:
	Repository();

	// This function communicates with the dynamic vector array to add a task
	// First it checks if it exists, and if it doesn't, then it gets added and returns a 0
	// If it does exist, it returns a 1 and doesn't get added
	int addTask(const Task& task);
	Task* getAll();
	int getSize();
	// Returns the sorted dynamic array, sorted descendingly by duration
	Task* sortDescending();
	void testRepo();
};