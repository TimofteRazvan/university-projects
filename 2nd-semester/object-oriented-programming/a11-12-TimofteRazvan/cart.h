#pragma once
#include "repository.h"
#include <windows.h>

class Cart
{
protected:
    Repo coats;
public:
	void addToCart(const Coat& coat);

    std::vector<Coat> getCoats() const;

	virtual void writeToFile() const = 0;

	virtual void readFromFile() const = 0;
};