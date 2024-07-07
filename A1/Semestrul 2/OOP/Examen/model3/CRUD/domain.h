//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL3_DOMAIN_H
#define MODEL3_DOMAIN_H

#include "string"

using std::string;

class Produs{
private:
    int id=0;
    string nume;
    string tip;
    double pret=0;
public:
    Produs() = default;
    Produs(int id, string nume, string tip, double pret) :
        id{id}, nume{nume}, pret{pret}, tip{tip} {}
    Produs(const Produs&) = default;
    Produs& operator=(const Produs&) = default;

    int getId() const {
        return id;
    }

    const string &getNume() const {
        return nume;
    }

    const string &getTip() const {
        return tip;
    }

    double getPret() const {
        return pret;
    }

    int getNrVoc() const{
        int rez=0;
        for(char ch : nume)
            if(string("aeiouAEIOU").find(ch) != string::npos)
                ++rez;
        return rez;
    }

    bool operator==(const Produs &rhs) const {
        return id == rhs.id;
    }

    bool operator!=(const Produs &rhs) const {
        return !(rhs == *this);
    }
};

#endif //MODEL3_DOMAIN_H
