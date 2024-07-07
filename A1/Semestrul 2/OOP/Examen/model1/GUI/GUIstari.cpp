//
// Created by Daniel on 06.06.2022.
//

#include "GUIstari.h"

void GUIstari::initGui() {
    ly->addWidget(table);
    ly->addWidget(btnOpen);
    ly->addWidget(btnInProgress);
    ly->addWidget(btnClosed);
}

void GUIstari::initConnect() {
    QObject::connect(table, &QTableWidget::cellClicked, [&](int r, int c) {lastid = displayed[r].getId();});
    QObject::connect(btnOpen, &QPushButton::clicked, [&]{
         service.changeState(lastid, "open");
    });
    QObject::connect(btnClosed, &QPushButton::clicked, [&]{
        service.changeState(lastid, "closed");
    });
    QObject::connect(btnInProgress, &QPushButton::clicked, [&]{
        service.changeState(lastid, "inprogress");
    });
}

void GUIstari::reload() {
    displayed = service.filterStare(stare);
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

void GUIstari::updateObs() {
    reload();
}
