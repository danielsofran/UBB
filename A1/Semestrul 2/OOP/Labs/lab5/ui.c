#include<stdio.h>
#include<string.h>
#include "service.h"
#include "repository.h"
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
    printf("Apasati 7 pentru a efectua undo\n");
    printf("Apasati 8 pentru a filtra concurentii cu scorul unei probleme mai mare decat scorul dat\n");
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
        printf("\n%s%s", ((Player*)v.elems[i])->nume, ((Player*)v.elems[i])->prenume);
        for (int j = 0; j < 10; j++)
            printf("%d ", ((Player*)v.elems[i])->scores[j]);
        printf("\n");
    }
}

void runConsole() {
    printMenu();
    myList l = createEmptyList();
    myList* v = &l;
    myList undo = createEmptyList();
    addElem(&undo, copyList(v, copyPlayer));
    char cmd[200];
    fgets(cmd, sizeof(cmd), stdin);
    while (strcmp(cmd, "exit\n") != 0) {
        char nume[20], prenume[20];
        int scores[10];

        if (strcmp(cmd, "1\n") == 0) {
            readPlayer(nume, prenume, scores);
            if (validate(nume, prenume, scores) == 1) {
                makeAdd(v, nume, prenume, scores);
                addElem(&undo, copyList(v, copyPlayer));
                printf("Operatie efectuata cu succes!\n");
            }
        }

        else if (strcmp(cmd, "2\n") == 0) {
            readPlayer(nume, prenume, scores);
            if (validate(nume, prenume, scores) == 1) {
                int res = makeDelete(v, nume, prenume, scores);
                if (res == 0) printf("Jucator inexistent");
                else {
                    addElem(&undo, copyList(v, copyPlayer));
                    printf("Operatie efectuata cu succes!\n");
                }
            }
        }

        else if (strcmp(cmd, "3\n") == 0) {
            char nume2[20], prenume2[20];
            int scores2[20];
            readPlayer(nume, prenume, scores);
            getchar();
            readPlayer(nume2, prenume2, scores2);
            if (validate(nume, prenume, scores) == 1 && validate(nume2, prenume2, scores2)) {
                int res = makeUpdate(v, nume, prenume, scores, nume2, prenume2, scores2);
                if (res == 0) printf("Primul jucator este inexistent");
                else
                {
                    addElem(&undo, copyList(v, copyPlayer));
                    printf("Operatie efectuata cu succes!\n");
                }
            }
        }

        else if (strcmp(cmd, "4\n") == 0) {
            char litera;
            printf("Introduceti litera dupa care se filtreaza: ");
            scanf("%c", &litera);
            myList ans = makeFilter(v, litera);
            printPlayer(ans);
            printf("Operatie efectuata cu succes!\n");
        }

        else if (strcmp(cmd, "5\n") == 0) {
            int x, y;
            printf("Tastati 0 daca doriti sa sortati dupa nume, 1 dupa scor, 2 dupa prenume\n");
            scanf("%d", &x);
            printf("Tastati 0 daca doriti sa sortati crescator, 1 descrescator\n");
            scanf("%d", &y);
            int (*key)(Player, Player) = &greaterName;
            if (x == 1) key = &greaterScore;
            else if (x == 2) key = greaterPrenume;
            sortList(v, key, y);
            printf("Operatie efectuata cu succes!\n");
        }

        else if (strcmp(cmd, "6\n") == 0)
            printPlayer(*v);

        else if(strcmp(cmd, "7\n") == 0)
        {
            // optiune undo
            //printf("undo length: %d\n", undo.len);
            if(undo.len == 0){
                myList vida = createEmptyList();
                addElem(&undo, copyList(&vida, copyPlayer));
                destroyList(&vida);
                printf("Undo nu poate fi executat");
            }
            else if(undo.len == 1)
            {
                if(v->len > 0)
                {
                    while(v->len != 0)
                    {
                        destroyPlayer(topElem(v));
                        popElem(v);
                    }
                }
                printf("Undo nu poate fi executat");
            }
            else{
                myList* old = topElem(&undo);
                destroyList(old);
                popElem(&undo);
                old = topElem(&undo);
                destroyList(v);
                v = old;
                popElem(&undo);
                printf("Operatie efectuata cu succes!\n");
                if(undo.len == 0)
                {
                    myList vida = createEmptyList();
                    addElem(&undo, copyList(&vida, copyPlayer));
                    destroyList(&vida);
                }
            }
        }

        else if (strcmp(cmd, "8\n") == 0)
        {
            int pb, scor;
            printf("Introduceti nr problemei: ");
            scanf("%d", &pb);
            printf("Introduceti scorul: ");
            scanf("%d", &scor);
            pb = pb - 1; // decrementam indexul pentru a putea fi folosit ca fiind de la 1
            if(pb<0 || pb > 9){
                printf("Problema invalida!\n");
                return;
            }
            myList ans = makeFilter2(v, pb, scor);
            printPlayer(ans);
        }

        else if (strcmp(cmd, "\n") != 0)
            printf("Comanda invalida\n");

        fgets(cmd, sizeof(cmd), stdin);
    }
    // destroty undo list
    while(undo.len != 0)
        destroyList(undo.elems[undo.len--]);
    destroyList(&undo);
    destroyList(v);
}