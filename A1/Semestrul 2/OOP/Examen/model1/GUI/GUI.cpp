//
// Created by Daniel on 06.06.2022.
//

#include "GUI.h"

void GUI::initGUi() {
    lymain->addWidget(table);
    lymain->addLayout(lydr);
    lydr->addLayout(form);
    lydr->addWidget(btnAdd);
    lydr->addWidget(lineSearch);
    lydr->addWidget(btnSearch);

    form->addRow("Id", lineId);
    form->addRow("Descriere", lineDescr);
    form->addRow("Stare", lineStare);
    form->addRow("Programatori", lineProgr);
}

void GUI::reload() {
    std::sort(displayed.begin(), displayed.end());
    table->clearContents();
    table->setRowCount(0);
    int rows=0;
    for(const auto& elem : displayed)
    {
        table->insertRow(rows);
        auto cel1 = new QTableWidgetItem(QString::number(elem.getId()));
        auto cel2 = new QTableWidgetItem(elem.getDescriere().c_str());
        auto cel3 = new QTableWidgetItem(elem.getStare().c_str());
        auto cel4 = new QTableWidgetItem(QString::number(elem.getProgramatori().size()));
        table->setItem(rows, 0, cel1);
        table->setItem(rows, 1, cel2);
        table->setItem(rows, 2, cel3);
        table->setItem(rows, 3, cel4);
        ++rows;
    }
}

void GUI::initConnect() {
    QObject::connect(btnAdd, &QPushButton::clicked, [&](){
        int id = lineId->text().toInt();
        auto descr = lineDescr->text().toStdString();
        auto stare = lineStare->text().toStdString();
        vector<string> progr;
        string txt = lineProgr->toPlainText().toStdString(), line;
        std::istringstream sin(txt);
        while(std::getline(sin, line))
            if(!line.empty())
                progr.push_back(line);
        try{
            service.add(id, descr, progr, stare);
            displayed = service.getAll();
            reload();
        }
        catch(MyException& me) {MsgBox(me.what());}
    });
    QObject::connect(btnSearch, &QPushButton::clicked, [&](){
         string txt = lineSearch->text().toStdString();
         displayed = service.filterNumePr(txt);
         reload();
    });
}

void GUI::updateObs() {
    displayed = service.getAll();
    reload();
}
