//
// Created by Daniel on 15.05.2022.
//

#ifndef TRAINTEST2_GUI_H
#define TRAINTEST2_GUI_H

#include "QWidget"
#include "QMessageBox"
#include <QPushButton>
#include "QLineEdit"
#include "QListWidget"
#include "QComboBox"
#include "QSpinBox"
#include "QTableWidget"
#include "QHBoxLayout"
#include <QVBoxLayout>
#include "QFormLayout"
#include "service.h"

#define MsgBox(text) QMessageBox::information(this, "Eroare!", text)

class GUI : public QWidget{
private:
    Service& service;

    QLineEdit* Titlu, *Titlu2;
    QLineEdit* Autor, *Autor2;
    QLineEdit* Gen, *Gen2;
    QSpinBox* Anap, *Anap2;

    QTableWidget* table;
    QLineEdit* search;

    QPushButton* btnAdd;
    QPushButton* btnRemove;
    QPushButton* btnModify;
    QPushButton* btnFind;
    QPushButton* btnUndo;
    QPushButton* btnSort;
    QPushButton* btnFilter;

    QComboBox* cmbFilter;
    QComboBox* cmbSort;

    template<class T>
    void loadData(T& elems)
    {
        table->clearContents();
        table->setRowCount(0);
        int i=0;
        for(Carte& elem : elems)
        {
            table->insertRow(i);
            table->setItem(i, 0, new QTableWidgetItem(elem.getTitlu().c_str()));
            table->setItem(i, 1, new QTableWidgetItem(elem.getAutor().c_str()));
            table->setItem(i, 2, new QTableWidgetItem(elem.getGen().c_str()));
            table->setItem(i, 3, new QTableWidgetItem(std::to_string(elem.getAnap()).c_str()));
            i++;
        }
    }

    void initGUI(){
        QHBoxLayout * layoutMain = new QHBoxLayout;
        setLayout(layoutMain);

        QVBoxLayout* pdr = new QVBoxLayout;
        QVBoxLayout* pst = new QVBoxLayout;
        layoutMain->addLayout(pst);
        layoutMain->addLayout(pdr);

        Titlu = new QLineEdit;
        Autor = new QLineEdit;
        Gen = new QLineEdit;
        Anap = new QSpinBox;
        Anap->setMinimum(0);
        Anap->setMaximum(3000);

        QFormLayout* form =new QFormLayout;
        form->addRow("Titlu", Titlu);
        form->addRow("Autor", Autor);
        form->addRow("Gen", Gen);
        form->addRow("Anul Aparitiei", Anap);
        pdr->addLayout(form);

        Titlu2 = new QLineEdit;
        Autor2 = new QLineEdit;
        Gen2 = new QLineEdit;
        Anap2 = new QSpinBox;
        Anap2->setMinimum(0);
        Anap2->setMaximum(3000);

        QFormLayout* form2 =new QFormLayout;
        form->addRow("Titlu", Titlu2);
        form->addRow("Autor", Autor2);
        form->addRow("Gen", Gen2);
        form->addRow("Anul Aparitiei", Anap2);
        pdr->addLayout(form);

        table = new QTableWidget(10, 4);
        QStringList header;
        header<<"Titlu"<<"Autor"<<"Gen"<<"An aparitie";
        table->setHorizontalHeaderLabels(header);
        pst->addWidget(table);

        search = new QLineEdit;
        pdr->addWidget(search);

        btnAdd = new QPushButton("&Add");
        btnRemove = new QPushButton("&Remove");
        btnModify = new QPushButton("&Modify");
        btnFind = new QPushButton("&Find");
        btnUndo = new QPushButton("&Undo");
        btnFilter = new QPushButton("&Filter");
        btnSort = new QPushButton("&Sort");

        QHBoxLayout* o1 = new QHBoxLayout;
        o1->addWidget(btnAdd);
        o1->addWidget(btnRemove);
        o1->addWidget(btnModify);
        o1->addWidget(btnFind);
        o1->addWidget(btnUndo);
        pdr->addLayout(o1);


        QGridLayout* grid = new QGridLayout;
        pst->addLayout(grid);
        cmbFilter = new QComboBox;
        cmbFilter->addItem("Titlu");
        cmbFilter->addItem("An aparitie");
        cmbSort = new QComboBox;
        cmbSort->addItem("Titlu");
        cmbSort->addItem("Autor");
        cmbSort->addItem("An ap + gen");
        grid->addWidget(btnFilter, 1, 0);
        grid->addWidget(cmbFilter, 1, 1);
        grid->addWidget(btnSort, 2, 0);
        grid->addWidget(cmbSort, 2, 1);
    }

    void getData(vector<string>& rez, int nr=0)
    {
        rez.clear();
        if(nr==0) {
            rez.push_back(Titlu->text().toStdString());
            rez.push_back(Autor->text().toStdString());
            rez.push_back(Gen->text().toStdString());
            rez.push_back(Anap->text().toStdString());
        }
        else{
            rez.push_back(Titlu2->text().toStdString());
            rez.push_back(Autor2->text().toStdString());
            rez.push_back(Gen2->text().toStdString());
            rez.push_back(Anap2->text().toStdString());
        }
    }

    void add(){
        vector<string> rez;
        getData(rez);
        try {
            service.add(rez[0], rez[1], rez[2], std::stoi(rez[3]));
            loadData(service.getAll());
        }
        catch(MyException& me) { MsgBox(me.getMessage().c_str());}
    }

    void remove(){
        vector<string> rez;
        getData(rez);
        try {
            service.remove(rez[0], rez[1], rez[2], std::stoi(rez[3]));
            loadData(service.getAll());
        }
        catch(MyException& me) { MsgBox(me.getMessage().c_str());}
    }

    void modify(){
        vector<string> rez;
        getData(rez);
        vector<string> rez2;
        getData(rez2, 2);
        try {
            service.modify(rez[0], rez[1], rez[2], std::stoi(rez[3]),
                           rez2[0], rez2[1], rez2[2], std::stoi(rez2[3]));
            loadData(service.getAll());
        }
        catch(MyException& me) { MsgBox(me.getMessage().c_str());}
    }

    void undo(){
        try{
            service.undo();
            loadData(service.getAll());
        }
        catch(MyException& me) { MsgBox(me.getMessage().c_str());}
    }

    void find(){
        string txt = search->text().toStdString();
        Carte c;
        try{
            c = service.find(txt);
        }
        catch(MyException& me) { MsgBox(me.getMessage().c_str());}
        setDText(c);
    }

    void filter(){
        string txt = search->text().toStdString();
        vector<Carte> rez;
        if(cmbFilter->currentText() == "Titlu")
        {
            rez = service.filtruTitlu(txt);
            loadData(rez);
        }
        if(cmbFilter->currentText() == "An aparitie")
        {
            rez = service.filtruAnAp(txt);
            loadData(rez);
        }
    }

    void sort(){
        if(cmbSort->currentText() == "Titlu")
        {
            service.sortTitlu();
            loadData(service.getAll());
        }
        if(cmbSort->currentText() == "Autor")
        {
            MsgBox("sort");
            service.sortAutor();
            loadData(service.getAll());
        }
        if(cmbSort->currentText() == "An ap + gen")
        {
            service.sortAnapGen();
            loadData(service.getAll());
        }
    }

    void setDText(const Carte& c)
    {
        Titlu->setText(c.getTitlu().c_str());
        Autor->setText(c.getAutor().c_str());
        Gen->setText(c.getGen().c_str());
        Anap->setValue(c.getAnap());
    }

    void setText(int row, int column=0){
        Carte c = *(service.getAll().begin()+row);
        setDText(c);
    }

    void initConnect(){
        QObject::connect(btnAdd, &QPushButton::clicked, this, &GUI::add);
        QObject::connect(btnRemove, &QPushButton::clicked, this, &GUI::remove);
        QObject::connect(btnModify, &QPushButton::clicked, this, &GUI::modify);
        QObject::connect(btnFind, &QPushButton::clicked, this, &GUI::find);
        QObject::connect(btnUndo, &QPushButton::clicked, this, &GUI::undo);
        QObject::connect(btnFilter, &QPushButton::clicked, this, &GUI::filter);
        QObject::connect(btnSort, &QPushButton::clicked, this, &GUI::sort);
        QObject::connect(table, &QTableWidget::cellClicked, this, &GUI::setText);
    }

public:
    GUI(Service& srv) : service(srv) {
        initGUI();
        loadData(service.getAll());
        initConnect();
    }
};

#endif //TRAINTEST2_GUI_H
