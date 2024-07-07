#include <stdio.h>
#include <stdlib.h> 
#include <string.h>
#include "player.h"
#pragma warning(disable:4996)


Player* createPlayer(char* nume, char* prenume, int* scores) {
	int n = (int) strlen(nume) + 1;
	int m = (int) strlen(prenume) + 1;
	int k = 20;
    Player* player = malloc(sizeof(Player));
	player->nume = malloc(n * sizeof(char));
	player->prenume = malloc(m * sizeof(char));
	player->scores = malloc(k * sizeof(int));

	strcpy(player->nume, nume);
	strcpy(player->prenume, prenume);
	for (int i = 0; i < 10; i++)
	    player->scores[i] = scores[i];
	return player;
}

void* copyPlayer(void* player_)
{
    Player* player = (Player*)player_;
    Player* rez = createPlayer(
        player->nume,
        player->prenume,
        player->scores
    );
    return rez;
}

int equals(Player A, Player B) {
	if (strcmp(A.nume, B.nume) != 0) return 0;
	if (strcmp(A.prenume, B.prenume) != 0) return 0;
	for (int i = 0; i < 10; i++)
		if (A.scores[i] != B.scores[i]) return 0;
	return 1;
}

void updatePlayer(Player* A, Player* B) {
    free(A->nume);
    free(A->prenume);
    A->nume = malloc(strlen(B->nume)+1);
    A->prenume = malloc(strlen(B->prenume)+1);
	strcpy(A->nume, B->nume);
	strcpy(A->prenume, B->prenume);
	for (int i = 0; i < 10; i++)
		A->scores[i] = B->scores[i];
}

void destroyPlayer(Player* player) {
    if(player==NULL) return;
	free(player->nume);
	free(player->prenume);
	free(player->scores);
    free(player);
    player = NULL;
}

int greaterPrenume(Player A, Player B){
    if (strcmp(A.prenume, B.prenume) > 0) return 1;
    return 0;
}

int greaterName(Player A, Player B) {
	if (strcmp(A.nume, B.nume) > 0) return 1;
	return 0;
}

int greaterScore(Player A, Player B) {
	int score1 = 0, score2 = 0;
	for (int i = 0; i < 10; i++) {
		score1 += A.scores[i];
		score2 += B.scores[i];
	}
	if (score1 > score2) return 1;
	return 0;
}

int cmp(Player A, Player B, int (*key) (Player, Player), int reverse) {
	if (reverse == 1) return ~key(A, B);
	return key(A, B);
}