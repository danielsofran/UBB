//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL3_MODEL_H
#define MODEL3_MODEL_H

#include "includesQT.h"
#include "CRUD/service.h"

class MyModel : public QAbstractTableModel{
private:
    vector<Produs> v;
    vector<Produs> filtered;
public:
    int rowCount(const QModelIndex& parent = QModelIndex()) const override{
        return v.size();
    }
    int columnCount(const QModelIndex& parent = QModelIndex()) const override{
        return 5;
    }
    QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const{
        Produs p = v[index.row()];
        if(role==Qt::DisplayRole){
            int col = index.column();
            if(col==0) return QString::number(p.getId());
            if(col==1) return QString::fromStdString(p.getNume());
            if(col==2) return QString::fromStdString(p.getTip());
            if(col==3) return QString::number(p.getPret());
            if(col==4) return QString::number(p.getNrVoc());
        }
        if(role==Qt::BackgroundRole){
            if(std::find(filtered.begin(), filtered.end(), p) != filtered.end())
                return QBrush(Qt::red);
        }
        return QVariant();
    }
    void setElems(const vector<Produs>& elems, const vector<Produs>& filtr)
    {
        v = elems;
        filtered = filtr;
        QModelIndex ss = createIndex(0, 0);
        QModelIndex dj = createIndex(rowCount(), columnCount());
        emit dataChanged(ss, dj);
        emit layoutChanged();
    }
};

#endif //MODEL3_MODEL_H
