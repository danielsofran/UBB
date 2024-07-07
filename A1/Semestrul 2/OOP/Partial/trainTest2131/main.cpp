#include "QApplication"
#include "tests.h"
#include "GUI.h"

int main(int argc, char** argv) {
    test();
    QApplication a(argc, argv);

    FileRepository<Procesor> r1("cpu.txt");
    FileRepository<PlacaDeBaza> r2("placi.txt");
    Service s(r1, r2);
    GUI gui(s);
    gui.show();

    return QApplication::exec();
}
