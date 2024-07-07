//
// Created by Daniel on 14.05.2022.
//

#ifndef UNTITLED_GUI_H
#define UNTITLED_GUI_H

#include "service.h"
#include "qlabel.h"
#include <QPushButton>
#include "QFormLayout"
#include "QHBoxLayout"
#include "QLineEdit"
#include "QListWidget"
#include "string"
#include "sstream"
#include "qdebug.h"
#include "QMessageBox"

using std::to_string;

class GUI : public QWidget {
private:
    Service& service;

    QFormLayout* form;
    QHBoxLayout* layout;
    QVBoxLayout* ldr;

    QListWidget* list;

    QLineEdit* Nr;
    QLineEdit* Prod;
    QLineEdit* Model;
    QLineEdit* Tip;

    QPushButton* btnAdd;
    QPushButton* btnRemove;
    QPushButton* btnModify;
    QPushButton* btn;

    void initGUI(){
        form = new QFormLayout;
        layout = new QHBoxLayout;
        setLayout(layout);
        list = new QListWidget;
        layout->addWidget(list);

        Nr = new QLineEdit;
        Prod = new QLineEdit;
        Model = new QLineEdit;
        Tip = new QLineEdit;

        ldr = new QVBoxLayout;
        form->addRow("Nr Inmat", Nr);
        form->addRow("Prod", Prod);
        form->addRow("Model", Model);
        form->addRow("Tip", Tip);
        ldr->addLayout(form);
        btnAdd = new QPushButton("Add");
        btnRemove = new QPushButton("Remove");
        btnModify = new QPushButton("Modify");
        ldr->addWidget(btnAdd);
        ldr->addWidget(btnRemove);
        ldr->addWidget(btnModify);
        layout->addLayout(ldr);
    }

    void initConnect(){

        QObject::connect(btnAdd, &QPushButton::clicked, [&](){
            QMessageBox::information(this, "Info", "add connect");
            try{service.add(Nr->text().toStdString(), Prod->text().toStdString(), Model->text().toStdString(), Tip->text().toStdString());}
            catch(MyException& me) {QMessageBox::information(this, "Info", "add error");}
            loadData();
        });
        QObject::connect(btnRemove, &QPushButton::clicked, [&](){
            service.remove(Nr->text().toStdString(), Prod->text().toStdString(), Model->text().toStdString(), Tip->text().toStdString());
            loadData();
        });
        QObject::connect(btnModify, &QPushButton::clicked, [&](){
            service.modify(Nr->text().toStdString(), Prod->text().toStdString(), Model->text().toStdString(), Tip->text().toStdString(),
                           Nr->text().toStdString(), Prod->text().toStdString(), Model->text().toStdString(), Tip->text().toStdString());
            loadData();
        });
    }

    void loadData(){
        list->clear();
        //QMessageBox::information(this, "Info", to_string(service.getAll().size()).c_str());
        for(const auto& m : service.getAll())
        {
            std::ostringstream sout;
            sout<<m;
            QListWidgetItem* item = new QListWidgetItem;
            item->setText(sout.str().c_str());
            list->addItem(item);
        }
    }

public:
    GUI(Service& s) : service(s){
        initGUI();
        initConnect();
        loadData();
    }
};

#endif //UNTITLED_GUI_H
