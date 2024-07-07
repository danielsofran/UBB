//
// Created by Daniel on 17.05.2022.
//

#ifndef TRAINTESTDOCTORI_DOMAIN_H
#define TRAINTESTDOCTORI_DOMAIN_H

#include <string>

using std::string;

class Doctor{
private:
    string cnp;
    string nume;
    string prenume;
    string sectie;
    bool inconcediu= false;
public:
    Doctor()=default; // constructor default
    Doctor(string cnp, string nume, string prenume, string sectie, bool inconcediu) :
    cnp{cnp}, nume{nume}, prenume{prenume}, sectie{sectie}, inconcediu{inconcediu} {}; // constructor cu parametrii
    Doctor(const Doctor&) = default;

    string getCnp() const;
    string getNume() const;
    string getPrenume() const;
    string getSectie() const;
    bool getInConcediu() const;

    bool operator==(const Doctor&) const;
    bool operator!=(const Doctor&) const;
};

#endif //TRAINTESTDOCTORI_DOMAIN_H
