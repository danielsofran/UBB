#include "tests.h"


void test()
{
    std::ofstream fout("testrepo.txt"); fout.close();
    testDomain();
    testException();
    testRepo();
    testService();
    testService2();
}

void testDomain(){
	Carte e1{"zzbcfkxvjhac", "ofb", "zvqopmp", 64};
	assert(e1.getTitlu() == "zzbcfkxvjhac");
	assert(e1.getAutor() == "ofb");
	assert(e1.getGen() == "zvqopmp");
	assert(e1.getAnap() == 64);
	Carte e2{"tvcnllxt", "pofbqx", "pep", 60};
	assert(e1 != e2);
	e1 = e2;
	assert(e1 == e2);
	Carte e3;
	assert(e1 != e3);
	Carte e4(e1);
	assert(e4 == e1);
	stringstream ss;
	ss<<e1;
	ss.seekg(0);
	ss>>e3;
	assert(e1 == e3);
}

void testException(){
    try{
        throw MyException("eroare");
    }
    catch(MyException& ex) {
        std::ostringstream sout;
        sout<<ex;
        assert(sout.str() == "eroare");
        assert(ex.getMessage() == "eroare");
    }
    try{
        throw ServiceException("eroare");
    }
    catch(MyException& ex) {
        std::ostringstream sout;
        assert(ex.getMessage() == "eroare");
    }
}

void testRepo(){
	Carte e1{"zzbcfkxvjhac", "ofb", "zvqopmp", 64};
	Carte e2{"tvcnllxt", "pofbqx", "pep", 60};
	Carte e3{"bsj", "tahkqsanju", "uesfntw", 46};
	Carte e4;
	FileRepository r("testrepo.txt");
    r.add(e1);
    r.add(e2);
    r.add(e3);
    assert(*r.begin() == e1);
    assert(*(r.end()-1) == e3);
    try{ r.add(e1); assert(false); }
    catch(RepoException&) {}
    assert(r.size() == 3);
    assert(r.find(e2) == true);
    assert(r.find(e4) == false);
    r.remove(e2);
    assert(r.size() == 2);
    assert(r.find(e2) == false);
    try{ r.remove(e2); assert(false); }
    catch(RepoException&) {}
    r.modify(e1, e2);
    assert(r.size() == 2);
    assert(r.find(e1) == false);
    assert(r.find(e2) == true);
    try{ r.modify(e1, e4); assert(false); }
    catch(RepoException&) {}
	Carte gasit;
	gasit = r.find([](const Carte& item){ return item.getTitlu() == "tvcnllxt"; });
	assert(gasit == e2);
	try{
		gasit = r.find([](const Carte& item){ return item.getTitlu() == "zzbcfkxvjhac"; });
		assert(false);
	}
	catch(RepoException&) {}
	r.remove(e2);
	r.remove(e3);
	assert(r.size() == 0);
    r.add(e1);
    r.add(e2);
    r.add(e3);
    r.sort([](Carte c1, Carte c2){ return c1.getTitlu() < c2.getTitlu(); });
    assert(*r.begin() == e3);
    assert(*(r.begin()+1) == e2);
    assert(*(r.begin()+2) == e1);
    r.remove(e1);
    r.remove(e2);
    r.remove(e3);
}

void testService(){
    FileRepository r("testrepo.txt");
    Service s(r);
    try{ s.undo();
        assert(false);
    }
    catch(ServiceException&){}
	s.add("zzbcfkxvjhac", "ofb", "zvqopmp", 64);
	assert(s.contains("zzbcfkxvjhac", "ofb", "zvqopmp", 64) == true);
	s.modify("zzbcfkxvjhac", "ofb", "zvqopmp", 64, "tvcnllxt", "pofbqx", "pep", 60);
	try{ 
		s.add("tvcnllxt", "pofbqx", "pep", 60);
		assert(false);
	} catch(ServiceException&) {}
	s.remove("tvcnllxt", "pofbqx", "pep", 60);
	try{ 
		s.remove("tvcnllxt", "pofbqx", "pep", 60);
		assert(false);
	} catch(ServiceException&) {}
	assert(s.contains("tvcnllxt", "pofbqx", "pep", 60) == false);
	try{ 
		s.modify("zzbcfkxvjhac", "ofb", "zvqopmp", 64, "tvcnllxt", "pofbqx", "pep", 60);
		assert(false);
	} catch(ServiceException&) {}
	s.add("tvcnllxt", "pofbqx", "pep", 60);
	Carte elem{"tvcnllxt", "pofbqx", "pep", 60};
	assert(*s.getAll().begin() == elem);
    s.undo();
    assert(!s.contains("tvcnllxt", "pofbqx", "pep", 60));
    s.add("zzbcfkxvjhac", "ofb", "zvqopmp", 64);
    s.modify("zzbcfkxvjhac", "ofb", "zvqopmp", 64, "tvcnllxt", "pofbqx", "pep", 60);
    s.undo();
    assert(!s.contains("tvcnllxt", "pofbqx", "pep", 60));
    s.remove("zzbcfkxvjhac", "ofb", "zvqopmp", 64);
    s.undo();
    assert(s.contains("zzbcfkxvjhac", "ofb", "zvqopmp", 64));
    s.undo();
}

void testService2(){
    Carte e1{"zzbcfkxvjhac", "ofb", "zvqopmp", 64};
    Carte e2{"tvcnllxt", "pofbqx", "pep", 60};
    Carte e3{"bsj", "tahkqsanju", "uesfntw", 64};
    FileRepository r("testrepo.txt");
    r.add(e1);
    r.add(e2);
    r.add(e3);
    Service s(r);
    s.sortAutor();
    assert(*(r.begin()+0) == e1);
    assert(*(r.begin()+1) == e2);
    assert(*(r.begin()+2) == e3);
    s.sortTitlu();
    assert(*(r.begin()+0) == e3);
    assert(*(r.begin()+1) == e2);
    assert(*(r.begin()+2) == e1);
    s.sortAnapGen();
    assert(*(r.begin()+0) == e2);
    assert(*(r.begin()+1) == e3);
    assert(*(r.begin()+2) == e1);
    auto rez = s.filtruAnAp("64");
    assert(rez[0] == e3);
    assert(rez[1] == e1);
    rez = s.filtruTitlu("zzbcfkxvjhac");
    assert(rez[0] == e1);
    rez = s.filtruTitlu("nimic");
    assert(rez.empty());
}
