#pragma once
#ifndef SERVICE_H_
#define SERVICE_H_
#include "domain.h"

// comanda fisierului repository sa creeze o lista goala(in care vor fi stocate pe viitor obiectele) 
void setup();

// functie care creeaza un jucator(nume, prenume, scores) si il adauga in vectorul static v
void makeAdd(myList* v, char* nume, char* prenume, int* scores);

// functie care creeaza un jucator(nume, prenume, scores) si il sterge din vectorul static v
int makeDelete(myList* v, char* nume, char* prenume, int* scores);

/*   functie care creeaza 2 jucatori A si B pe baza atributelor
*    (nume1, prenume1, scores1) si (nume2, prenume2, scores2)
*    Daca A se gaseste in vectorul static v, este inlocuit in vector cu B si returneaza 1
*    Altfel returneaza 0
*/
int makeUpdate(myList* v, char* nume1, char* prenume1, int* scores1, char* nume2, char* prenume2, int* scores2);

/*   functie care filtreaza jucatorii din lista dupa prima litera din nume
*    returneaza o lista cu jucatorii al caror nume incepe cu litera data
*/
myList makeFilter(myList* v, char litera);

#endif




