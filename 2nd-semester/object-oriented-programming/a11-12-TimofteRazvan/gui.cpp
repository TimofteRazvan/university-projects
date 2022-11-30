#include "gui.h"
#include <qmessagebox.h>
#include <qurl.h>
#include <qdesktopservices.h>
#include "validators.h"

void GUI::initGUI()
{
	this->totalPriceWidget = new QLabel("0");
	this->coatsListWidget = new QListWidget();
	this->cartListWidget = new QListWidget();
	this->searchWidget = new QLineEdit();
	this->sizeWidget = new QLineEdit();
	this->colourWidget = new QLineEdit();
	this->priceWidget = new QLineEdit();
	this->quantityWidget = new QLineEdit();
	this->photoWidget = new QLineEdit();
	this->addWidget = new QPushButton("Add");
	this->deleteWidget = new QPushButton("Delete");
	this->updateWidget = new QPushButton("Update");
	this->filterWidget = new QPushButton("Filter");
	this->addCartWidget = new QPushButton("Add");
	this->nextWidget = new QPushButton("Next");
	this->undoWidget = new QPushButton("Undo");
	this->redoWidget = new QPushButton("Redo");
	this->windowWidget = new QPushButton("Window");
	this->undoShortcutWidget = new QShortcut(QKeySequence(Qt::CTRL + Qt::Key_Z), this);
	this->redoShortcutWidget = new QShortcut(QKeySequence(Qt::CTRL + Qt::Key_Y), this);
	this->current = 0;
	this->cap = this->ctrl.getSize();

	QVBoxLayout* mainLayout = new QVBoxLayout(this);
	
	QFormLayout* priceLayout = new QFormLayout(this);
	priceLayout->addRow("Total Price: ", totalPriceWidget);
	mainLayout->addLayout(priceLayout);

	QFormLayout* searchLayout = new QFormLayout();
	searchLayout->addRow("Search: ", searchWidget);
	mainLayout->addLayout(searchLayout);

	QGridLayout* listsLayout = new QGridLayout();
	listsLayout->addWidget(coatsListWidget, 0, 0);
	listsLayout->addWidget(cartListWidget, 0, 1);

	mainLayout->addLayout(listsLayout);

	QFormLayout* linesLayout = new QFormLayout();
	
	linesLayout->addRow("Size: ", sizeWidget);
	linesLayout->addRow("Colour: ", colourWidget);
	linesLayout->addRow("Price: ", priceWidget);
	linesLayout->addRow("Quantity: ", quantityWidget);
	linesLayout->addRow("Link: ", photoWidget);

	mainLayout->addLayout(linesLayout);
	QGridLayout* buttonsLayout = new QGridLayout();

	buttonsLayout->addWidget(addWidget, 0, 0);

	addWidget->setStyleSheet(QString("background-color: qlineargradient(x1:0, y1:0, x2:1, y2:0, stop:0 white, stop: 0.4 "
		"gray, stop:1 green)"));
	buttonsLayout->addWidget(deleteWidget, 0, 1);
	deleteWidget->setStyleSheet(QString("background-color: qlineargradient(x1:0, y1:0, x2:1, y2:0, stop:0 white, stop: 0.4 "
		"gray, stop:1 red)"));
	buttonsLayout->addWidget(updateWidget, 0, 2);
	updateWidget->setStyleSheet(QString("background-color: qlineargradient(x1:0, y1:0, x2:1, y2:0, stop:0 white, stop: 0.4 "
		"gray, stop:1 blue)"));
	buttonsLayout->addWidget(undoWidget, 1, 0);
	buttonsLayout->addWidget(filterWidget, 1, 1);
	buttonsLayout->addWidget(redoWidget, 1, 2);
	buttonsLayout->addWidget(addCartWidget, 0, 3);
	buttonsLayout->addWidget(nextWidget, 0, 4);
	buttonsLayout->addWidget(windowWidget, 0, 5);

	mainLayout->addLayout(buttonsLayout);
}

void GUI::populateList()
{
	coatsListWidget->clear();
	// Can sort them here before adding them to the ListWidget, with a sort function done in the repo
	// ctrl.sort();
	std::vector<Coat> all = ctrl.getAll();
	for (auto coat : all)
	{
		coatsListWidget->addItem(QString::fromStdString(coat.getSize() + " - " + 
			coat.getColour() + " - " + std::to_string(coat.getPrice()) + " - " + 
			std::to_string(coat.getQuantity()) + " - " + coat.getPhotograph()));
	}
}

int GUI::getSelectedIndex() const
{
	QModelIndexList selectedIndexes = this->coatsListWidget->selectionModel()->selectedIndexes();
	if (selectedIndexes.size() == 0)
	{
		this->sizeWidget->clear();
		this->colourWidget->clear();
		this->priceWidget->clear();
		this->quantityWidget->clear();
		this->photoWidget->clear();
		return -1;
	}
	int selectedIndex = selectedIndexes.at(0).row();
	return selectedIndex;
}

void GUI::connectSignalsAndSlots()
{
	QObject::connect(this->addWidget, &QPushButton::clicked, this, &GUI::addCoat);
	QObject::connect(this->deleteWidget, &QPushButton::clicked, this, &GUI::deleteCoat);
	QObject::connect(this->updateWidget, &QPushButton::clicked, this, &GUI::updateCoat);
	QObject::connect(this->searchWidget, &QLineEdit::textChanged, this, &GUI::searchCoat);
	QObject::connect(this->filterWidget, &QPushButton::clicked, this, &GUI::filterCoat);
	QObject::connect(this->addCartWidget, &QPushButton::clicked, this, &GUI::addCart);
	QObject::connect(this->nextWidget, &QPushButton::clicked, this, &GUI::nextCoat);
	QObject::connect(this->undoWidget, &QPushButton::clicked, this, &GUI::undo);
	QObject::connect(this->redoWidget, &QPushButton::clicked, this, &GUI::redo);
	QObject::connect(this->windowWidget, &QPushButton::clicked, this, &GUI::runWindow);
	QObject::connect(this->undoShortcutWidget, &QShortcut::activated, this, &GUI::undo);
	QObject::connect(this->redoShortcutWidget, &QShortcut::activated, this, &GUI::redo);
}

void GUI::addCoat()
{
	std::string size = this->sizeWidget->text().toStdString();
	std::string colour = this->colourWidget->text().toStdString();
	int price = this->priceWidget->text().toInt();
	int quantity = this->quantityWidget->text().toInt();
	std::string photo = this->photoWidget->text().toStdString();
	try {
		this->ctrl.addCoat(size, colour, price, quantity, photo);

	}
	catch (ValidException err)
	{
		QMessageBox::critical(this, "Error", "Bad stuff");
	}
	this->populateList();
	int lastElement = this->ctrl.getAll().size() - 1;
	this->coatsListWidget->setCurrentRow(lastElement);
}

void GUI::deleteCoat()
{
	int selectedIndex = this->getSelectedIndex();
	if (selectedIndex < 0)
	{
		QMessageBox::critical(this, "Error", "No coat was selected!");
		return;
	}
	std::vector<Coat> coats = this->ctrl.getAll();
	Coat coat = coats[selectedIndex];
	try {
		this->ctrl.deleteCoat(coat.getPhotograph());
	}
	catch (ValidException err)
	{
		QMessageBox::critical(this, "Error", "Bad stuff");
	}
	this->populateList();
}

void GUI::updateCoat()
{
	std::string size = this->sizeWidget->text().toStdString();
	std::string colour = this->colourWidget->text().toStdString();
	int price = this->priceWidget->text().toInt();
	int quantity = this->quantityWidget->text().toInt();
	std::string photo = this->photoWidget->text().toStdString();

	int selectedIndex = this->getSelectedIndex();
	if (selectedIndex < 0)
	{
		QMessageBox::critical(this, "Error", "No coat was selected!");
		return;
	}
	std::vector<Coat> coats = this->ctrl.getAll();
	Coat coat = coats[selectedIndex];
	try
	{
		this->ctrl.updateCoat(coat.getPhotograph(), size, colour, price, quantity, photo);
	}
	catch (ValidException err)
	{
		QMessageBox::critical(this, "Error", "Bad stuff");
	}
	this->populateList();
}

void GUI::filterCoat()
{
	std::string filter = this->sizeWidget->text().toStdString();
	this->coatsListWidget->clear();
	this->ctrl.filterClear();
	if (!this->sizeWidget->text().toStdString().empty())
	{
		for (auto coat : this->ctrl.getAll())
		{
			if (coat.getSize() == filter)
				this->ctrl.addFilter(coat);
		}
		for (auto coat : this->ctrl.getFilter())
		{
			this->coatsListWidget->addItem(QString::fromStdString(coat.getSize() + " - " +
				coat.getColour() + " - " + std::to_string(coat.getPrice()) + " - " +
				std::to_string(coat.getQuantity()) + " - " + coat.getPhotograph()));
		}
		this->current = 0;
		this->cap = this->ctrl.getFilterSize() - 1;
	}
	else
	{
		for (auto coat : this->ctrl.getAll())
		{
			this->ctrl.addFilter(coat);
			this->coatsListWidget->addItem(QString::fromStdString(coat.getSize() + " - " +
				coat.getColour() + " - " + std::to_string(coat.getPrice()) + " - " +
				std::to_string(coat.getQuantity()) + " - " + coat.getPhotograph()));
		}
		this->current = 0;
		this->cap = this->ctrl.getSize() - 1;
	}
}

void GUI::addCart()
{
	Coat coat = this->ctrl.getFilter()[this->current];
	int total_price = this->totalPriceWidget->text().toInt();
	this->ctrl.addToCart(coat.getSize(), coat.getColour(), coat.getPrice(), coat.getQuantity(), coat.getPhotograph());
	total_price = total_price + coat.getPrice();
	this->totalPriceWidget->setText(QString::fromStdString(std::to_string(total_price)));
	this->cartListWidget->addItem(QString::fromStdString(coat.getSize() + " - " +
		coat.getColour() + " - " + std::to_string(coat.getPrice()) + " - " +
		std::to_string(coat.getQuantity()) + " - " + coat.getPhotograph()));
}

void GUI::nextCoat()
{
	if (this->current != cap)
		this->current++;
	else
		this->current = 0;
	Coat coat = this->ctrl.getFilter()[this->current];
	std::string link = coat.getPhotograph();
	QDesktopServices::openUrl(QUrl(QString::fromStdString(link), QUrl::TolerantMode));
}

void GUI::undo()
{
	try 
	{
		this->ctrl.undo();
		this->populateList();
	}
	catch (std::exception err)
	{
		QMessageBox::critical(this, "Error", err.what());
	}
}

void GUI::redo()
{
	try
	{
		this->ctrl.redo();
		this->populateList();
	}
	catch (std::exception err)
	{
		QMessageBox::critical(this, "Error", err.what());
	}
}

void GUI::runWindow()
{
	secGUI.populateCartList();
	secGUI.show();
}

void GUI::searchCoat()
{
	std::string filter = this->searchWidget->text().toStdString();
	std::vector<Coat> filtered;
	for (auto coat : this->ctrl.getAll())
	{
		size_t found = coat.getColour().find(filter);
		size_t found_2 = coat.getSize().find(filter);
		size_t found_3 = std::to_string(coat.getPrice()).find(filter);
		size_t found_4 = std::to_string(coat.getQuantity()).find(filter);
		size_t found_5 = coat.getPhotograph().find(filter);
		if (found != std::string::npos || found_2 != std::string::npos || found_3 != std::string::npos || 
			found_4 != std::string::npos || found_5 != std::string::npos)
			filtered.push_back(coat);
	}
	this->coatsListWidget->clear();
	for (auto coat : filtered)
	{
		this->coatsListWidget->addItem(QString::fromStdString(coat.getSize() + " - " +
			coat.getColour() + " - " + std::to_string(coat.getPrice()) + " - " +
			std::to_string(coat.getQuantity()) + " - " + coat.getPhotograph()));
	}

}

GUI::GUI(Controller& control, SecondaryGUI& secgui) : ctrl{ control }, secGUI(secgui)
{
	this->initGUI();
	this->populateList();
	this->connectSignalsAndSlots();
}


void SecondaryGUI::initSecondaryGUI()
{
	this->cartListWidget = new QListWidget();
	QVBoxLayout* mainLayout = new QVBoxLayout(this);
	QGridLayout* listsLayout = new QGridLayout();
	listsLayout->addWidget(this->cartListWidget, 0, 0);
	mainLayout->addLayout(listsLayout);
}

void SecondaryGUI::populateCartList()
{
	this->cartListWidget->clear();
	// Can sort them here before adding them to the ListWidget, with a sort function done in the repo
	// ctrl.sort();
	std::vector<Coat> all = ctrl.getCart();
	for (auto coat : all)
	{
		this->cartListWidget->addItem(QString::fromStdString(coat.getSize() + " - " +
			coat.getColour() + " - " + std::to_string(coat.getPrice()) + " - " +
			std::to_string(coat.getQuantity()) + " - " + coat.getPhotograph()));
	}
}

SecondaryGUI::SecondaryGUI(Controller& controller) : ctrl(controller)
{
	this->initSecondaryGUI();
	this->populateCartList();
}


/*
SecondaryGUI::SecondaryGUI(Controller& ctrl) : ctrl{ ctrl }
{
}

int SecondaryGUI::rowCount(const QModelIndex& parent) const
{
	return this->ctrl.getSize();
}

int SecondaryGUI::columnCount(const QModelIndex& parent) const
{
	return 3;
}

QVariant SecondaryGUI::data(const QModelIndex& index, int role) const
{
	int row = index.row();
	int column = index.column();

	Coat coat = this->ctrl.getAll()[row];

	if (role == Qt::DisplayRole)
	{
		switch (column)
		{
		case 0:
			return QString::fromStdString(coat.getPhotograph());
		case 1:
			return QString::number(coat.getPrice());
		default:
			break;
		}
	}

	if (role == Qt::FontRole)
	{
		QFont font{ "Verdana", 15 };
		return font;
	}

	if (role == Qt::BackgroundRole)
	{
		if (row % 2 == 1)
		{
			QBrush brush{ QColor{220, 220, 220} };
			return brush;
		}
	}

	if (role == Qt::UserRole)
		if (column == 1)
			return coat.getPrice();

	return QVariant();
}

QVariant SecondaryGUI::headerData(int section, Qt::Orientation orientation, int role) const
{
	if (orientation == Qt::Horizontal)
	{
		if (role == Qt::DisplayRole)
		{
			switch (section)
			{
			case 0:
				return QString{ "Link" };
			case 1:
				return QString{ "Price" };
			default:
				break;
			}
		}
	}

	return QVariant();
}
*/