#include <fstream>
#include "cartCSV.h"


CartCSV::CartCSV(std::string path) {
    this->path = path;
}

void CartCSV::writeToFile() const {
    std::ofstream g(path);
    if (!g.is_open())
        return;
    for (auto coat : coats.getCoats())
    {
        coat.operator<<(g);
    }
    g.close();
}

void CartCSV::readFromFile() const {
    const char* fp = path.c_str();
    ShellExecute(NULL, L"open", L"CoatsCSV.csv", NULL, NULL, SW_SHOWNORMAL);
}
