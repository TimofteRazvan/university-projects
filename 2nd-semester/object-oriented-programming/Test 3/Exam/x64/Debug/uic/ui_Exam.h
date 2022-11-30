/********************************************************************************
** Form generated from reading UI file 'Exam.ui'
**
** Created by: Qt User Interface Compiler version 6.3.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_EXAM_H
#define UI_EXAM_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_ExamClass
{
public:
    QWidget *centralWidget;
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout_2;
    QListWidget *categoryListWidget;
    QListWidget *shoppingListWidget;
    QHBoxLayout *horizontalLayout;
    QFormLayout *formLayout;
    QLabel *label;
    QLineEdit *searchLineEdit;
    QFormLayout *formLayout_2;
    QLabel *label_2;
    QLineEdit *categoryLineEdit;
    QPushButton *categoryPushButton;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *ExamClass)
    {
        if (ExamClass->objectName().isEmpty())
            ExamClass->setObjectName(QString::fromUtf8("ExamClass"));
        ExamClass->resize(600, 400);
        centralWidget = new QWidget(ExamClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        verticalLayout = new QVBoxLayout(centralWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setSpacing(6);
        horizontalLayout_2->setObjectName(QString::fromUtf8("horizontalLayout_2"));
        categoryListWidget = new QListWidget(centralWidget);
        categoryListWidget->setObjectName(QString::fromUtf8("categoryListWidget"));

        horizontalLayout_2->addWidget(categoryListWidget);

        shoppingListWidget = new QListWidget(centralWidget);
        shoppingListWidget->setObjectName(QString::fromUtf8("shoppingListWidget"));

        horizontalLayout_2->addWidget(shoppingListWidget);


        verticalLayout->addLayout(horizontalLayout_2);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(6);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));

        verticalLayout->addLayout(horizontalLayout);

        formLayout = new QFormLayout();
        formLayout->setSpacing(6);
        formLayout->setObjectName(QString::fromUtf8("formLayout"));
        label = new QLabel(centralWidget);
        label->setObjectName(QString::fromUtf8("label"));

        formLayout->setWidget(0, QFormLayout::LabelRole, label);

        searchLineEdit = new QLineEdit(centralWidget);
        searchLineEdit->setObjectName(QString::fromUtf8("searchLineEdit"));

        formLayout->setWidget(0, QFormLayout::FieldRole, searchLineEdit);


        verticalLayout->addLayout(formLayout);

        formLayout_2 = new QFormLayout();
        formLayout_2->setSpacing(6);
        formLayout_2->setObjectName(QString::fromUtf8("formLayout_2"));
        label_2 = new QLabel(centralWidget);
        label_2->setObjectName(QString::fromUtf8("label_2"));

        formLayout_2->setWidget(0, QFormLayout::LabelRole, label_2);

        categoryLineEdit = new QLineEdit(centralWidget);
        categoryLineEdit->setObjectName(QString::fromUtf8("categoryLineEdit"));

        formLayout_2->setWidget(0, QFormLayout::FieldRole, categoryLineEdit);


        verticalLayout->addLayout(formLayout_2);

        categoryPushButton = new QPushButton(centralWidget);
        categoryPushButton->setObjectName(QString::fromUtf8("categoryPushButton"));

        verticalLayout->addWidget(categoryPushButton);

        ExamClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(ExamClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 600, 22));
        ExamClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(ExamClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        ExamClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(ExamClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        ExamClass->setStatusBar(statusBar);

        retranslateUi(ExamClass);
        QObject::connect(categoryPushButton, SIGNAL(clicked()), ExamClass, SLOT(showItems()));

        QMetaObject::connectSlotsByName(ExamClass);
    } // setupUi

    void retranslateUi(QMainWindow *ExamClass)
    {
        ExamClass->setWindowTitle(QCoreApplication::translate("ExamClass", "Exam", nullptr));
        label->setText(QCoreApplication::translate("ExamClass", "Search:", nullptr));
        label_2->setText(QCoreApplication::translate("ExamClass", "Category:", nullptr));
        categoryPushButton->setText(QCoreApplication::translate("ExamClass", "Show items", nullptr));
    } // retranslateUi

};

namespace Ui {
    class ExamClass: public Ui_ExamClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_EXAM_H
