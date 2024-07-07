//
// Created by Daniel on 06.06.2022.
//

#include "tests.h"

void test(){
    testDomainRepo();
    testService();
}

void testDomainRepo()
{
    remove("testrepo.txt");
    std::filesystem::copy_file("repo.txt", "testrepo.txt");
    Repository r("testrepo.txt");
    assert(r.getAll().size() == 10);
    Produs p(12, "a", "b", 12.3);
    r.add(p);
    assert(r.getAll().size() == 11);
    assert(r.getAll()[0].getTip() == "electronic");
    assert(r.getAll()[0].getNume() == "masina");
    assert(r.getAll()[0].getId() == 1);
    assert(r.getAll()[0].getPret() -30.2 < 1e-6);

    assert(r.getAll().back() == p);
    assert(r.getAll()[0] != p);

    Produs p1(p);
    assert(p1 == p);

    try{r.add(p); assert(false);}
    catch(RepoException&){}
}

void testService(){
    Repository r("testrepo.txt");
    remove("testrepo.txt");
    std::filesystem::copy_file("repo.txt", "testrepo.txt");
    Service s(r);
    s.add("12", "a", "b", "12.0");
    assert(s.getAll().size() == 11);
    auto rez = s.filterPret(10);
    assert(rez.size() == 4);
    auto f = s.tipuri();
    assert(f.size() == 5);
    assert(f["haine"] == 3);
    try{s.add("12", "a", "b", "12.0"); assert(false); }
    catch(MyException&){}
    try{
        s.add("", "a", "b", "12.0");
        assert(false);
    }
    catch(MyException&){}
    try{s.add("", "a", "b", "12d.0"); assert(false); }
    catch(MyException&){}
}