//
// Created by Daniel on 07.06.2022.
//

#ifndef MODEL2_MODEL_H
#define MODEL2_MODEL_H

#include "includesQT.h"
#include "service.h"
#include "observer.h"

class MyModel : public QAbstractTableModel, public Observer{
private:
    Service& service;
public:
    MyModel(Service& s) : service(s) {s.addObserver(this); } // constructor
    // numarul de linii
    int rowCount(const QModelIndex& index = QModelIndex()) const override{
        return service.getAll().size();
    }
    // numarul de coloane
    int columnCount(const QModelIndex& index = QModelIndex()) const override{
        return 5;
    }
    // functia care preia date
    QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override{
        if(role == Qt::DisplayRole){
            Melodie m = service.getAllSorted()[index.row()];
            if(index.column() == 0)
                return QString::number(m.getId());
            if(index.column() == 1)
                return QString::fromStdString(m.getTitlu());
            if(index.column() == 2)
                return QString::fromStdString(m.getArtist());
            if(index.column() == 3)
                return QString::number(m.getRank());
            if(index.column() == 4)
                return QString::number(service.nrMelodiiRank(m.getRank()));
        }
        if(role == Qt::UserRole){
            Melodie m = service.getAllSorted()[index.row()];
            return m.getId();
        }
        return {};
    }
    // update in caz de notificare
    void updateObs() override{
        auto ss = createIndex(0,0);
        auto dj = createIndex(rowCount(), columnCount());
        emit dataChanged(ss, dj);
        emit layoutChanged();
    }
    ~MyModel(){ service.removeObserver(this); } // destructor
};

#endif //MODEL2_MODEL_H
