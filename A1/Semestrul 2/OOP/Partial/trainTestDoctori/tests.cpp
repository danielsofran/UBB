#include "tests.h"

void test(){
    testDomainRepo();
    testService();
}

void testDomainRepo(){
    FileRepository r("repo.txt");
    assert(r.size() == 10);
    Doctor d4{"434244634","Florin","Vasile","neuro-chirurgie",0};
    assert(r.getAll()[3] == d4);
    assert(r.getAll()[1].getInConcediu() == 1);
    assert(r.getAll()[4] != d4);
    assert(d4.getCnp() == "434244634");
    assert(d4.getNume() == "Florin");
    assert(d4.getPrenume() == "Vasile");
    assert(d4.getInConcediu() == 0);
    Doctor d;
    assert(d != d4);
    Doctor d1(d4);
    assert(d1 == d4);
}

void testService(){
    FileRepository r("repo.txt");
    Doctor d4{"434244634","Florin","Vasile","neuro-chirurgie",0};
    Service s(r);
    assert(s.getAll().size() == 10);
    assert(s.find("Florin","Vasile","neuro-chirurgie") == d4);
    assert(s.find("Florin Vasile neuro-chirurgie") == d4);
    Doctor di{"356456","Istvan","Daniel","neurologie",0};
    vector<Doctor> rez = s.filterNume("Istvan");
    assert(rez.size() == 3);
    assert(rez[0] == di);
    rez = s.filterSectie("cardiologie");
    assert(rez.size() == 4);
}