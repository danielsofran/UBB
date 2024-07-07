#include "Colectie.h"
#include "IteratorColectie.h"
#include <exception>
#include <iostream>

using namespace std;

//functia care da <hashCode>-ul unui element
int hashCode(TElem e){
//ptr moment numarul e intreg
    return abs(e);
}

//functia de dispersie
int Colectie::d(TElem elem) const {
    //dispersia prin diviziune
    return hashCode(elem) % m;
}

// actualizare primLiber
void Colectie::actPrimLiber() // O(m)
{
    primLiber++;
    while (primLiber < m && e[primLiber] != NIL)
        primLiber++;
}

void Colectie::resize() { // theta(m)
    TElem *newe = new TElem [2*m];
    int* newurm = new int[2*m];
    int* newf = new int[2*m];
    for(int i=0;i<m;++i)
        newe[i] = e[i],
        newf[i] = frecvente[i],
        newurm[i] = urm[i];
    for(int i=m;i<2*m;++i)
        newe[i] = NIL,
        newurm[i] = -1,
        newf[i] = 0;
    primLiber = m-1;
    m*=2;
    delete[] e; delete[] frecvente; delete[] urm;
    e = newe; frecvente = newf; urm = newurm;
}

//Complexitate theta(m)-best case, theta(m^2)-worst case, theta(m^2)-average.Overall O(m^2)
void Colectie::rehash() {
    TElem* olde = new TElem[m];
    int* oldf = new TElem[m];
    memcpy(olde, e, sizeof(TElem)*m);
    memcpy(oldf, frecvente, sizeof(TElem)*m);
    for(int i=0;i<m;++i)
    {
        e[i] = NIL;
        frecvente[i] = 0;
        urm[i] = -1;
    }
    primLiber=0;
    //actPrimLiber();
    nrelem = 0;
    for(int index=0;index<m;++index)
        if(olde[index]!=NIL)
            for(int indexf=0;indexf<oldf[index];++indexf)
                adauga(olde[index]);
    delete[] olde; delete[] oldf;
}

Colectie::Colectie() { // theta(m)
    m = INIT_LENGTH; //initializam m cu o valoare predefinita
    //se initializeaza vectorii
    e = new TElem[m];
    frecvente = new int[m];
    urm = new int[m];
    for (int i = 0; i < m; i++) {
        e[i] = NIL; // consideram ca elementul e intreg
        frecvente[i]=0;
        urm[i] = -1;
    }
    //initializare primLIber
    primLiber = 0;
    nrelem = 0;
}

// theta(1) - in ipoteza dispersiei uniforme
// O(m*m)
void Colectie::adauga(TElem elem) {
    //locatia de dispersie a cheii
    int i=d(elem);
    if (e[i] == NIL)	// pozitia este libera
    {
        e[i] = elem;	// adaugam elementul
        frecvente[i] = 1;
        if (i == primLiber)
            actPrimLiber();	// actualizam primLiber, daca este nevoie
        nrelem++;
        return;
    }

    int j = -1;	// j va retine pozitia precedenta lui i, pentru a putea reface inlantuirea
    // daca pozitia nu este libera
    while (i != -1)	// iteram pana gasim capatul inlantuirii
    {
        if(e[i] == elem) // am gasit valoarea, cresc frecventa
        {
            frecvente[i]++;
            nrelem++;
            return;
        }
        j = i;
        i = urm[i];
    }

    if (primLiber >= m)     // redimensionam tabela, facem rehash
    {
        resize();
        rehash();
    }

    // adaugam elementul
    e[primLiber] = elem;
    frecvente[primLiber] = 1;
    urm[j] = primLiber;
    urm[primLiber] = -1;
    actPrimLiber();

    nrelem++;
}

int Colectie::getAnterior(int i) { // O(m)
    int k=0, j=-1;
    while(k<m && j==-1)
        if(urm[k] == i) j = k;
        else ++k;
    return j;
}

bool Colectie::sterge(TElem elem) { // O(m), dar e theta(1) amortizat in ipoteza dispersiei uniforme
	int i = d(elem), j = getAnterior(i);
    while(i!=-1 && e[i] != elem)
    {
        j = i;
        i = urm[i];
    }
    if(i==-1) return false;
    else if(frecvente[i]>1){
        frecvente[i]--;
        nrelem--;
        return true;
    }
    else{
        bool gata = false;
        while(!gata){
            int p = urm[i], pp = i;
            while( p != -1 && d(e[p]) != i)
                pp = p,
                p = urm[p];
            if(p==-1) gata = true;
            else{
                e[i] = e[p];
                frecvente[i] = frecvente[p];
                j = pp;
                i = p;
            }
        }
        if(j!=-1) urm[j] = urm[i];
        e[i] = NIL;
        urm[i] = -1;
        frecvente[i] = 0;
        if(primLiber > i)
            primLiber = i;
        this->nrelem--;
        return true;
    }
}

//Complexitate theta(1)-best case, theta(m)-worst case. Overall O(m)
bool Colectie::cauta(TElem elem) const {
    // cautam cheia in lista inlantuita incepand cu locatia de dispersie a cheii
    for(int i = d(elem); i != -1; i = urm[i])
        if(e[i] == elem)
            return true;
    return false;
}

int Colectie::nrAparitii(TElem elem) const { // theta(1) in ipoteza dispersiei uniforme
    for(int i = d(elem); i != -1; i = urm[i])
        if(e[i] == elem)
            return frecvente[i];
	return 0;
}


int Colectie::dim() const { // theta(1)
	return nrelem;
}


bool Colectie::vida() const { // theta(1)
	return nrelem==0;
}

IteratorColectie Colectie::iterator() const { // O(m)
	return IteratorColectie(*this);
}


Colectie::~Colectie() { // theta(1)
	delete[] e;
    delete[] urm;
    delete[] frecvente;
}

// BC, WC, AC, GC: theta(m)
int Colectie::elementeCuFrecventaData(int frecventa) const {
    int cnt = 0;
    for(int i=0;i<m;++i)
        if(e[i] != NIL && frecvente[i] == frecventa)
            ++cnt;
    return cnt;
}
