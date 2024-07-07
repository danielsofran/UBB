//
// Created by Daniel on 19.05.2022.
//

#include "tests.h"
#include "filesystem"

void test(){
    remove("testrepo.txt");
    std::filesystem::copy_file("repo.txt", "testrepo.txt");

    Repository r("testrepo.txt");
    assert(r.getAll().size() == 10);
    Apartament a1(300,"Hasdeu",2000);
    r.sterge(a1);
    assert(r.getAll().size()==9);

    Service s(r);
    try{ s.sterge(a1); assert(false);}
    catch(std::logic_error&) {}
    auto rez = s.filterSupr(100, 125);
    assert(rez.size() == 2);
    assert(rez[0].getSupr() == 100);
    assert(rez[0].getStrada() == "C R Vivu");
    assert(rez[0].getPret() == 1600);
    rez = s.filterPret(5000, 40000);
    assert(rez.size() == 2);
}