#include "DisplayWindow.h"
#include <qmessagebox.h>

DisplayWindow::DisplayWindow(SourceRepo& rs, RepoProgrammers& rp, Programmer& p, QWidget *parent)
	: repo_source{ rs }, repo_prog{ rp }, p{ p }, QWidget(parent)
{
	this->repo_source.addObserver(this);
	this->initGUI();
	this->connectSignals();
	this->show();
}



void DisplayWindow::initGUI()
{
	this->tableModel = new TableModel(this->repo_source);
	this->fileTableView = new QTableView();
	this->fileTableView->setModel(this->tableModel);
	this->fileTableView->resizeColumnsToContents();
	this->nameLineEdit = new QLineEdit();
	this->addButton = new QPushButton("Add");
	this->reviseButton = new QPushButton("Revise");
	this->buttonsLayout = new QGridLayout();
	this->nameLabel = new QLabel("Name: ");
	this->buttonsLayout->addWidget(this->nameLabel, 0, 0);
	this->buttonsLayout->addWidget(this->nameLineEdit, 0, 1);
	this->buttonsLayout->addWidget(this->addButton, 1, 0);
	this->buttonsLayout->addWidget(this->reviseButton, 1, 1);
	this->revisedLabel = new QLabel(QString::fromStdString(std::to_string(this->p.getRevised())));
	this->totalLabel = new QLabel(QString::fromStdString(std::to_string(this->p.getTotal())));
	this->userLabel = new QLabel(QString::fromStdString(this->p.getName()));
	this->buttonsLayout->addWidget(this->revisedLabel, 2, 0);
	this->buttonsLayout->addWidget(this->totalLabel, 2, 1);
	this->buttonsLayout->addWidget(this->userLabel, 2, 2);
	this->mainLayout = new QVBoxLayout();
	this->mainLayout->addWidget(this->fileTableView);
	this->mainLayout->addLayout(this->buttonsLayout);
	this->setLayout(this->mainLayout);
	this->setWindowTitle(QString::fromStdString(this->p.getName()));
}

void DisplayWindow::connectSignals()
{
	QObject::connect(this->fileTableView, &QTableView::clicked, this, &DisplayWindow::checkFileStatus);
	QObject::connect(this->addButton, &QPushButton::clicked, this, &DisplayWindow::addFile);
	QObject::connect(this->reviseButton, &QPushButton::clicked, this, &DisplayWindow::reviseFile);
}

void DisplayWindow::update()
{
	this->notifyModel();
}

void DisplayWindow::notifyModel()
{
	this->tableModel->updateInternalData();
}

void DisplayWindow::addFile()
{
	std::string name = this->nameLineEdit->text().toStdString();
	if (this->nameLineEdit->text().toStdString().empty())
	{
		QMessageBox::critical(this, "Invalid name!", "Invalid name field!");
		return;
	}
	std::string pname = this->userLabel->text().toStdString();
	SourceFile sf(name, "not_revised", pname, "NULL");
	try
	{
		this->repo_source.addSourceFile(sf);
	}
	catch (std::exception ex)
	{
		QMessageBox::critical(this, "Invalid name!", "Invalid name field!");
		return;
	}
}

void DisplayWindow::reviseFile()
{
	SourceFile sf = this->repo_source.getSourceFiles()[this->fileTableView->currentIndex().row()];
	this->repo_source.setStatusFile(sf, this->userLabel->text().toStdString());
	this->revisedLabel->setText(QString::fromStdString(std::to_string(this->revisedLabel->text().toInt() + 1)));
	this->totalLabel->setText(QString::fromStdString(std::to_string(this->totalLabel->text().toInt() - 1)));
	this->reviseButton->setDisabled(true);

	if (this->revisedLabel->text() == QString::fromStdString(std::to_string(this->p.getTotal())))
		QMessageBox::critical(this, "Congratulations!", "You revised your allocated tasks!");
}

void DisplayWindow::checkFileStatus()
{
	SourceFile sf = this->repo_source.getSourceFiles()[this->fileTableView->currentIndex().row()];
	if (sf.getStatus() == "revised" || sf.getCreator() == this->userLabel->text().toStdString())
		this->reviseButton->setDisabled(true);
	else
		this->reviseButton->setEnabled(true);

}

DisplayWindow::~DisplayWindow()
{
}
