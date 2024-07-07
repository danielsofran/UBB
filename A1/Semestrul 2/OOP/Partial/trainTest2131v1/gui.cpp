//
// Created by Daniel on 19.05.2022.
//

// You may need to build the project (run Qt uic code generator) to get "ui_GUI.h" resolved

#include "gui.h"

void GUI::initGUI() {
    QHBoxLayout* lymain = new QHBoxLayout(this);
    lymain->addWidget(lst);
    QVBoxLayout* lydr = new QVBoxLayout;
    lydr->addWidget(btnRmv);
    lydr->addWidget(sup1);
    lydr->addWidget(sup2);
    lydr->addWidget(btnFSup);
    lydr->addWidget(pr1);
    lydr->addWidget(pr2);
    lydr->addWidget(btnFPr);
    lymain->addLayout(lydr);
}

void GUI::initConnect() {
    QObject::connect(btnRmv, &QPushButton::clicked, [&](){
        int index = lst->currentRow();
        if(index<0){
            MsgBox("Neselectat!");
            return;
        }
        auto item = displayed[index];
        try {
            service.sterge(item);
            displayed = service.getAll();
            loadData();
        }
        catch(std::logic_error& e){
            MsgBox(e.what());
        }
    });
    QObject::connect(btnFSup, &QPushButton::clicked, [&]{
        int s1, s2;
        try{
            s1 = sup1->text().toInt();
            s2 = sup2->text().toInt();
        }
        catch(...){
            MsgBox("Date invalide!");
            return ;
        }
        displayed = service.filterSupr(s1, s2);
        loadData();
    });
    QObject::connect(btnFPr, &QPushButton::clicked, [&]{
        int s1, s2;
        try{
            s1 = pr1->text().toInt();
            s2 = pr2->text().toInt();
        }
        catch(...){
            MsgBox("Date invalide!");
            return ;
        }
        displayed = service.filterPret(s1, s2);
        loadData();
    });
}

void GUI::loadData() {
    lst->clear();
    for(const Apartament& ap : displayed) {
        string text = ap.getStrada() + " " + std::to_string(ap.getSupr()) + "m^2 "+ std::to_string(ap.getPret())+"euro";
        lst->addItem(text.c_str());
    }
}
