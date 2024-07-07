#include "tests.h"

void test()
{
    testDomain();
    testRepoService();
}

void testDomain(){
	Device e1{"qzptzlwfhnio", "yjempfxhogfe", 91, "wfzjt", 39};
	assert(e1.getTip() == "qzptzlwfhnio");
	assert(e1.getModel() == "yjempfxhogfe");
	assert(e1.getAn() == 91);
	assert(e1.getCuloare() == "wfzjt");
	assert(e1.getPret() == 39);
	Device e2{"dapofbhfqa", "lwugkug", 23, "slzsnatvafty", 24};
	assert(e1 != e2);
	e1 = e2;
	assert(e1 == e2);
	Device e3;
	assert(e1 != e3);
	Device e4(e1);
	assert(e4 == e1);
	stringstream ss;
	ss<<e1;
	ss.seekg(0);
	ss>>e3;
	assert(e1 == e3);
    string model = "yjemp fxhogfe";
    Device d("qzptzlwfhnio", model, 91, "wfzjt", 39);
    stringstream ss2;
    ss2<<d;
    ss2.seekg(0);
    ss2>>d;
    assert(d.getModel() == model);
}

void testRepoService(){
    Repository r("repo.txt");
    Service s(r);
    assert(s.getAll() == r.getAll());
    assert(s.getAll().size()==10);
    auto rez = s.sortModel();
    assert(rez[0].getModel() == "AsusZenBookz1");
    assert(s.getAll()[0].getModel() != "AsusZenBookz1");
    rez = s.sortPret();
    assert(rez[0].getPret() == 200);
}

