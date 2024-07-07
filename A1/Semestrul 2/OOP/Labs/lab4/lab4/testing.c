#pragma once
#include <stdio.h>
#include <string.h>
#include "player.h" 
#include "domain.h"
#include "service.h"
#include "testing.h"
#include <assert.h>


/// PLAYER
void testCreatePlayer() {
	int scores[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	Player player = createPlayer("Ion", "John", scores);
	assert(strcmp(player.nume, "Ion") == 0);
	assert(strcmp(player.prenume, "John") == 0);
	for (int i = 0; i < 10; i++)
		assert(player.scores[i] == scores[i]);
	destroyPlayer(&player);
}

void testEquals() {
	int scores[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	Player A = createPlayer("Ion", "John", scores);
	Player B = createPlayer("Ion", "John", scores);
	Player C = createPlayer("Io", "John", scores);
	Player D = createPlayer("Ion", "Joe", scores);
	Player E = createPlayer("Ion", "John", scores);
	E.scores[9] = 9;
	assert(equals(A, B) == 1);
	assert(equals(A, C) == 0);
	assert(equals(A, D) == 0);
	assert(equals(A, E) == 0);
	destroyPlayer(&A);
	destroyPlayer(&B);
	destroyPlayer(&C);
	destroyPlayer(&D);
	destroyPlayer(&E);

}

void testUpdatePlayer() {
	int scores[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int scores2[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 9 };
	Player A = createPlayer("Ion", "John", scores);
	Player B = createPlayer("Andrei", "Pavel", scores2);
	updatePlayer(&A, &B);
	assert(equals(A, B) == 1);
}
///end PLAYER 



/// DOMAIN
void testCreateEmptyList() {
	myList v = createEmptyList();
	assert(v.len == 0);
	int scores[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	Player A = createPlayer("Ion", "Ion", scores);
	addPlayer(&v, A);
	destroyList(&v);
}

void testAddPlayer() {
	myList v = createEmptyList();
	int scores[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	Player A = createPlayer("Ion", "John", scores);
	Player B = createPlayer("Andrei", "Andrei", scores);
	addPlayer(&v, A);
	addPlayer(&v, B);
	assert(equals(v.elems[0], A) == 1);
	assert(equals(v.elems[1], B) == 1);
	assert(v.len == 2);
	destroyList(&v);
}

void testDeletePlayer() {
	myList v = createEmptyList();
	int scores[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	Player A = createPlayer("Ion", "John", scores);
	Player B = createPlayer("Andrei", "Andrei", scores);
	Player C = createPlayer("Dan", "Petrescu", scores);
	addPlayer(&v, A);
	addPlayer(&v, B);
	addPlayer(&v, C);
	deletePlayer(&v, B);
	assert(equals(v.elems[0], A) == 1);
	assert(equals(v.elems[1], C) == 1);
	assert(v.len == 2);
	destroyList(&v);
}

void testFindPlayer() {
	myList v = createEmptyList();
	int scores[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	Player A = createPlayer("Ion", "John", scores);
	Player B = createPlayer("Andrei", "Andrei", scores);
	Player C = createPlayer("Dan", "Petrescu", scores);
	addPlayer(&v, A);
	addPlayer(&v, B);
	findPlayer(&v, A);
	assert(findPlayer(&v, A) == 0);
	assert(findPlayer(&v, B) == 1);
	assert(findPlayer(&v, C) == -1);
	destroyList(&v);
	destroyPlayer(&C);
}

void testGreaterAndCompare() {
	int scores[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	Player A = createPlayer("Andrei", "Pavel", scores);
	Player B = createPlayer("Bogdan", "Anton", scores);
	B.scores[9] = 9;
	assert(greaterScore(A, B) == 1);
	assert(greaterName(B, A) == 1);
	int (*key1) (Player, Player) = &greaterName;
	int (*key2) (Player, Player) = &greaterScore;
	assert(cmp(A, B, key1, 1));
	assert(cmp(B, A, key2, 1));
	assert(cmp(A, B, key2, 0));
	destroyPlayer(&A);
	destroyPlayer(&B);
}
/// end DOMAIN


/// SERVICE
void testMakeAdd() {
	myList v = createEmptyList();
	int scores[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	makeAdd(&v, "Ion", "John", scores);
	assert(v.len == 1);
	assert(strcmp(v.elems[0].nume, "Ion") == 0);
	assert(strcmp(v.elems[0].prenume, "John") == 0);

	for (int j = 0; j < 10; j++)
		assert(v.elems[0].scores[j] == scores[j]);
	destroyList(&v);
}

void testMakeDelete() {
	myList v = createEmptyList();
	int scores[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	Player A = createPlayer("Ion", "John", scores);
	Player B = createPlayer("ABc", "DEf", scores);

	addPlayer(&v, A);
	addPlayer(&v, B);
	int res = makeDelete(&v, "Ion", "Jon", scores);
	assert(res == 0);
	assert(v.len == 2);
	assert(equals(v.elems[0], A) == 1);
	assert(equals(v.elems[1], B) == 1);

	int res2 = makeDelete(&v, "Ion", "John", scores);
	assert(res2 == 1);
	assert(v.len == 1);
	assert(equals(v.elems[1], B) == 1);
	destroyList(&v);
}

void testMakeUpdate() {
	int scores[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int scores2[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	myList v = createEmptyList();
	Player A = createPlayer("Ion", "John", scores);
	Player B = createPlayer("Andrei", "Pavel", scores2);
	addPlayer(&v, A);
	int res = makeUpdate(&v, "Ion", "John", scores, "Andrei", "Pavel", scores2);
	assert(res == 1);
	assert(equals(v.elems[0], B) == 1);
	destroyList(&v);
	destroyPlayer(&A);
	destroyPlayer(&B);
}

void testSortList() {
	myList v = createEmptyList();
	int scores[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	Player A = createPlayer("zzz", "a", scores);
	Player B = createPlayer("yyy", "bb", scores);
	Player C = createPlayer("xxzx", "cc", scores);
	Player D = createPlayer("qqqq", "ded", scores);
	D.scores[9] = 7;
	C.scores[9] = 8;
	B.scores[9] = 9;

	addPlayer(&v, A);
	addPlayer(&v, B);
	addPlayer(&v, C);
	addPlayer(&v, D);

	int (*key1) (Player, Player) = &greaterName;
	int (*key2) (Player, Player) = &greaterScore;
	sortList(&v, key1, 0);
	assert(equals(v.elems[0], D));
	assert(equals(v.elems[1], C));
	assert(equals(v.elems[2], B));
	assert(equals(v.elems[3], A));

	sortList(&v, key2, 1);
	assert(equals(v.elems[0], A));
	assert(equals(v.elems[1], B));
	assert(equals(v.elems[2], C));
	assert(equals(v.elems[3], D));
	destroyList(&v);
}

void testFilter() {
	myList v = createEmptyList();
	int scores[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	Player A = createPlayer("andrei", "a", scores);
	Player B = createPlayer("Andrei", "bb", scores);
	Player C = createPlayer("Bogdan", "cc", scores);
	Player D = createPlayer("adrian", "dd", scores);
	addPlayer(&v, A);
	addPlayer(&v, B);
	addPlayer(&v, C);
	addPlayer(&v, D);

	char litera = 'a';
	myList ans = makeFilter(&v, litera);
	assert(ans.len == 2);
	assert(equals(ans.elems[0], A));
	assert(equals(ans.elems[1], D));
	destroyList(&v);
}