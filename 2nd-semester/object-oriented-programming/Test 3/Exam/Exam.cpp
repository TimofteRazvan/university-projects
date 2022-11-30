#include "Exam.h"

Exam::Exam(Controller& ctrl, QWidget* parent)
    : QMainWindow(parent), ctrl{ ctrl }
{
    ui.setupUi(this);
    this->populateList();
}

void Exam::populateList()
{
    this->ui.shoppingListWidget->clear();
    std::string _quantity;
    ctrl.sort();
    for (auto prod : ctrl.getAll())
    {
        _quantity = std::to_string(prod.getQuantity());
        this->ui.shoppingListWidget->addItem(QString::fromStdString(prod.getCategory() + " - " + prod.getName() + " - " + _quantity));
    }
}

void Exam::showItems()
{
    this->ui.categoryListWidget->clear();
    std::string category = this->ui.categoryLineEdit->text().toStdString();
    std::string _quantity;
    std::vector<Product> filtered;
    for (auto prod : ctrl.getAll())
    {
        if (category == prod.getCategory())
        {
            filtered.push_back(prod);
        }
    }
    for (int i = 0; i < filtered.size() - 1; i++)
        for (int j = i + 1; j < filtered.size(); j++)
            if (filtered[i].getQuantity() < filtered[j].getQuantity())
            {
                Product aux;
                aux = filtered[i];
                filtered[i] = filtered[j];
                filtered[j] = aux;
            }
    for (auto prod : filtered)
    {
        _quantity = std::to_string(prod.getQuantity());
        this->ui.categoryListWidget->addItem(QString::fromStdString(prod.getCategory() + " - " + prod.getName() + " - " + _quantity));
    }
}
