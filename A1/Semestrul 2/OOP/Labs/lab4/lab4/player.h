#pragma once
#ifndef PLAYER_H_
#define PLAYER_H_

typedef struct {
	char* nume;
	char* prenume;
	int* scores;
} Player;


//functie care creeaza un jucator pe baza unui nume, prenume si scor
Player createPlayer(char* nume, char* prenume, int* scores);

//functie care verifica daca 2 jucatori sunt identici 
int equals(Player A, Player B);

//functie care simuleaza operatorul de atribuire pentru entitati de tip jucator
void updatePlayer(Player* A, Player* B);

//functie care elibereaza memoria alocata jucatorului player
void destroyPlayer(Player* player);


//functie care returneaza 1 daca numele jucatorului A este mai mic lexicografic decat numele lui B
//altfel returneaza 0
int greaterName(Player A, Player B);

//functie care returneaza 1 daca scorul total al jucatorului A este mai mic lexicografic decat scorul total al lui B
//altfel returneaza 0 
int greaterScore(Player A, Player B);

//functie de comparare a 2 jucatori pe baza unui criteriu(reprezentat de functia key) 
//returneaza 1 daca A < B, 0 altfel 
int cmp(Player A, Player B, int (*key) (Player, Player), int reverse);


#endif
