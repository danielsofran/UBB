#include "QApplication"
#include "tests.h"
#include "gui.h"

int main(int argc, char** argv) {
    test();
    QApplication a(argc, argv);

    Repository r("testrepo.txt");
    Service s(r);
    GUI gui(s);
    gui.show();

    return QApplication::exec();
}
