//
// Created by Daniel on 06.06.2022.
//

#include "GUI.h"

void GUI::initGUI() {
    model->setElems(service.getAll(), service.filterPret(-1));
    table->setModel(model);
    model->setElems(service.getAll(), service.filterPret(-1));

    setLayout(lymain);
    lymain->addWidget(table);
    lymain->addLayout(lydr);

    setWindowTitle(tip.c_str());
    lblNr->setText(QString::number(nr));
    lydr->addWidget(lblNr);

    form->addRow("Id", lineId);
    form->addRow("NUme", lineNume);
    form->addRow("Tip", lineTip);
    form->addRow("Pret", linePret);
    lydr->addLayout(form);
    lydr->addWidget(btn);

    slider->setMinimum(1);
    slider->setMaximum(100);
    slider->setTickInterval(1);
    slider->setEnabled(true);
    lydr->addWidget(slider);

}

void GUI::connect() {
    QObject::connect(btn, &QPushButton::clicked, [&](){
        string s1, s2, s3, s4;
        s1 = lineId->text().toStdString();
        s2 = lineNume->text().toStdString();
        s3 = lineTip->text().toStdString();
        s4 = linePret->text().toStdString();
        try{service.add(s1, s2, s3, s4);}
        catch(MyException& ex) { MsgBox(ex.what());}
        reload();
    });
    QObject::connect(slider, &QSlider::sliderReleased, [&](){
        int val = slider->value();
        auto filter = service.filterPret(val);
        reload(filter);
    });
}

void GUI::reload(const vector<Produs>& filter) {
    auto v = service.getAll();
    std::sort(v.begin(), v.end(), [](const Produs& p1, const Produs& p2){
        return p1.getPret() < p2.getPret();
    });
    model->setElems(v, filter);
}

void GUI::updateLines(const Produs & p) {
    lineId->setText(QString::number(p.getId()));
    linePret->setText(QString::number(p.getPret()));
    lineNume->setText(QString::fromStdString(p.getNume()));
    lineTip->setText(QString::fromStdString(p.getTip()));
}

void GUI::clearLines() {
    lineId->setText("");
    linePret->setText("");
    lineNume->setText("");
    lineTip->setText("");
}

void GUI::updateObs() {
    auto f = service.tipuri();
    setWindowTitle(tip.c_str());
    lblNr->setText(QString::number(f[tip]));
}
