//
// Created by Daniel on 17.05.2022.
//

#include "GUI.h"

void GUI::initGUI() {
    setLayout(lymain);
    lymain->addWidget(lst);

    QFormLayout* form = new QFormLayout(this);
    form->addRow("CNP", Cnp);
    form->addRow("Nume/Sectie", NumeSectie);
    //form->addRow("Prenume", Prenume);
    //form->addRow("Sectie", Sectie);
    lymain->addLayout(form);

    lymain->addWidget(btnfname);
    lymain->addWidget(btnfsect);
    lymain->addWidget(btnshowall);
}

void GUI::initConnect() {
    QObject::connect(btnshowall, &QPushButton::clicked, [&]{
        loadData(s.getAll());
    });
    QObject::connect(btnfname, &QPushButton::clicked, [&]{
        auto rez = s.filterNume(NumeSectie->text().toStdString());
        loadData(rez);
    });
    QObject::connect(btnfsect, &QPushButton::clicked, [&]{
        auto rez = s.filterSectie(NumeSectie->text().toStdString());
        loadData(rez);
    });
    QObject::connect(lst, &QListWidget::itemClicked, [&](QListWidgetItem* item){
        string text = item->text().toStdString();
        Doctor rez;
        try{ rez = s.find(text);}
        catch(ServiceException& se) {QMessageBox::information(this, "warning", se.getMessage()); }
        Cnp->setText(rez.getCnp().c_str());
    });
}

void GUI::loadData(const vector<Doctor> &v) {
    lst->clear();
    for(const Doctor& d : v)
    {
        string rez = d.getNume() + " " + d.getPrenume()+ " "+d.getSectie();
        QListWidgetItem* item = new QListWidgetItem;
        item->setText(rez.c_str());
        if(d.getInConcediu()) item->setBackground(Qt::red);
        else item->setBackground(Qt::green);
        lst->addItem(item);
    }
}
