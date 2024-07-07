//
// Created by Daniel on 05.06.2022.
//

#include "bits/stdc++.h"
using namespace std;

class Smoothy{
private:
    int pret;
public:
    Smoothy(int pret) : pret(pret){}
    virtual int getPret() const {return pret; };
    virtual string descriere() = 0;
    virtual ~Smoothy() =default;
};

class DecoratorSmoothy : public Smoothy{
private:
    Smoothy* smoothy;
public:
    DecoratorSmoothy(int pret, Smoothy* s) : Smoothy(pret), smoothy(s) {}
    string descriere() override{
        return smoothy->descriere();
    }
    int getPret() const {return smoothy->getPret(); };
    ~DecoratorSmoothy() override{
        delete smoothy;
    }
};

class SmoothyCuFrisca : public DecoratorSmoothy{
public:
    SmoothyCuFrisca(int pret, Smoothy* s) : DecoratorSmoothy(pret, s) {}
    string descriere() override{
        return DecoratorSmoothy::descriere()+" cu frisca";
    }
    int getPret() const {return DecoratorSmoothy::getPret()+2; };
};

class SmoothyCuUmbreluta : public DecoratorSmoothy{
public:
    SmoothyCuUmbreluta(int pret, Smoothy* s) : DecoratorSmoothy(pret, s) {}
    string descriere() override{
        return DecoratorSmoothy::descriere()+" cu umbreluta";
    }
    int getPret() const {return DecoratorSmoothy::getPret()+3; };
};

class BasicSmoothy: public Smoothy{
private:
    string nume;
public:
    BasicSmoothy(int pret, string nume) : Smoothy(pret), nume(nume){}
    string descriere() override{
        return nume;
    }
};

vector<Smoothy*> f(){
    vector<Smoothy*> rez;
    Smoothy* smoothy1 = new BasicSmoothy(2, "kiwi");
    SmoothyCuFrisca* decorator11 = new SmoothyCuFrisca(2, smoothy1);
    SmoothyCuUmbreluta* decorator12 = new SmoothyCuUmbreluta(2, decorator11);//
    rez.push_back(decorator12);

    Smoothy* smoothy2 = new BasicSmoothy(10, "capsuni");
    SmoothyCuFrisca* decorator2 = new SmoothyCuFrisca(2, smoothy2);//
    rez.push_back(decorator2);

    Smoothy* smoothy3 = new BasicSmoothy(5, "kiwi"); //
    rez.push_back(smoothy3);

    return rez;
}

int main(){
    auto v = f();
    sort(v.begin(), v.end(), [](Smoothy* s1, Smoothy* s2)
        {return s1->descriere() < s2->descriere();});
    for(auto s : v)
        cout<<s->descriere()<<"\t"<<s->getPret()<<'\n';
    for(auto s : v)
        delete s;
}

