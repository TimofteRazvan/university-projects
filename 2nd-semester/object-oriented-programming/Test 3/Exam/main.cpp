#include "Exam.h"
#include <QtWidgets/QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    Repository repo("Shopping_list.txt");
    Controller ctrl(repo);
    Exam GUI(ctrl);
    GUI.show();
    //Exam w;
    //w.show();
    return a.exec();
}
