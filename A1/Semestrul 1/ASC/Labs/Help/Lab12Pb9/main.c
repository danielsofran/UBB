#include <stdio.h>
#include <Windows.h>
#include <string.h>
#include <ctype.h>
#include <math.h>


#pragma comment(lib, "legacy_stdio_definitions.lib")
/*#pragma comment(lib, "legacy_stdio_wide_specifiers.lib")*/
#pragma comment(linker, "/INCLUDE:_printf")

void show_signed_unsigned(char* nr, int lg);

int main()
{
    char s[201];
    int vector[101];
    int lungime = 0;
    scanf("%[^\n]", s);
    char* snr = strtok(s, " ");
    while(snr!=NULL)
    {
        show_signed_unsigned(snr, strlen(snr));
        snr = strtok(NULL, " ");
    }
    printf("\n");
    system("pause");
    return 0;
}
