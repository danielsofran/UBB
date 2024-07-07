#include <QApplication>
#include "tests.h"
#include "GUI/GUI.h"
#include "GUI/GUIstari.h"

int main(int argc, char** argv) {
    QApplication a(argc, argv);
    Test t;
    Repository r("repotest.txt");
    Service s(r);
    GUI* gui = new GUI(s);
    gui->show();

    GUIstari* guiopen = new GUIstari(s, "open");
    GUIstari* guiinp = new GUIstari(s, "inprogress");
    GUIstari* guiclosed = new GUIstari(s, "closed");

    guiopen->show();
    guiclosed->show();
    guiinp->show();
    return QApplication::exec();
}
