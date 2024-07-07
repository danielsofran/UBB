#include <QApplication>
#include "tests.h"
#include "UI/GUI.h"

int main(int argc, char** argv) {
    Test t;
    QApplication a(argc, argv);
    Repository r("repo.txt");
    Service s(r);
    GUI* gui = new GUI(s);
    gui->show();
    return a.exec();
}
