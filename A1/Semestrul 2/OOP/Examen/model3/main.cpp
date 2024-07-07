#include <QApplication>
#include "GUI.h"
#include "CRUD/tests.h"

int main(int argc, char** argv) {
    test();
    QApplication a(argc, argv);
    Repository r("testrepo.txt");
    Service s(r);
    auto f = s.tipuri();
    for(auto p : f){
        GUI* w = new GUI(s, p.first, p.second);
        w->show();
    }

    return QApplication::exec();
}
