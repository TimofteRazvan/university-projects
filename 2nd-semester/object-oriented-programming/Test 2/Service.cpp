#include "Service.h"
#include <fstream>
#include <iterator>

Service::Service()
{
}

void Service::addDepartment(HospitalDepartment* depart)
{
    departments.push_back(depart);
}

void Service::initDepartments()
{
    HospitalDepartment* department_1 = new NeonatalUnit{ "Spitalul Copiilor", 20, 10, 11, 10.0 };
    HospitalDepartment* department_2 = new NeonatalUnit{ "Spitalul Noua Sansa", 50, 70, 80, 5.0 };
    HospitalDepartment* department_3 = new Surgery{ "Spitalul Surgical", 20, 50 };
    departments.push_back(department_1);
    departments.push_back(department_2);
    departments.push_back(department_3);
}

vector<HospitalDepartment*> Service::getAllDepartments()
{
    return departments;
}

vector<HospitalDepartment*> Service::getAllEfficientDepartments()
{
    vector<HospitalDepartment*> efficient;
    for (auto x : departments)
    {
        if (x->isEfficient())
            efficient.push_back(x);
    }
    return efficient;
}

void Service::writeToFile(string fileName)
{
    ofstream g{ fileName };
    for (auto x : departments)
    {
        g << x->toString() << "\n";
    }
}

Service::~Service()
{
    for (auto x : departments)
    {
        delete x;
    }
}
