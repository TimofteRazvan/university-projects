#include "Assignment_10_11.h"
#include <qmessagebox.h>
#include <qpainter.h>

Assignment_10_11::Assignment_10_11(Controller& ctrl, QWidget *parent)
    : QMainWindow(parent), ctrl{ctrl}
{
    ui.setupUi(this);
	this->populateList();
	this->connectSignalsAndSlots();
}

void Assignment_10_11::connectSignalsAndSlots()
{
	QObject::connect(this->ui.deleteButton, &QPushButton::clicked, this, &Assignment_10_11::deleteCoat);
}

void Assignment_10_11::populateList()
{
	ctrl.addCoat("XL", "Red", 20, 20, "Link1");
	ctrl.addCoat("XXL", "Red", 20, 20, "Link2");
	ctrl.addCoat("S", "Blue", 20, 20, "Link3");
	ctrl.addCoat("XL", "Red", 20, 20, "Link4");
	ctrl.addCoat("L", "Green", 20, 20, "Link5");
	ctrl.addCoat("XXL", "Black", 20, 20, "Link6");
	ctrl.addCoat("XXL", "Red", 20, 20, "Link7");
	ctrl.addCoat("S", "Pink", 20, 20, "Link8");
	ctrl.addCoat("L", "Red", 20, 20, "Link9");
	ctrl.addCoat("S", "Yellow", 20, 20, "Link10");
	this->ui.coatsListWidget->clear();
	// Can sort them here before adding them to the ListWidget, with a sort function done in the repo
	// ctrl.sort();
	std::vector<Coat> all = ctrl.getAll();
	int counter = 0;
	QFont font;
	font.setBold(true);
	QRadialGradient gradient(0, 0, 50, 0, 0);
	QBrush brush(gradient);
	brush.setColor(Qt::red);
	for (auto coat : all)
	{
		this->ui.coatsListWidget->addItem(QString::fromStdString(coat.getSize() + " - " + coat.getColour() + " - " + coat.getPhotograph()));
		if (coat.getColour() == "Red")
		{
			this->ui.coatsListWidget->item(counter)->setFont(font);
			this->ui.coatsListWidget->item(counter)->setBackground(brush);
		}
		counter += 1;
	}
}

int Assignment_10_11::getSelectedIndex() const
{
	QModelIndexList selectedIndexes = this->ui.coatsListWidget->selectionModel()->selectedIndexes();
	if (selectedIndexes.size() == 0)
	{
		this->ui.sizeLineEdit->clear();
		this->ui.colourLineEdit->clear();
		this->ui.photoLineEdit->clear();
		return -1;
	}
	int selectedIndex = selectedIndexes.at(0).row();
	return selectedIndex;
}

void Assignment_10_11::deleteCoat()
{
	int selectedIndex = this->getSelectedIndex();
	if (selectedIndex < 0)
	{
		QMessageBox::critical(this, "Error", "No coat was selected!");
		return;
	}
	std::vector<Coat> coats = this->ctrl.getAll();
	Coat coat = coats[selectedIndex];
	this->ctrl.deleteCoat(coat.getPhotograph());
	this->populateList();
}

void Assignment_10_11::showTotalPrice()
{
	std::string given_size = this->ui.givenSizeLineEdit->text().toStdString();
	std::vector<Coat> all = this->ctrl.getAll();
	int total_price = 0;
	for (auto x : all)
	{
		if (x.getSize() == given_size)
			total_price += x.getPrice();
	}
	std::string total = std::to_string(total_price);
	if (total_price != 0)
	{
		this->ui.totalPriceLabel->setText(QString::fromStdString(total));
	}
	else
	{
		QMessageBox::critical(this, "Error", "No coats with given size!");
		return;
	}
}

void Assignment_10_11::addCoat()
{
	std::string size = this->ui.sizeLineEdit->text().toStdString();
	std::string colour = this->ui.colourLineEdit->text().toStdString();
	std::string photo = this->ui.photoLineEdit->text().toStdString();

	this->ctrl.addCoat(size, colour, 10, 10, photo);

	this->populateList();
	int lastElement = this->ctrl.getAll().size() - 1;
	this->ui.coatsListWidget->setCurrentRow(lastElement);
}