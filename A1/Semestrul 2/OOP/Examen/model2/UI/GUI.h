//
// Created by Daniel on 07.06.2022.
//

#ifndef MODEL2_GUI_H
#define MODEL2_GUI_H

#include "service.h"
#include "includesQT.h"
#include "model.h"

#define MsgBox(txt) QMessageBox::warning(NULL, "Atentie", txt)

class GUI : public QWidget, public Observer{
private:
    Service& service;
    int lastid;

    QHBoxLayout* lymain = new QHBoxLayout(this);
    QVBoxLayout* lydr = new QVBoxLayout;
    QFormLayout* form = new QFormLayout;

    QTableView* table = new QTableView;
    MyModel* model;

    QLineEdit* lineTitlu = new QLineEdit;
    QSlider* sliderRank = new QSlider(Qt::Orientation::Horizontal);

    QPushButton* btnModify = new QPushButton("Modifica");
    QPushButton* btnSterge = new QPushButton("Sterge");

    void initGUI(); // adaug componentele la suprafata
    void initConnect(); // conectez semnalele la sloturi

    void updateObs() override; // update in caz de notificare

    void paintEvent(QPaintEvent* ev) override; // paint

public:
    GUI(Service& s) : service(s){
        service.addObserver(this);
        initGUI();
        initConnect();
    }
    ~GUI() override{
        service.removeObserver(this);
    }
};

#endif //MODEL2_GUI_H
