/*   HEADER pt implementarea / stocarea jucatorilor
*
*/
#pragma once
#ifndef DOMAIN_H_
#define DOMAIN_H_

#include "player.h"

typedef struct {
	int len;
	int cp;
	Player* elems;
} myList;


//functie care creeaza o lista goala cu elemente de tip TElem 
myList createEmptyList();

//functie care adauga jucatorul player in lista list
void addPlayer(myList* list, Player* player);

//functie care sterge jucatorul player din lista list
void deletePlayer(myList* list, Player* player);

// functie care tipareste pozitia( sau -1, daca nu exista) a jucatorului player in lista list
int findPlayer(myList* list, Player* player);

//functie care elibereaza memoria alocata unei liste
void destroyList(myList* list);

//functie de sortare a unei liste pe baza unei chei(key) in ordine crescatoare/descrescatoare(reverse)
void sortList(myList* list, int (*key) (Player, Player), int reverse);


#endif
