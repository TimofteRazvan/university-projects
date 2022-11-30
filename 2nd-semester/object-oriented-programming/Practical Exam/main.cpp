#include "PracticalExam.h"
#include <QtWidgets/QApplication>
#include "DisplayWindow.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    RepoProgrammers repo_prog;
    SourceRepo repo_source;
    std::vector<DisplayWindow*> windows;
    for (auto x : repo_prog.getProgrammers())
        windows.push_back(new DisplayWindow(repo_source, repo_prog, x));
    return a.exec();
}
