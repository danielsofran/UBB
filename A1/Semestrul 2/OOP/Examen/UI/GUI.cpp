//
// Created by Daniel on 08.06.2022.
//

#include "GUI.h"

void GUI::initGUI() {
    setLayout(lymain);
    lymain->addWidget(table);
    lymain->addLayout(lydr);
    lydr->addLayout(form);
    lydr->addWidget(btnAdd);
    lydr->addWidget(btnModif);
    lydr->addWidget(btnPlay);
    lydr->addWidget(btnPlayView);
    form->addRow("Dim", lineDim);
    form->addRow("Table", lineTabla);
    form->addRow("Jucator", lineJucator);
    form->addRow("Stare", lineStare);
}

void GUI::reload() {
    table->clearContents();
    table->setRowCount(0);
    auto v = service.getAll();
    std::sort(v.begin(), v.end());
    int rows = 0;
    for(const auto& elem : v){
        table->insertRow(rows);
        auto cel1 = new QTableWidgetItem(QString::number(elem.getId()));
        auto cel2 = new QTableWidgetItem(QString::number(elem.getDim()));
        auto cel3 = new QTableWidgetItem(QString::fromStdString(elem.getTabla()));
        auto cel4 = new QTableWidgetItem(QString::fromStdString(elem.getJucator()));
        auto cel5 = new QTableWidgetItem(QString::fromStdString(elem.getStare()));
        table->setItem(rows, 0, cel1);
        table->setItem(rows, 1, cel2);
        table->setItem(rows, 2, cel3);
        table->setItem(rows, 3, cel4);
        table->setItem(rows, 4, cel5);
        ++rows;
    }
}

void GUI::initConnect() {
    QObject::connect(table, &QTableWidget::cellClicked, [&](int row, int col){
        currentid = table->item(row, 0)->text().toInt();
    });
    QObject::connect(btnAdd, &QPushButton::clicked, [&](){
        string dim = lineDim->text().toStdString();
        string tabla = lineTabla->text().toStdString();
        string jucator = lineJucator->text().toStdString();
        try{
            service.add(dim, tabla, jucator);
            reload();
        }
        catch(MyException& me){MsgBox(me.what());}
    });
    QObject::connect(btnModif, &QPushButton::clicked, [&](){
        string dim = lineDim->text().toStdString();
        string tabla = lineTabla->text().toStdString();
        string jucator = lineJucator->text().toStdString();
        string stare = lineStare->text().toStdString();
        try{
            service.modify(currentid, dim, tabla, jucator, stare);
            reload();
        }
        catch(MyException& me){MsgBox(me.what());}
    });
    QObject::connect(btnPlayView, &QPushButton::clicked, [&](){
         try{
             service.find(currentid);
         }
         catch(MyException& me){
             MsgBox(me.what());
             return ;
         }
         GUITabla* guit = new GUITabla(service, currentid, false);
         guit->show();
    });
    QObject::connect(btnPlay, &QPushButton::clicked, [&](){
        try{
            service.find(currentid);
        }
        catch(MyException& me){
            MsgBox(me.what());
            return ;
        }
        GUITabla* guit = new GUITabla(service, currentid, true);
        guit->show();
    });
}
