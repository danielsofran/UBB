//
// Created by Daniel on 14.05.2022.
//

#ifndef UNTITLED_TESTS_H
#define UNTITLED_TESTS_H

#include "domain.h"
#include "exceptions.h"
#include "repository.h"
#include "service.h"

#include "cassert"
#include "sstream"

void test();
void testDomain();
void testException();
void testRepo(Repository&);
void testService(Service&);

#endif //UNTITLED_TESTS_H
