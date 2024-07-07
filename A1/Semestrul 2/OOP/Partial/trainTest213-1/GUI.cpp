//
// Created by Daniel on 18.05.2022.
//

#include "GUI.h"

void GUI::initGUI() {
    QHBoxLayout* lymain = new QHBoxLayout;
    setLayout(lymain);
    lymain->addWidget(lst);
    QVBoxLayout* lydr = new QVBoxLayout;
    lymain->addLayout(lydr);
    lydr->addWidget(btnRemove);
    QFormLayout *forms = new QFormLayout;
    forms->addRow("Suprafata min", sup1);
    forms->addRow("Suprafata max", sup2);
    lydr->addLayout(forms);
    lydr->addWidget(btnFilterSup);
    QFormLayout *formp = new QFormLayout;
    formp->addRow("Pret min", pr1);
    formp->addRow("Pret max", pr2);
    lydr->addLayout(formp);
    lydr->addWidget(btnFilterPret);
}

void GUI::initConnect() {
    QObject::connect(btnRemove, &QPushButton::clicked, [&](){
        string data = lst->currentItem()->text().toStdString();
        service.remove(data);
        loadData(service.getAll());
    });
    QObject::connect(btnFilterSup, &QPushButton::clicked, [&](){
        int s1, s2;
        vector<Apartament> rez;
        try{
            s1 = sup1->text().toInt();
            s2 = sup2->text().toInt();
            rez = service.filterSup(s1, s2);
            loadData(rez);
        }
        catch (...){
            QMessageBox::information(this, "", "Eroare!");
        }
    });
    QObject::connect(btnFilterPret, &QPushButton::clicked, [&](){
        int s1, s2;
        vector<Apartament> rez;
        try{
            s1 = pr1->text().toInt();
            s2 = pr2->text().toInt();
            rez = service.filterPret(s1, s2);
            loadData(rez);
        }
        catch (...){
            QMessageBox::information(this, "", "Eroare!");
        }
    });
}

void GUI::loadData(vector<Apartament> elems) {
    lst->clear();
    for(const Apartament& ap : elems)
    {
        std::ostringstream out;
        out<<ap.getStrada()<<' '<<ap.getSupr()<<' '<<ap.getPret();
        lst->addItem(out.str().c_str());
    }
}
