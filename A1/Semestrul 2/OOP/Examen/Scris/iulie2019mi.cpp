//#include <bits/std++.h> // include toate librariile
#include <string>
#include <vector>
#include <bitset>
#include "iostream"

using namespace std;

class Vehicul{
private:
    string marca;
    double pret;
    string tip;

public:
    Vehicul(string marca, double pret, string tip) {
         this->marca = marca;
         this->pret = pret;
         this->tip = tip;
    }

    string getMarca() { return marca; }
    string getTip() { return tip; }
    double getPret() { return  pret; }

    void setPret(double pret) { this->pret = pret; }

    virtual void sunet() = 0;

    string toString() { return marca + to_string(pret) + tip; }
};

class Masina: public  Vehicul{
private:
    string culoare;

public:
    Masina(string marca, double pret, string tip, string c): Vehicul(marca, pret, tip), culoare(c){}

    void sunet() override {
        cout <<"vrom vrom";
    }
};

bitset<1000000000000000> f;

int main() {
    Masina v1("Audi", 2000, "Motocicleta", "maro");
    Masina v2("Passat", 2200, "Trotineta", "maro");
    Masina v3("Audi", 4000, "Bicicleta", "maro");

    v1.getMarca();
    v1.sunet();
}