//
// Created by Daniel on 19.05.2022.
//

#include "GUI.h"

void GUI::initGUI() {
    QHBoxLayout* layout = new QHBoxLayout;
    setLayout(layout);
    layout->addWidget(lst);
    QVBoxLayout* ldr = new QVBoxLayout;
    ldr->addWidget(Model);
    ldr->addWidget(An);
    ldr->addWidget(btnSortModel);
    ldr->addWidget(btnSortPret);
    ldr->addWidget(btnSortNo);
    layout->addLayout(ldr);
}

void GUI::initConnect() {
    QObject::connect(lst, &QListWidget::currentRowChanged, [&](int row){
        Device d = displayed[row];
        Model->setText(d.getModel().c_str());
        An->setText(std::to_string(d.getAn()).c_str());
    });
    QObject::connect(btnSortModel, &QPushButton::clicked, [&](){
        displayed = service.sortModel();
        loadData();
    });
    QObject::connect(btnSortPret, &QPushButton::clicked, [&](){
        displayed = service.sortPret();
        loadData();
    });
    QObject::connect(btnSortNo, &QPushButton::clicked, [&](){
        displayed = service.getAll();
        loadData();
    });
}

void GUI::loadData() {
    lst->clear();
    for(Device& d : displayed){
        QListWidgetItem *item = new QListWidgetItem(lst);
        string text = d.getModel() + " " + d.getCuloare() + " " + std::to_string(d.getPret());
        item->setText(text.c_str());
        if(d.getCuloare() == "negru"){
            item->setBackground(Qt::black);
            item->setForeground(Qt::white);
        }
        if(d.getCuloare() == "albastru")
            item->setBackground(Qt::blue);
        if(d.getCuloare() == "galben")
            item->setBackground(Qt::yellow);
        if(d.getCuloare() == "rosu")
            item->setBackground(Qt::red);
    }
}
