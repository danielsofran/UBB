//
// Created by Daniel on 08.06.2022.
//

#ifndef EXAMEN_GUITABLA_H
#define EXAMEN_GUITABLA_H

#include "service.h"
#include "includesQT.h"

#define MsgBox(txt) QMessageBox::warning(NULL, "Atentie", txt)

class GUITabla : public QWidget{
private:
    Service& service;
    int dim;
    int id;
    bool playable;
    QPushButton*** mat;

    QGridLayout* grid = new QGridLayout;

    void initGUI(); // initializare componente + semnale, daca e playable
    void reload(); // reincarc datele

public:
    GUITabla(Service& s, int id, bool play) : service(s), id(id), playable(play){
        dim = s.find(id).getDim();
        mat = new QPushButton**[dim];
        for(int i=0;i<dim;++i)
            mat[i] = new QPushButton*[dim];
        initGUI();
    }
    ~GUITabla(){
        for(int i=0;i<dim;++i)
            delete mat[i];
        delete mat;
    }
};

#endif //EXAMEN_GUITABLA_H
