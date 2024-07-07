//
// Created by Daniel on 07.06.2022.
//

#include "tests.h"

void Test::testService() {
    remove(testfile.c_str());
    std::filesystem::copy_file(file, testfile);
    Repository r(testfile);
    Service s(r);
    assert(s.getAll().size() == 10);
    assert(s.getAllSorted()[0].getRank() == 2);

    s.modify(1, "Cantare2", 2);
    assert(r.getAll()[0].getRank()==2);
    assert(r.getAll()[0].getTitlu()=="Cantare2");
    try{s.sterge(4); assert(false);}
    catch(ServiceException&){}
    try{s.sterge(11); assert(false); }
    catch(ServiceException&){}
    try{s.modify(11, "", 8); assert(false); }
    catch(ServiceException&){}
    assert(s.nrMelodiiRank(2)==2);
    s.sterge(1);
    assert(s.getAll().size() == 9);
    assert(s.nrMelodiiRank(2)==1);
}

void Test::testDomainRepo() {
    remove(testfile.c_str());
    std::filesystem::copy_file(file, testfile);
    Repository r(testfile);
    assert(r.getAll().size()==10);

    Melodie m(6, "Summersong2020", "Elektromania", 10);
    Melodie m2(m);
    m2.setRank(7);
    m2.setTitlu("SummerSong2020");
    assert(r.getAll()[5] == m);

    assert(r.nrMeldii(10) == 3);
    assert(r.nrMeldii(7) == 1);
    r.modify(m, m2);
    assert(r.getAll()[5].getRank() == 7);
    assert(r.nrMeldii(7) == 2);

    r.sterge(m2);
    assert(r.getAll().size() == 9);
    assert(r.nrMeldii(7) == 1);
}

void Test::testDomain() {
    Melodie m(1, "Summersong2020", "Elektromania", 10);
    assert(m.getId() == 1);
    assert(m.getTitlu() == "Summersong2020");
    assert(m.getArtist() == "Elektromania");
    assert(m.getRank() == 10);
    assert(m==m);
    Melodie m2(m);
    m2.setRank(7);
    m2.setTitlu("SummerSong2020");
    assert(m2.getRank() == 7);
    assert(m2.getTitlu() == "SummerSong2020");
    assert(m2 < m);
    m2 = Melodie(90, "", "", 9);
    assert(m2 != m);
}

Test::Test() {
    testDomain();
    testDomainRepo();
    testService();
}



