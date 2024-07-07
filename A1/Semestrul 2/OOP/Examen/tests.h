//
// Created by Daniel on 08.06.2022.
//

#ifndef EXAMEN_TESTS_H
#define EXAMEN_TESTS_H

#include "cassert"
#include "filesystem"
#include "repository.h"
#include "service.h"

class Test{
private:
    string file = "repo.txt";
    string testfile = "repotest.txt";

    void testDomain(){ // teste la Joc
        Joc j(3, 3, "XXX-OXO-O-O", "O", "Terminat");
        assert(j.getTabla() == "XXX-OXO-O-O");
        assert(j.getId() == 3);
        assert(j.getStare() == "Terminat");
        assert(j.getJucator() == "O");
        Joc j2(j);
        j.setStare("In derulare");
        assert(j.getStare() == "In derulare");
        assert(!(j!=j));
    }

    void testRepo(){ // teste la repository
        remove(testfile.c_str());
        std::filesystem::copy_file(file, testfile);
        Repository r(testfile);
        assert(r.size() == 2);
        assert(r.getAll()[0].getId() == 1);
        assert(r.getAll()[1].getPoz(0, 0) == "-");
//        std::cout<<r.getAll()[1].getPoz(2, 2);
//        std::cout<<r.getAll()[0].getTabla();
        assert(r.getAll()[1].getPoz(2, 2) == "X");
        assert(r.getAll()[1].getStare() == "In derulare");
        Joc j(3, 3, "XXX-OXO-O-O", "O", "Terminat");
        r.add(j);
        assert(r.size() == 3);
        assert(r.getAll()[2].getPoz(1, 0) == "-");
        Joc j1(4, 3, "XXX-OXO-O-O", "O", "Terminat");
        r.modify(j, j1);
        assert(r.getAll()[2].getId() == 4);
    }

    void testService(){ // teste la service
        remove(testfile.c_str());
        std::filesystem::copy_file(file, testfile);
        Repository r(testfile);
        Service s(r);
        assert(s.getAll().size() == 2);
        s.add("3", "XXXOOO---", "X");
        assert(s.getAll().size() == 3);
        assert(s.getAll()[2].getId() == 3);
        assert(s.getAll()[2].getStare() == "Neinceput");
        s.modify(3, "4", "--XXOOOOXXXXOOOO", "O", "In derulare");
        assert(s.getAll()[2].getDim() == 4);
        s.modifyPoz(3, 0, 0, "X");
        s.modifyPoz(3, 0, 1, "O");
        assert(s.getAll()[2].getPoz(0, 0) == "X");
        try{
            s.add("6", "XXXOOO---", "X"); assert(false);
        }
        catch(ServiceException&){}
        try{
            s.add("3", "XXXOOO-X--", "X"); assert(false);
        }
        catch(ServiceException&){}
        try{
            s.add("we", "XXXOOO---", "X"); assert(false);
        }
        catch(ServiceException&){}
        try{
            s.add("3", "XXXOoO---", "X"); assert(false);
        }
        catch(ServiceException&){}
        try{
            s.add("3", "XXXOOO---", "-"); assert(false);
        }
        catch(ServiceException&){}
        try{
            s.modify(3, "3", "XXXOOO---", "X", "Inceput"); assert(false);
        }
        catch(ServiceException&){}
        try{
            s.modifyPoz(3, 3, 3, "X"); assert(false);
        }
        catch(ServiceException&){}
        try{
            s.modifyPoz(3, 3, 3, "u"); assert(false);
        }
        catch(ServiceException&){}
        s.find(1);
        try{
            s.find(0); assert(false);
        }
        catch(ServiceException&){}
    }

public:
    Test(){ // testeaza tot
        testDomain();
        testRepo();
        testService();
    }
};

#endif //EXAMEN_TESTS_H
