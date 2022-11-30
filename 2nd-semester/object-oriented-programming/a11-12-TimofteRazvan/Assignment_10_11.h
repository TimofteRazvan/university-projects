#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_Assignment_10_11.h"
#include "controller.h"

class Assignment_10_11 : public QMainWindow
{
    Q_OBJECT

public:
    Assignment_10_11(Controller& ctrl, QWidget *parent = Q_NULLPTR);

private:
    Controller& ctrl;
    Ui::Assignment_10_11Class ui;
    int getSelectedIndex() const;
    void connectSignalsAndSlots();
    void populateList();
    void deleteCoat();

public slots:
    void addCoat();
    void showTotalPrice();
};
