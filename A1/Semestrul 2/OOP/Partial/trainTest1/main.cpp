#include <QApplication>
#include <QPushButton>

#include "tests.h"
#include "GUI.h"

int main(int argc, char *argv[]) {
    test();
//    QApplication a(argc, argv);
//    Repository r;
//    FileRepository fr("repo.txt");
//    Service s(fr);
//    GUI gui(s);
//    gui.show();

    return QApplication::exec();
}
