//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL1_GUI_H
#define MODEL1_GUI_H

#include "service.h"
#include "includesQT.h"

#define MsgBox(txt) QMessageBox::warning(NULL, "Atentie", txt)

class GUI : public QWidget, public Observer{
private:
    Service& service;
    vector<Task> displayed;

    QTableWidget* table = new QTableWidget(10, 4);
    QHBoxLayout* lymain = new QHBoxLayout(this);
    QVBoxLayout* lydr = new QVBoxLayout;
    QFormLayout* form = new QFormLayout;

    QLineEdit* lineId = new QLineEdit;
    QLineEdit* lineDescr = new QLineEdit;
    QLineEdit* lineStare = new QLineEdit;
    QLineEdit* lineSearch = new QLineEdit;
    QPlainTextEdit* lineProgr = new QPlainTextEdit;

    QPushButton* btnAdd = new QPushButton("Add");
    QPushButton* btnSearch = new QPushButton("Search");

    void initGUi();
    void reload();
    void initConnect();

    void updateObs() override;

public:
    GUI(Service& s) : service(s){
        s.addObserver(this);
        initGUi();
        initConnect();
        displayed = s.getAll();
        reload();
    }
    ~GUI(){
        service.removeObserver(this);
    }
};

#endif //MODEL1_GUI_H
