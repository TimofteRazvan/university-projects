#pragma once

#include <string>
#include <sstream>
using namespace std;

class HospitalDepartment
{
protected:
	string hospitalName;
	int numberOfDoctors;
public:
	HospitalDepartment(const string& name, int nrDoc);
	virtual bool isEfficient() const = 0;
	virtual string toString() const;
	~HospitalDepartment();
};

class NeonatalUnit : public HospitalDepartment
{
private:
	int numberOfMothers;
	int numberOfNewborns;
	double averageGrade;
public:
	NeonatalUnit(const string& name, int nrDoc, int nrMothers, int nrNewborns, double grade);
	bool isEfficient() const;
	string toString() const;
};

class Surgery : public HospitalDepartment
{
private:
	int numberOfPatients;
public:
	Surgery(const string& name, int nrDoc, int nrPatients);
	bool isEfficient() const;
	string toString() const;
};