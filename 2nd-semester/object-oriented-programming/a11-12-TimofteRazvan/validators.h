#pragma once
#include <string>
#include "coat.h"
#include "repository.h"
#include "controller.h"

// The exception that we throw in order to print the errors
class ValidException {
private:
    std::string message;
public:
    // ValidException creator with given 'errors' string
    ValidException(std::string _message);
    // Returns the message (errors string)
    std::string get_message() const;

};

// Coat Validator class, validates Coat objects
class CoatValidator {
public:
    // The function we call via CoatValidator to validate a given coat
    static void validate(const Coat& coat);
};

// Repository Validator class, validates the repository functions and finds exceptions in the repository operations
class RepositoryValidator {
public:
    // Checks if the to-be-added coat already exists, throws exception if TRUE
    static void validate_addCoat(Repo& repo, Coat coat);

    // Checks if the to-be-deleted coat exists, throws exception if FALSE
    static void validate_deleteCoat(Repo& repo, std::string photo);

    // Checks if the to-be-updated coat exists, throws exception if FALSE
    static void validate_updateCoat(Repo& repo, std::string photo);
};
