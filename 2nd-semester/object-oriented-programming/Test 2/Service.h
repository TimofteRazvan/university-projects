#pragma once
#include "HospitalDepartment.h"
#include <vector>

class Service
{
private:
	vector<HospitalDepartment*> departments;
public:
	Service();
	void addDepartment(HospitalDepartment* depart);
	void initDepartments();
	vector<HospitalDepartment*> getAllDepartments();
	vector<HospitalDepartment*> getAllEfficientDepartments();
	void writeToFile(string fileName);
	~Service();
};