//
// Created by Daniel on 15.05.2022.
//

#ifndef TRAINTEST2_GUI_H
#define TRAINTEST2_GUI_H

#include "QWidget"
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
            table->setItem(i, 2, new QTableWidgetItem(std::to_string(elem.getAnap()).c_str()));
            i++;
        }
    }

    void initGUI(){
        QHBoxLayout * layoutMain = new QHBoxLayout;
        setLayout(layoutMain);

        QVBoxLayout* pdr = new QVBoxLayout;
        QVBoxLayout* pst = new QVBoxLayout;
        layoutMain->addLayout(pdr);
        layoutMain->addLayout(pst);

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
        btnFind = new QPushButton("&Modify");
        btnUndo = new QPushButton("&Undo");
        btnFilter = new QPushButton("&Undo");
        btnSort = new QPushButton("&Undo");

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
        grid->addWidget(btnFilter, 0, 0);
        grid->addWidget(cmbFilter, 0, 1);
        grid->addWidget(btnSort, 1, 0);
        grid->addWidget(cmbSort, 1, 1);
    }

public:
    GUI(Service& srv) : service(srv) {
        initGUI();
        loadData(service.getAll());
    }
};

#endif //TRAINTEST2_GUI_H
