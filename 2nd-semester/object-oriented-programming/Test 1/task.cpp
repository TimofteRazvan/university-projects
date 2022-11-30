#include "task.h"

Task::Task() : description(""), duration(), priority()
{
}

Task::Task(string _description, int _duration, int _priority)
	: description(_description), duration(_duration), priority(_priority)
{
}

Task::Task(const Task& rhs) : description(rhs.description), duration(rhs.duration), priority(rhs.priority)
{
}

string Task::getDescription()
{
	return description;
}

int Task::getDuration()
{
	return duration;
}

int Task::getPriority()
{
	return priority;
}

void Task::setDescription(string _description)
{
	description = _description;
}

void Task::setDuration(int _duration)
{
	duration = _duration;
}

void Task::setPriority(int _priority)
{
	priority = _priority;
}


