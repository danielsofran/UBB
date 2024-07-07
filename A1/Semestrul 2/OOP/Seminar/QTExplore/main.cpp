#include <iostream>
#include "QApplication"
#include "GUI1.h"
#include "service.h"
int main(int argc, char** argv) {
    QApplication a(argc, argv);

    FileRepository r("repo.txt");
    Service s(r);
    GUI1 gui1(s);
    gui1.show();

    return QApplication::exec();
}
