//
// Created by Daniel on 17.05.2022.
//

#ifndef QTEXPLORE_GUI1_H
#define QTEXPLORE_GUI1_H

#include "service.h"

#include "QWidget"
#include "QLineEdit"
#include "QPushButton"
#include "QMessageBox"
#include "QFormLayout"
#include "QVBoxLayout"
#include "QHBoxLayout"
#include "QGroupBox"
#include "QRadioButton"

class GUI1 : public QWidget{
private:
    Service& service;

    QGridLayout* grid = new QGridLayout;
    QGroupBox* group = new QGroupBox;
    QRadioButton* rd1 = new QRadioButton("Radio 1");
    QRadioButton* rd2 = new QRadioButton("Radio 2");
    QRadioButton* rd3 = new QRadioButton("Radio 3");
    QRadioButton* rd4 = new QRadioButton("Radio 4");

    void initGUI();
    void initConnect();

    template<class T>
    void loadData(T& collection){};

public:
    GUI1(Service& srv) : service(srv) {
        initGUI();
        initConnect();
        loadData(service.getAll());
    }
};

#endif //QTEXPLORE_GUI1_H
