//
// Created by Daniel on 08.06.2022.
//

#include "GUITabla.h"

void GUITabla::initGUI() {
    setLayout(grid);

    for(int i=0;i<dim;++i)
        for(int j=0;j<dim;++j)
        {
            string str = service.find(id).getPoz(i, j);
            if(str == "-") str = " ";
            QPushButton* btn = new QPushButton(str.c_str());
            if(playable)
            {
                QObject::connect(btn, &QPushButton::clicked, [&,i,j](){
                     string player = service.find(id).getJucator();
                     try{
                         service.modifyPoz(id, i, j, player);
                         //btn->setText(player.c_str());
                         reload();
                     }
                     catch(MyException& me) {MsgBox(me.what());}
                });
            }
            grid->addWidget(btn, i, j);
            mat[i][j] = btn;
        }
}

void GUITabla::reload() {
    for(int i=0;i<dim;++i)
        for(int j=0;j<dim;++j)
        {
            string str = service.find(id).getPoz(i, j);
            if(str == "-") str = " ";
            mat[i][j]->setText(str.c_str());
        }
}


