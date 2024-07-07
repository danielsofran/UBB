//
// Created by Daniel on 06.06.2022.
//

#include "bits/stdc++.h"
using namespace std;

class Transformer{
public:
    virtual void transform(vector<int>& v) = 0;
    // virtual ~Transformer() = default;
};

class Adder : public Transformer{
private:
    int cat;
public:
    Adder(int nr) : cat(nr) {}
    void transform(vector<int>& v) override{
        cout<<"\nInainte de add:\n";
        for(auto& elem : v)
            cout<<elem<<' ',
            elem += cat;
        cout<<"\nDupa add:\n";
        for(auto& elem : v)
            cout<<elem<<' ';
        cout<<'\n';
    }
};

class Swapper : public Transformer{
public:
    void transform(vector<int>& v) override{
        cout<<"\nInainte de swap:\n";
        for(int i=0;i<v.size()-1;i+=2)
            cout<<v[i]<<' '<<v[i+1]<<' ',
            swap(v[i], v[i+1]);
        cout<<"\nDupa swap:\n";
        for(auto& elem : v)
            cout<<elem<<' ';
        cout<<'\n';
    }
};

class Nuller : public Adder{
public:
    Nuller(int nr) : Adder(nr) {}
    void transform(vector<int>& v) override{
        cout<<"\nInainte de nuller:\n";
        for(auto& elem : v)
            cout<<elem<<' ';
        cout<<'\n';
        Adder::transform(v);
        for(auto& elem : v)
            if(elem > 10)
                elem = 0;
        cout<<"\nDupa nuller:\n";
        for(auto& elem : v)
            cout<<elem<<' ';
        cout<<'\n';
    }
};

class Composite : public Transformer{
private:
    Transformer *t1, *t2;
public:
    Composite(Transformer* p1, Transformer* p2) : t1(p1), t2(p2) {}
    void transform(vector<int>& v) override{
        t1->transform(v);
        t2->transform(v);
    }
    ~Composite(){
        delete t1;
        delete t2;
    }
};

class Numbers{
private:
    vector<int> nrs;
    Transformer* t;
public:
    Numbers(Transformer* p) : t(p){}
    void add(int nr){
        nrs.push_back(nr);
    }
    vector<int> transform(){
        vector<int> rez = nrs;
        std::sort(rez.begin(), rez.end(), [](int a, int b){return a>b;});
        t->transform(rez);
        return rez;
    }
    ~Numbers(){
        delete t;
    }
};

Numbers* creeaza(){
    Transformer* nuller = new Nuller(9);
    Transformer* swapper = new Swapper();
    Transformer* adder = new Adder(3);
    Transformer* comp1 = new Composite(swapper, adder);
    Transformer* comp2 = new Composite(nuller, comp1);
    Numbers* nrs = new Numbers(comp2);
//    for(int i=1;i<=5;++i)
//    nrs->add(i);
//    auto r1 = nrs->transform();
//    for(auto x : r1) cout<<x<<' '; cout<<'\n';
    return nrs;
}

int main(){
    Numbers* n1 = creeaza(), *n2 = creeaza();
    for(int i=0;i<5;++i)
        n1->add(i%3),
        n2->add(7-i%3);
    const auto& r1 = n1->transform();
    //auto r2 = n2->transform();
    for(auto x : r1) cout<<x<<' '; cout<<'\n';
    //for(auto x : r2) cout<<x<<' '; cout<<'\n';
    delete n1;
    delete n2;
    return 0;
}