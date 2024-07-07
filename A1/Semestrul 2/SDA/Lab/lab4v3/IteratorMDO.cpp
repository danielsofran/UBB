#include "IteratorMDO.h"
#include "MDO.h"

IteratorMDO::IteratorMDO(const MDO& d) : dict(d){
    index = d.primOcupat;
    indexval = 1;
	/* de adaugat */
}

void IteratorMDO::prim(){
	/* de adaugat */
    index = dict.primOcupat;
    indexval=1;
}

void IteratorMDO::urmator(){
	/* de adaugat */
    if(!valid()) throw std::exception();
    if(indexval < dict.valori[index].dim())
        indexval++;
    else {
        index = dict.urm[index];
        indexval = 1;
    }
}

bool IteratorMDO::valid() const{
	if(index == 0)
	    return false;
    if(indexval > dict.valori[index].dim())
        return false;
    return true;
}

TElem IteratorMDO::element() const{
	/* de adaugat */
    if(!valid()) throw std::exception();
    TCheie cheie = dict.chei[index];
    auto it = dict.valori[index].prim();
    for(int i=1;i<indexval;++i)
        it.urmator();
	return TElem(cheie, it.element());
}


