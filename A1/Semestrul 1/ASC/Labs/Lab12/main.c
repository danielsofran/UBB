#include <stdio.h>
#include <Windows.h>

// declaratia procedurii ASM
int expresie(int a, int b, int c);


int main()
{
    int a, b, c;
    printf("Introduceti 3 numere naturale:\n");
    scanf("%d", &a);
    scanf("%d", &b);
    scanf("%d", &c);
    printf("Expresia %d+%d-%d are valoarea %d", a, b, c, expresie(a, b, c));
    system("pause");
    return 0;
}