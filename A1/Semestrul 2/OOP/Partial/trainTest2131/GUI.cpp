//
// Created by Daniel on 18.05.2022.
//

#include "GUI.h"

void GUI::initGUI() {
    setLayout(lymain);
    lymain->addWidget(lstPlB);
    QVBoxLayout* lyc = new QVBoxLayout;
    lymain->addLayout(lyc);
    lymain->addWidget(lstCPU);
    QFormLayout* form = new QFormLayout;
    form->addRow("Nume", Nume);
    form->addRow("Soclu", Soclu);
    form->addRow("Pret", Pret);
    lyc->addLayout(form);
    lyc->addWidget(btnAdd);
    lyc->addWidget(btnFilter);
    lyc->addWidget(btnShow);
    lyc->addWidget(btnTotal);
}

void GUI::initConnect() {
    QObject::connect(btnAdd, &QPushButton::clicked, [&]{
        string t1 = Nume->text().toStdString();
        string t2 = Soclu->text().toStdString();
        int pret = Pret->text().toInt();
        service.addPlaca(t1, t2, pret);
        loadData(service.getPlaci());
    });
    QObject::connect(btnTotal, &QPushButton::clicked, [&]{
         auto curr1 = lstCPU->currentItem()->text().toStdString();
         auto curr2 = lstPlB->currentItem()->text().toStdString();
         auto item1 = service.findProcesor(curr1);
         auto item2 = service.findPlaca(curr2);
         int pret = item1.getPret() + item2.getPret();
         QMessageBox::warning(this, "Pret total", std::to_string(pret).c_str());
    });
    QObject::connect(btnFilter, &QPushButton::clicked, [&]{
        auto curr1 = lstCPU->currentItem()->text().toStdString();
        auto item1 = service.findProcesor(curr1);
        auto rez = service.filterSoclu(item1.getSoclu());
        loadData(rez);
    });
    QObject::connect(btnShow, &QPushButton::clicked, [&]{
        loadData(service.getPlaci());
    });
}

void GUI::loadData(const vector<PlacaDeBaza> &plb) {
    lstCPU->clear();
    lstPlB->clear();
    for(const Procesor& p : service.getProcesoare())
    {
        string txt = p.getNume() + "-" + std::to_string(p.getNrThreaduri());
        lstCPU->addItem(txt.c_str());
    }
    for(const PlacaDeBaza& p : plb)
        lstPlB->addItem(p.getNume().c_str());
}
