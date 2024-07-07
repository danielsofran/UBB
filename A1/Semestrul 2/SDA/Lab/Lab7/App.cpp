#include <iostream>

#include "TestExtins.h"
#include "TestScurt.h"
#include "Colectie.h"
#include "cassert"
using namespace std;


int main() {
    Colectie c;
    int n=4, k=1;
    for(int i=0; i<n;++i)
        for(int j=0;j<k;++j)
            c.adauga(i);
    assert(c.dim() == k*n);
    for(int i=0; i<n;++i)
        for(int j=0;j<k;++j)
            assert(c.sterge(i)== true);
    testAll();
    testAllExtins();

}
