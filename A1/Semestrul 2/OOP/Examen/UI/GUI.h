//
// Created by Daniel on 08.06.2022.
//

#ifndef EXAMEN_GUI_H
#define EXAMEN_GUI_H

#include "service.h"
#include "includesQT.h"
#include "GUITabla.h"

#define MsgBox(txt) QMessageBox::warning(NULL, "Atentie", txt)

class GUI : public QWidget{
private:
    Service& service;
    int currentid;

    QTableWidget* table = new QTableWidget(10, 5);
    QHBoxLayout* lymain = new QHBoxLayout;
    QVBoxLayout* lydr = new QVBoxLayout;

    QFormLayout* form = new QFormLayout;

    QPushButton* btnAdd = new QPushButton("Add");
    QPushButton* btnModif = new QPushButton("Modify");
    QPushButton* btnPlay = new QPushButton("Play");
    QPushButton* btnPlayView = new QPushButton("View Table");

    QLineEdit* lineDim = new QLineEdit;
    QLineEdit* lineTabla = new QLineEdit;
    QLineEdit* lineJucator = new QLineEdit;
    QLineEdit* lineStare= new QLineEdit;

    void initGUI(); // adaug componentele
    void reload(); // incarc din fisier
    void initConnect(); // conectez semnalele la sloturi

public:
    GUI(Service& s) : service(s){ // constructor
        initGUI();
        initConnect();
        reload();
    }
};

#endif //EXAMEN_GUI_H
