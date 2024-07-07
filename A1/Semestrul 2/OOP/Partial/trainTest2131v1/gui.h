//
// Created by Daniel on 19.05.2022.
//

#ifndef TRAINTEST2131V1_GUI_H
#define TRAINTEST2131V1_GUI_H

#include "service.h"

#include <QWidget>
#include "QListWidget"
#include "QLineEdit"
#include "QPushButton"
#include "QHBoxLayout"
#include "QVBoxLayout"
#include "QMessageBox"

#define MsgBox(text) QMessageBox::information(this, "", text)

class GUI : public QWidget {
private:
    Service& service;
    vector<Apartament> displayed;

    QListWidget* lst = new QListWidget;

    QPushButton* btnRmv = new QPushButton("Sterge");
    QPushButton* btnFSup = new QPushButton("Filtreaza suprafat");
    QPushButton* btnFPr= new QPushButton("Filtreaza pret");

    QLineEdit* pr1 = new QLineEdit;
    QLineEdit* pr2 = new QLineEdit;
    QLineEdit* sup1 = new QLineEdit;
    QLineEdit* sup2 = new QLineEdit;

    void initGUI(); // incarc componentele
    void initConnect(); // conectez semnalele
    void loadData(); // incarc datele

public:
    GUI(Service& s) : service(s){
        initGUI();
        initConnect();
        displayed = s.getAll();
        loadData();
    }
};


#endif //TRAINTEST2131V1_GUI_H
