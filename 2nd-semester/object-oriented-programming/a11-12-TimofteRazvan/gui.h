#pragma once
#include "qwidget.h"
#include "controller.h"
#include "qlistwidget.h"
#include "qlineedit.h"
#include "qpushbutton.h"
#include "qboxlayout.h"
#include "qformlayout.h"
#include <qlabel.h>
#include <qshortcut.h>

/*
class SecondaryGUI : public QAbstractTableModel
{
private:
	Controller& ctrl;

public:
	SecondaryGUI(Controller& ctrl);

	int rowCount(const QModelIndex& parent = QModelIndex()) const override;
	int columnCount(const QModelIndex& parent = QModelIndex()) const override;
	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override;

	QVariant headerData(int section, Qt::Orientation orientation, int role = Qt::DisplayRole) const override;
};
*/


class SecondaryGUI : public QWidget
{
private:
	QListWidget* cartListWidget;
	Controller& ctrl;

	void initSecondaryGUI();
	

public:
	SecondaryGUI(Controller& controller);
	void populateCartList();
};


class GUI : public QWidget
{
private:
	Controller& ctrl;
	SecondaryGUI& secGUI;
	QLabel* totalPriceWidget;
	QListWidget* coatsListWidget;
	QListWidget* cartListWidget;
	QLineEdit* searchWidget;
	QLineEdit* sizeWidget;
	QLineEdit* colourWidget;
	QLineEdit* priceWidget;
	QLineEdit* quantityWidget;
	QLineEdit* photoWidget;
	QPushButton* addWidget;
	QPushButton* deleteWidget;
	QPushButton* updateWidget;
	QPushButton* filterWidget;
	QPushButton* addCartWidget;
	QPushButton* nextWidget;
	QPushButton* undoWidget;
	QPushButton* redoWidget;
	QPushButton* windowWidget;
	QShortcut* undoShortcutWidget;
	QShortcut* redoShortcutWidget;
	int current, cap;

	void initGUI();
	void populateList();
	int getSelectedIndex() const;
	void connectSignalsAndSlots();

	void addCoat();
	void deleteCoat();
	void updateCoat();
	void filterCoat();
	void addCart();
	void nextCoat();
	void undo();
	void redo();
	void runWindow();
public:
	GUI(Controller& control, SecondaryGUI& secgui);
public slots:
	void searchCoat();

};