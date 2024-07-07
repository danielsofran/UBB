#include "QApplication"
#include "tests.h"
#include "GUI.h"

int main(int argc, char** argv) {
    QApplication a(argc, argv);
    test();

    Repository r("repo.txt");
    Service s(r);
    GUI gui(s);
    gui.show();

    return QApplication::exec();
}
