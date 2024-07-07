//
// Created by Daniel on 07.06.2022.
//

#ifndef MODEL2_TESTS_H
#define MODEL2_TESTS_H

#include "cassert"
#include "filesystem"
#include "service.h"

class Test{
private:
    string testfile="repotest.txt";
    string file="repo.txt";

    void testDomain(); // test la Melodie
    void testDomainRepo(); // Test la Melodie si Repository
    void testService(); // Test la Service

public:
    Test(); // apelez toate testele
};

#endif //MODEL2_TESTS_H
