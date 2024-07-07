//
// Created by Daniel on 06.06.2022.
//

#ifndef MODEL1_TESTS_H
#define MODEL1_TESTS_H

#include "repo.h"
#include "service.h"
#include "filesystem"
#include "cassert"

class Test{
private:
    void testRepoDomain(){
        Repository r("repotest.txt");
        assert(r.getAll().size() == 10);
        assert(r.getAll()[0].getProgramatori().size()==2);
        assert(r.getAll()[0].getProgramatori()[0]=="Andrei");
        assert(r.getAll()[0].getDescriere()=="vr");
        assert(r.getAll()[0].getId()==1);
        Task t(13, "faina", {"Vasile"}, "closed");
        r.add(t);
        assert(r.getAll().size() == 11);
        assert(r.getAll().back() == t);
        assert(r.getAll().back().getStare() == "closed");
        try{r.add(t); assert(false); }
        catch(RepoException&){}
    }
    void testService(){
        Repository r("repotest.txt");
        Service s(r);
        assert(s.getAll().size() == 11);
        try{s.add(13, "faina", {"Vasile"}, "closed"); assert(false); }
        catch(MyException&){}
        try{s.add(12, "faina", {}, "closed"); assert(false); }
        catch(ServiceException&){}
        try{s.add(12, "faina", {}, "inprogress"); assert(false); }
        catch(ServiceException&){}
        try{s.add(13, "faina", {"Vasile"}, "closedt"); assert(false); }
        catch(MyException&){}
        try{s.add(13, "", {"Vasile"}, "closed"); assert(false); }
        catch(MyException&){}
        s.add(14, "faina", {"Vasile"}, "closed");
        assert(s.getAll().size() == 12);
        auto rez = s.filterNumePr("Dan");
        assert(rez.size() == 2);
        assert(rez[0].getId() == 2);
        rez = s.filterNumePr("Da");
        assert(rez.size() == 2);
        assert(rez[0].getId() == 2);
        rez = s.filterStare("open");
        assert(rez.size() == 1);
    }
public:
    Test(){
        remove("repotest.txt");
        std::filesystem::copy_file("repo.txt", "repotest.txt");
        testRepoDomain();
        testService();
    }
};

#endif //MODEL1_TESTS_H
