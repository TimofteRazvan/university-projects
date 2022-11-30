#pragma once

#include <QWidget>
#include "ui_DisplayWindow.h"
#include "Observer.h"
#include "RepoProgrammers.h"
#include "TableModel.h"
#include <qtableview.h>
#include <qlineedit.h>
#include <qlabel.h>
#include <qboxlayout.h>
#include <qgridlayout.h>
#include <qpushbutton.h>

class DisplayWindow : public QWidget, public Observer
{
	Q_OBJECT

public:
	DisplayWindow(SourceRepo& rs, RepoProgrammers& rp, Programmer& p, QWidget *parent = Q_NULLPTR);
	void initGUI();
	void connectSignals();
	void update();
	void notifyModel();
	void addFile();
	void reviseFile();
	void checkFileStatus();
	~DisplayWindow();

private:
	SourceRepo& repo_source;
	RepoProgrammers& repo_prog;
	Programmer& p;
	TableModel* tableModel;
	QTableView* fileTableView;
	QLineEdit* nameLineEdit;
	QLabel* nameLabel;
	QLabel* revisedLabel;
	QLabel* totalLabel;
	QLabel* userLabel;
	QPushButton* addButton;
	QPushButton* reviseButton;
	QGridLayout* buttonsLayout;
	QVBoxLayout* mainLayout;

	Ui::DisplayWindow ui;
};
