#include <fstream>
#include "cartHTML.h"

CartHTML::CartHTML(std::string path) {
    this->path = path;
}

void CartHTML::writeToFile() const {
    std::ofstream g(path);
    if (!g.is_open())
        return;
    g << "<!DOCTYPE html>\n<html>\n\t<head>\n\t\t<title>Cart</title>\n\t</head>\n\t<body>\n\t\t<table border=\"\">\n";
    g << "\t\t<tr>\n\t\t\t<td>Size</td>\n\t\t\t<td>Colour</td>\n\t\t\t<td>Price</td>\n\t\t\t<td>Quantity</td>\n\t\t\t<td>Link</td>\n\t\t</tr>\n";
    for (auto coat : coats.getCoats())
    {
        g << "\t\t<tr>\n";
        g << "\t\t\t<td>" << coat.getSize() << "</td>\n";
        g << "\t\t\t<td>" << coat.getColour() << "</td>\n";
        g << "\t\t\t<td>" << coat.getPrice() << "</td>\n";
        g << "\t\t\t<td>" << coat.getQuantity() << "</td>\n";
        g << "\t\t\t<td><a href = \"" << coat.getPhotograph() << "\">Link</a></td>\n";
        g << "\t\t</tr>\n";
    }
    g << "\t\t</table>\n\t</body>\n</html>\n";
    g.close();
}

void CartHTML::readFromFile() const {
    const char* fp = path.c_str();
    ShellExecute(NULL, L"open", L"CoatsHTML.html", NULL, NULL, SW_SHOWNORMAL);
}
