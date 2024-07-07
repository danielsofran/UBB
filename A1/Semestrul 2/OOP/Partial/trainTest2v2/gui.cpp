//
// Created by Daniel on 15.05.2022.
//

// You may need to build the project (run Qt uic code generator) to get "ui_GUI.h" resolved

#include "gui.h"
#include "ui_GUI.h"


GUI::GUI(QWidget *parent) :
        QWidget(parent), ui(new Ui::GUI) {
    ui->setupUi(this);
}

GUI::~GUI() {
    delete ui;
}

