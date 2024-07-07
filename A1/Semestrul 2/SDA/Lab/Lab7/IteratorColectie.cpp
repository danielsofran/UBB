#include "IteratorColectie.h"
#include "Colectie.h"

IteratorColectie::IteratorColectie(const Colectie& c): col(c) {
    int nod = col.rad;
    while (nod != NIL){
        s.push(nod);
        nod = col.noduri[nod].st;
    }
}

TElem IteratorColectie::element() const{
	/* de adaugat */
    int nod = s.top();
	return col.noduri[nod].val;
}

bool IteratorColectie::valid() const {
	/* de adaugat */
	return !s.empty();
}

void IteratorColectie::urmator() {
    if(!valid()) throw ;
	int nod = s.top();
    if(frecv < col.noduri[nod].frecv) {
        frecv++;
        return;
    }
    s.pop();
    frecv = 1;
    if(col.noduri[nod].dr != NIL) {
        nod = col.noduri[nod].dr;
        while (nod != NIL)
            s.push(nod),
            nod = col.noduri[nod].st;
    }
}

void IteratorColectie::prim() {
    s = stack<int>();
    if (col.rad != NIL && !col.noduri[col.rad].isNULL()){
        s.push(col.rad);
        frecv = 1;
    }
}
