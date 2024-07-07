//
// Created by Daniel on 18.05.2022.
//

#include "tests.h"

void test(){
    testDomainRepo();
}

void testDomainRepo(){
    FileRepository r("repo.txt");
    assert(r.getAll().size() == 10);
    auto rez = r.filter([](Apartament ap) {return ap.getStrada()=="Lacului";});
    assert(rez.size() == 2);
    assert(rez[0] == Apartament(340,"Lacului",5400));
    assert(rez[0].getStrada() == "Lacului");
    assert(rez[0].getSupr() == 340);
    assert(rez[0].getPret() == 5400);
    Service s(r);
    string data = "Lacului 340 5400";
    Apartament ap = s.find(data);
    assert(s.find(data) == rez[0]);
    assert(s.filterPret(5000, 6000).size() == 2);
    assert(s.filterSup(300, 200).size() == 5);
    assert(s.getAll() == r.getAll());
    //s.remove(data);
}