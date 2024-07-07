#pragma once

#include "domain.h"
#include "exceptions.h"
#include "repository.h"
#include "service.h"

#include "cassert"
#include "sstream"

using std::stringstream;

void test(); // ruleaza toate testele
void testDomain(); // testeaza constructori, getteri, operatori
void testException(); // test ierarhie de exceptii
void testRepo(); // test repository from file
void testService(); // test service

