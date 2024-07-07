//
// Created by Daniel on 18.05.2022.
//

#ifndef TRAINTEST2131_GUI_H
#define TRAINTEST2131_GUI_H

#include "service.h"

#include "QWidget"
#include "QLineEdit"
#include "QListWidget"
#include "QPushButton"
#include "QMessageBox"
#include "QFormLayout"
#include "QVBoxLayout"
#include "QHBoxLayout"

class GUI : public QWidget{
private:
    Service& service;

    QHBoxLayout* lymain = new QHBoxLayout(this);
    QListWidget* lstCPU = new QListWidget;
    QListWidget* lstPlB = new QListWidget;

    QLineEdit* Nume = new QLineEdit;
    QLineEdit* Soclu = new QLineEdit;
    QLineEdit* Pret = new QLineEdit;

    QPushButton* btnTotal = new QPushButton("Calculeaza pretul total");
    QPushButton* btnAdd = new QPushButton("Add Placa de baza");
    QPushButton* btnFilter = new QPushButton("Flitreze placile de baza dupa soclul curent");
    QPushButton* btnShow = new QPushButton("Afiseaza tot");

    void initGUI();
    void initConnect();
    void loadData(const vector<PlacaDeBaza>& plb);

public:
    GUI(Service& s) : service(s){
        initGUI();
        initConnect();
        loadData(s.getPlaci());
    }
};

#endif //TRAINTEST2131_GUI_H
