/********************************************************************************
** Form generated from reading UI file 'PracticalExam.ui'
**
** Created by: Qt User Interface Compiler version 6.3.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_PRACTICALEXAM_H
#define UI_PRACTICALEXAM_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_PracticalExamClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *PracticalExamClass)
    {
        if (PracticalExamClass->objectName().isEmpty())
            PracticalExamClass->setObjectName(QString::fromUtf8("PracticalExamClass"));
        PracticalExamClass->resize(600, 400);
        menuBar = new QMenuBar(PracticalExamClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        PracticalExamClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(PracticalExamClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        PracticalExamClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(PracticalExamClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        PracticalExamClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(PracticalExamClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        PracticalExamClass->setStatusBar(statusBar);

        retranslateUi(PracticalExamClass);

        QMetaObject::connectSlotsByName(PracticalExamClass);
    } // setupUi

    void retranslateUi(QMainWindow *PracticalExamClass)
    {
        PracticalExamClass->setWindowTitle(QCoreApplication::translate("PracticalExamClass", "PracticalExam", nullptr));
    } // retranslateUi

};

namespace Ui {
    class PracticalExamClass: public Ui_PracticalExamClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_PRACTICALEXAM_H
