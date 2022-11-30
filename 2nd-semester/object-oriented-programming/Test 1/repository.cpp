#include "repository.h"]
#include <assert.h>

Repository::Repository()
{
}

int Repository::addTask(const Task& task)
{
    int error = 0;
    Task* all = array.getAllElems();
    int size = array.getSize();
    Task newTask = task;
    for (int i = 0; i < size; i++)
    {
        if (all[i].getDescription() == newTask.getDescription())
            error = 1;
    }
    if (error)
        return 1;
    array.add(task);
    return 0;
        
}

Task* Repository::getAll()
{
    return array.getAllElems();
}

int Repository::getSize()
{
    return array.getSize();
}

Task* Repository::sortDescending()
{
    int size = getSize();
    Task* all = getAll();
    Task copy;
    Task* sorted = all;
    for (int i = 0; i < size - 1; i++)
        for (int j = i; j < size; j++)
            if (sorted[i].getDuration() < sorted[j].getDuration())
            {
                copy = sorted[i];
                sorted[i] = sorted[j];
                sorted[j] = copy;
            }
    return sorted;
}

void Repository::testRepo()
{
    Repository repo;
    Task task1("Task1", 25, 3);
    Task task2("Task2", 25, 3);
    Task task2copy("Task2", 25, 3);
    Task task3("Task3", 25, 3);
    assert(repo.getSize() == 0);
    repo.addTask(task1);
    assert(repo.getSize() == 1);
    repo.addTask(task2);
    assert(repo.getSize() == 2);
    repo.addTask(task2copy);
    assert(repo.getSize() == 2);
    Task* all = repo.getAll();
    assert(all[0].getDescription() == task1.getDescription());
}


