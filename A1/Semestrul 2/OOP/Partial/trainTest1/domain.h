//
// Created by Daniel on 14.05.2022.
//

#ifndef UNTITLED_DOMAIN_H
#define UNTITLED_DOMAIN_H

#include <string>
#include <iostream>
#include <utility>

using std::string;
using std::istream;
using std::ostream;

class Masina{
private:
    string nrinmat;
    string prod;
    string model;
    string tip;
public:
    Masina() = default;
    Masina(string nrinmat, string prod, string model, string tip){
        this->nrinmat = std::move(nrinmat);
        this->prod = std::move(prod);
        this->model = std::move(model);
        this->tip = std::move(tip);
    }
    Masina(const Masina& m) = default;
    Masina& operator=(const Masina&) = default;

    string getNrInmat() const {return nrinmat;}
    string getProd() const  {return prod;}
    string getModel() const {return model;}
    string getTip() const {return tip;}
    void setNrInmat(string nrinmat1) {this->nrinmat = std::move(nrinmat1);}
    void setProd(string prod1) {this->prod = std::move(prod1);}
    void setModel(string model1) {this->model = std::move(model1);}
    void setTip(string tip1) {this->tip = std::move(tip1);}

    friend bool operator==(const Masina& m1, const Masina& m2)
    {
        return m1.nrinmat == m2.nrinmat;
    }
    friend bool operator!=(const Masina& m1, const Masina& m2) {return !(m1==m2);}

    friend istream& operator>>(istream& in, Masina& m)
    {
        in>>m.nrinmat>>m.prod>>m.model>>m.tip;
        return in;
    }
    friend ostream& operator<<(ostream& out, const Masina& m)
    {
        out<<m.nrinmat<<' '<<m.prod<<' '<<m.model<<' '<<m.tip;
        return out;
    }
};

# define valid(s) (s.length() != 0)

#endif //UNTITLED_DOMAIN_H
