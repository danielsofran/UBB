//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL3_GUI_H
#define MODEL3_GUI_H

#include "includesQT.h"
#include "CRUD/service.h"
#include "model.h"

#define MsgBox(txt) QMessageBox::warning(nullptr, "", txt)

class GUI : public QWidget, public Observer
{
private:
    Service& service;
    int nr;
    string tip;

    MyModel* model = new MyModel;
    QTableView* table = new QTableView;

    QHBoxLayout* lymain = new QHBoxLayout;
    QVBoxLayout* lydr = new QVBoxLayout;

    QLabel* lblNr = new QLabel;
    QPushButton* btn = new QPushButton("&Add");

    QLineEdit* lineId = new QLineEdit;
    QLineEdit* lineNume = new QLineEdit;
    QLineEdit* lineTip = new QLineEdit;
    QLineEdit* linePret = new QLineEdit;

    QFormLayout* form = new QFormLayout;

    QSlider* slider = new QSlider;

    void initGUI();
    void connect();
    void reload(const vector<Produs>& filter = {});
    void updateLines(const Produs&);
    void clearLines();

    void updateObs();

public:
    GUI(Service& s, string tip, int nr) : service(s){
        this->tip = tip;
        this->nr = nr;
        s.addObserver(this);
        initGUI();
        connect();
        reload();
    }
    ~GUI(){
        service.remove(this);
    }
};

#endif //MODEL3_GUI_H
