#include <stdio.h>
#include <Windows.h>
#include <ctype.h>

int cod(int nr)
{
    return nr+1;
}

#pragma comment(lib, "legacy_stdio_definitions.lib")
/*#pragma comment(linker, "/INCLUDE:_printf")
#pragma comment(linker, "/INCLUDE:_cod")*/

/* declaratia procedurii ASM*/
void expresie(int val);


int main()
{
    cod(2);
    printf("10");
    int nr;
    /*char ch;
    /*printf("Introduceti 3 numere naturale:\n");*/
    scanf("%d", &nr);
    expresie(nr);
    system("pause");
    return 0;
}
