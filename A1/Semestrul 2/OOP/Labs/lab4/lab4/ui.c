#pragma once
#include<stdio.h> 
#include<string.h>
#include "service.h"
#include "domain.h"
#include "ui.h"
#include "player.h"
#pragma warning(disable:4996)
#define oo 100000

void printMenu() {
    printf("Apasati 1 pentru a adauga un nou concurent\n");
    printf("Apasati 2 pentru a sterge un concurent\n");
    printf("Apasati 3 pentru a actualiza datele un concurent\n");
    printf("Apasati 4 pentru a filtra concurentii\n");
    printf("Apasati 5 pentru a sorta concurentii\n");
    printf("Apasati 6 pentru a tipari lista de jucatori\n");
}

void readPlayer(char nume[], char prenume[], int scores[]) {
    printf("Nume: ");
    fgets(nume, sizeof(nume), stdin);
    printf("Prenume: ");
    fgets(prenume, sizeof(prenume), stdin);
    printf("Scoruri: ");
    for (int i = 0; i < 10; i++)
        scanf("%d", &scores[i]);
}

int validate(char nume[], char prenume[], int scores[]) {
    char err[100];
    err[0] = '\0';
    if (strcmp(nume, "\n") == 0)  strcat(err, "Nume inexistent!; ");
    if (strcmp(prenume, "\n") == 0) strcat(err, "Prenume inexistent! ");
    for (int i = 0; i < 10; i++)
        if (scores[i] < 0) {
            strcat(err, "Scor negativ!");
            break;
        }

    strcat(err, "\n");
    if (strcmp(err, "\n") != 0) {
        printf(err);
        return 0;
    }
    return 1;
}

void printPlayer(myList v) {
    int n = v.len;
    if (n == 0) {
        printf("Lista nu contine niciun jucator!\n");
        return;
    }

    for (int i = 0; i < n; i++) {
        printf("Jucatorul %d:", i + 1);
        printf("\n%s%s", v.elems[i].nume, v.elems[i].prenume);
        for (int j = 0; j < 10; j++)
            printf("%d ", v.elems[i].scores[j]);
        printf("\n");
    }
}

void runConsole() {

    printMenu();
    myList v = createEmptyList();
    char cmd[200];
    fgets(cmd, sizeof(cmd), stdin);
    while (strcmp(cmd, "exit\n") != 0) {
        char nume[20], prenume[20];
        int scores[10];

        if (strcmp(cmd, "1\n") == 0) {
            readPlayer(nume, prenume, scores);
            if (validate(nume, prenume, scores) == 1)
                makeAdd(&v, nume, prenume, scores);
        }

        else if (strcmp(cmd, "2\n") == 0) {
            readPlayer(nume, prenume, scores);
            if (validate(nume, prenume, scores) == 1) {
                int res = makeDelete(&v, nume, prenume, scores);
                if (res == 0) printf("Jucator inexistent");
            }
        }

        else if (strcmp(cmd, "3\n") == 0) {
            char nume2[20], prenume2[20];
            int scores2[20];
            readPlayer(nume, prenume, scores);
            getchar();
            readPlayer(nume2, prenume2, scores2);
            if (validate(nume, prenume, scores) == 1 && validate(nume2, prenume2, scores2)) {
                int res = makeUpdate(&v, nume, prenume, scores, nume2, prenume2, scores2);
                if (res == 0) printf("Primul jucator este inexistent");
            }
        }

        else if (strcmp(cmd, "4\n") == 0) {
            char litera;
            printf("Introduceti litera dupa care se filtreaza: ");
            scanf("%c", &litera);
            myList ans = makeFilter(&v, litera);
            printPlayer(ans);
        }

        else if (strcmp(cmd, "5\n") == 0) {
            int x, y;
            printf("Tastati 0 daca doriti sa sortati dupa nume, 1 dupa scor\n");
            scanf("%d", &x);
            printf("Tastati 0 daca doriti sa sortati crescator, 1 descrescator\n");
            scanf("%d", &y);
            int (*key)(Player, Player) = &greaterName;
            if (x == 1) key = &greaterScore;
            sortList(&v, key, y);
        }

        else if (strcmp(cmd, "6\n") == 0)
            printPlayer(v);

        else if (strcmp(cmd, "\n") != 0)
            printf("Comanda invalida\n");

        fgets(cmd, sizeof(cmd), stdin);
    }

    destroyList(&v);
}