//
// Created by Daniel on 18.05.2022.
//

#ifndef TRAINTEST213_1_GUI_H
#define TRAINTEST213_1_GUI_H

#include "service.h"

#include "QWidget"
#include "QLineEdit"
#include "QListWidget"
#include "QHBoxLayout"
#include "QVBoxLayout"
#include "QPushButton"
#include "QFormLayout"
#include "QMessageBox"

class GUI : public QWidget{
private:
    Service& service;

    QListWidget* lst = new QListWidget;

    QLineEdit* sup1 = new QLineEdit;
    QLineEdit* sup2 = new QLineEdit;
    QLineEdit* pr1 = new QLineEdit;
    QLineEdit* pr2 = new QLineEdit;

    QPushButton *btnRemove = new QPushButton("Remove");
    QPushButton *btnFilterSup = new QPushButton("Filtreaza suprafata");
    QPushButton *btnFilterPret = new QPushButton("Filtreaza dupa pret");

    void initGUI();
    void initConnect();
    void loadData(vector<Apartament> elems);

public:
    GUI(Service& s) : service(s){
        initGUI();
        initConnect();
        loadData(s.getAll());
    }
};

#endif //TRAINTEST213_1_GUI_H
