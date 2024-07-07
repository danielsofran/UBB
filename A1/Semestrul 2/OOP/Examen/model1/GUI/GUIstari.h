//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL1_GUISTARI_H
#define MODEL1_GUISTARI_H

#include "includesQT.h"
#include "service.h"
#include "observer.h"

class GUIstari : public QWidget, public Observer{
private:
    string stare; int lastid;
    Service& service;
    vector<Task> displayed;
    Observable* obs;

    QVBoxLayout* ly = new QVBoxLayout(this);
    QTableWidget* table = new QTableWidget(10, 4);
    QPushButton* btnClosed = new QPushButton("Closed");
    QPushButton* btnInProgress = new QPushButton("InProgress");
    QPushButton* btnOpen = new QPushButton("Open");

    void initGui();
    void initConnect();
    void reload();

    void updateObs() override;
public:
    GUIstari(Service& srv, string stare) : service(srv), stare(stare){
        srv.addObserver(this);
        initGui();
        initConnect();
        reload();
    }
    ~GUIstari(){
        service.removeObserver(this);
    }
};

#endif //MODEL1_GUISTARI_H
