#pragma once
#include "cart.h"

class CartCSV : public Cart {
private:
    std::string path;
public:
    CartCSV(std::string path);

    void writeToFile() const override;

    void readFromFile() const override;

};
