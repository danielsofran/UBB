//
// Created by Daniel on 07.06.2022.
//

#include "GUI.h"

void GUI::initGUI() {
    model = new MyModel(service);
    table->setModel(model);

    sliderRank->setMinimum(1);
    sliderRank->setMaximum(10);

    lymain->addWidget(table);
    lymain->addLayout(lydr);
    lydr->addLayout(form);
    form->addRow("Titlu", lineTitlu);
    form->addRow("Rank", sliderRank);
    lydr->addWidget(btnModify);
    lydr->addWidget(btnSterge);
}

void GUI::initConnect() {
    QObject::connect(table->selectionModel(), &QItemSelectionModel::selectionChanged, [&](){
        auto sels = table->selectionModel()->selectedIndexes();
        if(sels.isEmpty()) return ;
        lastid = sels.at(0).data(Qt::UserRole).toInt();
    });
    QObject::connect(btnSterge, &QPushButton::clicked, [&](){
        try{service.sterge(lastid);}
        catch(MyException& me){ MsgBox(me.what()); }
    });
    QObject::connect(btnModify, &QPushButton::clicked, [&](){
        auto titlu = lineTitlu->text().toStdString();
        auto rank = sliderRank->value();
        try{service.modify(lastid, titlu, rank);}
        catch(MyException& me){ MsgBox(me.what()); }
    });
}

void GUI::paintEvent(QPaintEvent* ev) {
    QPainter p(this);
    p.setBrush(QBrush(Qt::black));
    int x = this->width() - 10, y = this ->height()-10;
    for(const Melodie& m : service.getAll())
        p.drawLine(x, y, x, y-m.getRank()/20.0*y),
        x-=40;
}

void GUI::updateObs() {
    repaint();
}
