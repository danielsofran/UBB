#pragma once
#ifndef UI_H_
#define UI_H_
//functie care tipareste meniul aplicatiei 
void printMenu();

/* Bucla principala a aplicatiei
 * functie de interactiune cu utilizatorul
 */
void runConsole();


// functie care citeste datele unui jucator
void readPlayer(char nume[], char prenume[], int scores[]);


/* functie care valideaza datele unui jucator
   returneaza 1 daca este valid
   0 altfel */
int validate(char nume[], char prenume[], int scores[]);

#endif



