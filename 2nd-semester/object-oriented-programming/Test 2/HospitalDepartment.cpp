#include "HospitalDepartment.h"

HospitalDepartment::HospitalDepartment(const string& name, int nrDoc)
{
    this->hospitalName = name;
    this->numberOfDoctors = nrDoc;
}

string HospitalDepartment::toString() const
{
    stringstream ss;
    ss << hospitalName << ", " << numberOfDoctors;
    return ss.str();
}

HospitalDepartment::~HospitalDepartment()
{
}

NeonatalUnit::NeonatalUnit(const string& name, int nrDoc, int nrMothers, int nrNewborns, double grade)
    : HospitalDepartment {name, nrDoc}, numberOfMothers {nrMothers}, numberOfNewborns {nrNewborns}, averageGrade {grade}
{
}

bool NeonatalUnit::isEfficient() const
{
    if (this->averageGrade > 8.5 && this->numberOfNewborns >= this->numberOfMothers)
        return true;
    return false;
}

string NeonatalUnit::toString() const
{
    stringstream ss;
    ss << "Name: " << hospitalName << ", Nr. Doctors: " << numberOfDoctors << ", Nr. Mothers: " << numberOfMothers << ", Nr. Newborns: " << numberOfNewborns << ", Grade: " << averageGrade;
    return ss.str();
}

Surgery::Surgery(const string& name, int nrDoc, int nrPatients)
    : HospitalDepartment {name, nrDoc}, numberOfPatients {nrPatients}
{
}

bool Surgery::isEfficient() const
{
    if (numberOfPatients / numberOfDoctors >= 2)
        return true;
    return false;
}

string Surgery::toString() const
{
    stringstream ss;
    ss << "Name: " << hospitalName << ", Nr. Doctors: " << numberOfDoctors << ", Nr. Patients: " << numberOfPatients;
    return ss.str();
}
