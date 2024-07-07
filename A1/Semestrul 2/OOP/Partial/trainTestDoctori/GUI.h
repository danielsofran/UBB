//
// Created by Daniel on 17.05.2022.
//

#ifndef TRAINTESTDOCTORI_GUI_H
#define TRAINTESTDOCTORI_GUI_H

#include "service.h"

#include "QWidget"
#include "QHBoxLayout"
#include "QVBoxLayout"
#include "QFormLayout"
#include "QPushButton"
#include "QLineEdit"
#include "QListWidget"
#include "QListWidgetItem"
#include "QColor"
#include "QMessageBox"

class GUI : public QWidget{
private:
    Service& s;

    QVBoxLayout* lymain = new QVBoxLayout(this);

    QListWidget* lst = new QListWidget;

    QLineEdit* Cnp = new QLineEdit;
    QLineEdit* NumeSectie = new QLineEdit;
    //QLineEdit* Prenume = new QLineEdit;
    //QLineEdit* Sectie = new QLineEdit;

    QPushButton* btnfname = new QPushButton("&Filter Name");
    QPushButton* btnfsect = new QPushButton("&FIlter Sectie");
    QPushButton* btnshowall = new QPushButton("&Show All");

    void initGUI(); // initializam fereastra
    void initConnect(); // conectam butoanele la actiuni
    void loadData(const vector<Doctor>& v); // incarcam date

public:
    GUI(Service& srv) : s(srv){
        initGUI();
        initConnect();
        loadData(s.getAll());
    }
};

#endif //TRAINTESTDOCTORI_GUI_H
