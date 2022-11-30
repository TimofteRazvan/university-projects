#pragma once
#include "cart.h"

class CartHTML : public Cart {
private:
    std::string path;
public:
    CartHTML(std::string path);

    void writeToFile() const override;

    void readFromFile() const override;
};