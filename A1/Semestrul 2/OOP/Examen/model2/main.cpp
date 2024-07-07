#include <QApplication>
#include "tests.h"
#include "UI/GUI.h"

int main(int argc, char** argv) {
    QApplication a(argc, argv);
    Test t;
    Repository r("repotest.txt");
    Service s(r);
    GUI* gui = new GUI(s);
    gui->show();
    return QApplication::exec();
}
