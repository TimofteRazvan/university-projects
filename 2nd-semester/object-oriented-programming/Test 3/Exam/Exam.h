#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_Exam.h"
#include "Controller.h"

class Exam : public QMainWindow
{
    Q_OBJECT

public:
    Exam(Controller& ctrl, QWidget *parent = Q_NULLPTR);

private:
    Controller& ctrl;
    Ui::ExamClass ui;
    void populateList();
public slots:
    void showItems();
};
