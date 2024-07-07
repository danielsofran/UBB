//
// Created by Daniel on 17.05.2022.
//

#include "tests.h"

void test(){
    testDomainRepo();
}

void testDomainRepo()
{
    Procesor p("AMD", 4, "scl", 8000);
    assert(p.getNume() == "AMD");
    assert(p.getNrThreaduri() == 4);
    assert(p.getSoclu() == "scl");
    assert(p.getPret() == 8000);
    std::istringstream sin("AMD,4,scl,8000,");
    sin >> p;
    assert(p.getNume() == "AMD");
    assert(p.getNrThreaduri() == 4);
    assert(p.getSoclu() == "scl");
    assert(p.getPret() == 8000);

    PlacaDeBaza pb("AMD", "scl", 8000);
    assert(pb.getNume() == "AMD");
    assert(pb.getSoclu() == "scl");
    assert(pb.getPret() == 8000);
    std::istringstream sin2("AMD,scl,8000,");
    sin2 >> pb;
    assert(pb.getNume() == "AMD");
    assert(pb.getSoclu() == "scl");
    assert(pb.getPret() == 8000);

    FileRepository<Procesor> rp("cpu.txt");
    assert(rp.getAll().size()==5);
}