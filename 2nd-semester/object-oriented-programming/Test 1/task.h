#pragma once
#include <string.h>
#include <string>
using namespace std;

class Task
{
private:
	string description;
	int duration;
	int priority;
public:
	Task();
	Task(string _description, int _duration, int _priority);
	Task(const Task& rhs);
	string getDescription();
	int getDuration();
	int getPriority();

	void setDescription(string _description);
	void setDuration(int _duration);
	void setPriority(int _priority);
};