//
// Created by Daniel on 19.05.2022.
//

#ifndef TRAINTEST2172_GUI_H
#define TRAINTEST2172_GUI_H
#include "service.h"

#include "QWidget"
#include "QLineEdit"
#include "QListWidget"
#include "QFormLayout"
#include "QVBoxLayout"
#include "QHBoxLayout"
#include "QMessageBox"
#include "QPushButton"

class GUI : public QWidget{
private:
    Service& service;
    vector<Device> displayed;

    QListWidget* lst = new QListWidget;

    QLineEdit* Model = new QLineEdit;
    QLineEdit* An = new QLineEdit;

    QPushButton* btnSortModel = new QPushButton("Sortare model");
    QPushButton* btnSortPret = new QPushButton("Sortare pret");
    QPushButton* btnSortNo = new QPushButton("Nesortat");

    void initGUI(); // initializam suprafata
    void initConnect(); // conectam actiunile la componente
    void loadData(); // incarcam datele afisate

public:
    GUI(Service& s) : service(s){
        displayed = s.getAll();
        initGUI();
        initConnect();
        loadData();
    }
};

#endif //TRAINTEST2172_GUI_H
