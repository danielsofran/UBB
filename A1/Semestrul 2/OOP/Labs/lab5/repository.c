#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "player.h"
#include "repository.h"


myList createEmptyList() {
	myList v;
	v.len = 0;
	v.cp = 2;
	v.elems = malloc(2 * sizeof(void*));
	return v;
}

myList* copyList(myList* list, void* (*copy)(void*)) {
    myList* v = malloc(sizeof(myList));
    v->len = list->len;
    v->cp = list->cp;
    v->elems = malloc(v->cp * sizeof(void*));
    for(int i=0;i<list->len;++i)
        v->elems[i] = copy(list->elems[i]);
    return v;
}

void resize(myList* v) {
	int newCapacity = 2 * v->cp;
	void** newElems = malloc(newCapacity * sizeof(void*));
	for (int i = 0; i < v->len; i++)
		newElems[i] = v->elems[i];
	free(v->elems);
	v->elems = newElems;
	v->cp = newCapacity;
}

void addElem(myList* list, void* player) {
	if (list->len == list->cp) resize(list);
	list->elems[list->len] = player;
	list->len++;
}

void* topElem(myList* list){
    return list->elems[list->len-1];
}

void popElem(myList* list)
{
    if(list->len > 0)
    {
        list->len--;
    }
}

int findPlayer(myList* list, Player* player) {

	for (int i = 0; i < list->len; i++)
		if (equals(*player, *(Player*)(list->elems[i])))
            return i;
	return -1;
}

void deletePlayer(myList* list, Player* player) {
	int pos = findPlayer(list, player);
	destroyPlayer(list -> elems[pos]);
	for (int i = pos; i < list->len - 1; i++)
		list->elems[i] = list->elems[i + 1];
	list->len--;
}

void destroyList(myList* list) {
	if (list -> len != 0) 
		for (int i = 0; i < list -> len; i++)
            if(list->elems[i] != NULL)
			    destroyPlayer(list -> elems[i]);
    if(list->elems != NULL)
	free(list -> elems);
}

void sortList(myList* list, int (*key) (Player, Player), int reverse) {
	int n = list->len;  
	for (int i = 0; i < n - 1; i++) 
		for (int j = i + 1; j < n; j++) {
			Player A = *(Player*)list -> elems[i], B = *(Player*)list -> elems[j];
			if (cmp(A, B, key, reverse)) {
				Player* aux = list -> elems[i];
				list -> elems[i] = list -> elems[j];
				list -> elems[j] = aux;
			}
	}
}


