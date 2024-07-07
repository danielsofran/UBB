#include "tests.h"


void test()
{
    std::ofstream fout("testrepo.txt"); fout.close();
    testDomain();
    testException();
    testRepo();
    testService();
}

void testDomain(){
	Produs e1{"grlxuonmkqst", "gmkyyreoxinv", 0.744186, "shnxaaes"};
	assert(e1.getNume() == "grlxuonmkqst");
	assert(e1.getTip() == "gmkyyreoxinv");
	assert(e1.getPret() == 0.744186);
	assert(e1.getProducator() == "shnxaaes");
	Produs e2{"mhqxdcisfy", "nkp", 0.371429, "fgyjle"};
	assert(e1 != e2);
	e1 = e2;
	assert(e1 == e2);
	Produs e3;
	assert(e1 != e3);
	Produs e4(e1);
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
	Produs e1{"grlxuonmkqst", "gmkyyreoxinv", 0.744186, "shnxaaes"};
	Produs e2{"mhqxdcisfy", "nkp", 0.371429, "fgyjle"};
	Produs e3{"ofbtb", "hmijt", 0.038462, "nlzzpydad"};
	Produs e4;
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
	Produs gasit;
	gasit = r.find([](const Produs& item){ return item.getNume() == "mhqxdcisfy"; });
	assert(gasit == e2);
	try{
		gasit = r.find([](const Produs& item){ return item.getNume() == "grlxuonmkqst"; });
		assert(false);
	}
	catch(RepoException&) {}
	r.remove(e2);
	r.remove(e3);
	assert(r.size() == 0);
}

void testService(){
    FileRepository r("testrepo.txt");
    Service s(r);
	s.add("grlxuonmkqst", "gmkyyreoxinv", 0.744186, "shnxaaes");
	assert(s.contains("grlxuonmkqst", "gmkyyreoxinv", 0.744186, "shnxaaes") == true);
	s.modify("grlxuonmkqst", "gmkyyreoxinv", 0.744186, "shnxaaes", "mhqxdcisfy", "nkp", 0.371429, "fgyjle");
	try{ 
		s.add("mhqxdcisfy", "nkp", 0.371429, "fgyjle");
		assert(false);
	} catch(ServiceException&) {}
	s.remove("mhqxdcisfy", "nkp", 0.371429, "fgyjle");
	try{ 
		s.remove("mhqxdcisfy", "nkp", 0.371429, "fgyjle");
		assert(false);
	} catch(ServiceException&) {}
	assert(s.contains("mhqxdcisfy", "nkp", 0.371429, "fgyjle") == false);
	try{ 
		s.modify("grlxuonmkqst", "gmkyyreoxinv", 0.744186, "shnxaaes", "mhqxdcisfy", "nkp", 0.371429, "fgyjle");
		assert(false);
	} catch(ServiceException&) {}
	s.add("mhqxdcisfy", "nkp", 0.371429, "fgyjle");
	Produs elem{"mhqxdcisfy", "nkp", 0.371429, "fgyjle"};
	assert(*s.getAll().begin() == elem);
}
