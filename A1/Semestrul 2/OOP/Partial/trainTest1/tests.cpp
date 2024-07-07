//
// Created by Daniel on 14.05.2022.
//
#include "tests.h"


void testDomain()
{
    Masina m("122", "Audi", "Pasat", "lung");
    assert(m.getNrInmat() == "122");
    assert(m.getProd() == "Audi");
    assert(m.getModel() == "Pasat");
    assert(m.getTip() == "lung");
    std::ostringstream sout;
    sout<<m;
    std::istringstream sin(sout.str());
    Masina m2;
    sin>>m2;
    assert(m == m2);
    assert(!(m != m2));
    m2.setNrInmat("122");
    m2.setProd("Audi");
    m2.setModel("Pasat");
    m2.setTip("lung");
    m = Masina(m2);
    assert(m == m2);
}

void testException(){
    try{
        throw MyException("eroare");
    }
    catch(MyException& ex) {
        std::ostringstream sout;
        sout<<ex;
        assert(sout.str() == "eroare");
    }
    try{
        throw RepoException("eroare");
    }
    catch(MyException& ex) {
        std::ostringstream sout;
        sout<<ex;
        assert(sout.str() == "eroare");
    }
}

void testRepo(Repository& r)
{
    Masina m("122", "Audi", "Pasat", "lung");
    r.add(m);
    assert(*r.begin() == m);
    assert(*(r.end()-1) == m);
    try{r.add(m);
        assert(false);
    } catch (RepoException& re){}
    Masina m1("123", "Audi", "Pasat", "lung");
    r.add(m1);
    assert(r.size() == 2);
    assert(r.find(m1) == true);
    Masina m3("125", "Audi", "Pasat", "papuc");
    assert(r.find(m3) == false);
    r.modify(m, m1);
    r.remove(m1);
    int sz=0;
    for(auto& elem : r)
    {
        ++sz;
    }
    assert(sz==1);
    r.remove(*r.begin());
}

void testService(Service& s){
    Masina m("122", "Audi", "Pasat", "lung");
    s.add("122", "Audi", "Pasat", "lung");
    s.remove("122", "Audi", "Pasat", "lung");
    try{ s.find("122");
        assert(false);}
    catch(MyException& me){}
    s.add("122", "Audi", "Pasat", "lung");
    s.modify("122", "Audi", "Pasat", "lung",
             "123", "Audi", "Pasat", "lung");
    m.setNrInmat("123");
    assert(*s.getAll().begin() == m);
}

void test()
{
    std::ofstream fout("testrepo.txt"); fout.close();
    testDomain();
    testException();
    Repository r;
    FileRepository fr("testrepo.txt");
    testRepo(r);
    //testRepo(fr);
    Service s(r);
    testService(s);
    //Service sf(fr);
    //testService(sf);
    //assert(fr.size() == 1);
}