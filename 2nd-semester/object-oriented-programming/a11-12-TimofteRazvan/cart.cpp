#include "cart.h"

void Cart::addToCart(const Coat& coat)
{
    coats.addCoat(coat);
}

std::vector<Coat> Cart::getCoats() const
{
    return coats.getCoats();
}
