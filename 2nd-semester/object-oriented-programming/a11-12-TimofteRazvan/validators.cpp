#include "validators.h"
#include <algorithm>

ValidException::ValidException(std::string _message) : message{ _message }
{
}

std::string ValidException::get_message() const
{
    return this->message;
}

void CoatValidator::validate(const Coat& coat)
{
    std::string errors;
    if (coat.getSize() != "XXL" && coat.getSize() != "XL" && coat.getSize() != "L" && coat.getSize() != "S")
        errors += "Invalid size!\n";
    if (coat.getPrice() < 1)
        errors += "Invalid price!\n";
    if (coat.getQuantity() < 0)
        errors += "Invalid quantity!\n";

    if (errors.size() > 0)
        throw ValidException(errors);
}

void RepositoryValidator::validate_addCoat(Repo& repo, Coat coat) {
    std::vector<Coat> all = repo.getAll();
    auto it = std::find(all.begin(), all.end(), coat);
    std::string errors;
    if (it != all.end())
        errors += "Coat already exists!\n";
    //if (!errors.empty())
        //throw ValidException(errors);
}

void RepositoryValidator::validate_deleteCoat(Repo& repo, std::string photo) {
    std::vector<Coat> all = repo.getAll();
    bool ok = 1;
    for (auto coat : all)
    {
        if (coat.getPhotograph() == photo)
            ok = 0;
    }
    std::string errors;
    if (ok)
        errors += "Coat not found!\n";
    if (!errors.empty())
        throw ValidException(errors);
}

void RepositoryValidator::validate_updateCoat(Repo& repo, std::string photo) {
    std::vector<Coat> all = repo.getAll();
    bool ok = 1;
    for (auto coat : all)
    {
        if (coat.getPhotograph() == photo)
            ok = 0;
    }
    std::string errors;
    if (ok)
        errors += "Coat not found!\n";
    if (!errors.empty())
        throw ValidException(errors);
}