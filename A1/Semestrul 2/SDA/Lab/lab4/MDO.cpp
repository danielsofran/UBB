#include "IteratorMDO.h"
#include "MDO.h"
#include <iostream>
#include <vector>

#include <exception>
using namespace std;

MDO::MDO(Relatie r) {
    primLiber = 0;
    primOcupat = -1;
    capacity = 100;
    elements = vector<vector<TValoare>>(capacity);
    chei = vector<TCheie>(capacity);
    urm = vector<int>(capacity);
    rel = r;
    for(int i=0;i<capacity;++i);
}


void MDO::adauga(TCheie c, TValoare v) {
	/* de adaugat */
}

vector<TValoare> MDO::cauta(TCheie c) const {
	/* de adaugat */
	return vector<TValoare>  ();
}

bool MDO::sterge(TCheie c, TValoare v) {
	/* de adaugat */
	return false;
}

int MDO::dim() const {
	/* de adaugat */
	return 0;
}

bool MDO::vid() const {
	/* de adaugat */
	return true;
}

IteratorMDO MDO::iterator() const {
	return IteratorMDO(*this);
}

MDO::~MDO() {
	/* de adaugat */
}
