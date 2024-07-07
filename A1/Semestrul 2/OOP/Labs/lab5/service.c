#include <stdio.h>
#include <stdlib.h>
#include "player.h"
#include "repository.h"
#include "service.h"

void makeAdd(myList* v, char* nume, char* prenume, int* scores) {
	Player* player = createPlayer(nume, prenume, scores);
	addElem(v, player);
}

int makeDelete(myList* v, char* nume, char* prenume, int* scores) {
	Player* player = createPlayer(nume, prenume, scores);
	if (findPlayer(v, player) == -1) {
        destroyPlayer(player);
        return 0;
    }
	deletePlayer(v, player);
    destroyPlayer(player);
	return 1;
}

int makeUpdate(myList* v, char* nume1, char* prenume1, int* scores1, char* nume2, char* prenume2, int* scores2) {
	Player* A = createPlayer(nume1, prenume1, scores1);
	Player* B = createPlayer(nume2, prenume2, scores2);
	int pos = findPlayer(v, A);
	if (pos == -1) return 0;
	v->elems[pos] = B;
    destroyPlayer(A);
	return 1;
}

myList makeFilter(myList* v, char litera) {
	myList ans = createEmptyList();
	for (int i = 0; i < v -> len; i++)
		if (((Player*)v->elems[i])->nume[0] == litera)
            addElem(&ans, v->elems[i]);
	return ans;
}

myList makeFilter2(myList* v, int pb, int scor)
{
    myList ans = createEmptyList();
    for (int i = 0; i < v -> len; i++)
    {
        Player* p = v->elems[i];
        if(p->scores[pb] > scor)
            addElem(&ans, v->elems[i]);
    }
    return ans;
}