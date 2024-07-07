#include <iostream>
#include <cassert>
#include "TestExtins.h"
#include "TestScurt.h"
using namespace std;

#include "Colectie.h"
#include "IteratorColectie.h"

void creeazaColectie(Colectie& c) {
//    c.adauga(2);
//    c.adauga(5);
//    c.adauga(12);
//    c.adauga(25);
//    c.adauga(2);
//    c.adauga(15);
//    c.adauga(25);
    for(int j=0;j<2;++j)
        for(int i=0;i<23;i++)
            c.adauga(i);
}

void tiparesteColectie(const Colectie& c) {
    IteratorColectie it = c.iterator();
    while (it.valid()) {
        TElem e = it.element();
        cout <<e<<" "<<endl;
        it.urmator();
    }
}

int main() {
	testAll();
    cout<<"Test scurt gata!\n";
    Colectie c;
    // stergere in ordine
    for(int i=-4;i<=4;i+=2)
        c.adauga(i);
    for(int i=-5;i<=5;++i) {
        bool ok = c.sterge(i);
        assert(ok == !(i%2));
    }
    // stergere in ord inversa
    for(int i=-4;i<=4;i+=2)
        c.adauga(i);
    for(int i=5;i>-5;--i) {
        bool ok = c.sterge(i);
        assert(ok == !(i%2));
    }
    cout<<"Test personal gata!\n";
	testAllExtins();
	cout<<"\nEnd";
}
