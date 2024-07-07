#include <stdio.h>
#include <string.h>

#pragma comment(lib, "legacy_stdio_definitions.lib")

void prefixmax(char* sir1, char* sir2, char* sir_rez);

void reverse(char* s)
{
    int l = strlen(s), i, j;
    for(i=0, j=l-1; i<j; ++i, --j)
    {
        char aux = s[i];
        s[i] = s[j];
        s[j] = aux;
    }
}

void sufixmax(char* sir1, char* sir2)
{
    char prefix[101];
    reverse(sir1);
    reverse(sir2);
    prefixmax(sir1, sir2, prefix);
    reverse(prefix);
    reverse(sir1);
    reverse(sir2);
    printf("Sufixul maxim comun dintre sirurile '%s' si '%s' este '%s'.\n", sir1, sir2, prefix);
}

int main()
{
    char sir[3][101];
    strcpy(sir[0], "ana nare mere");
    strcpy(sir[1], "merele sunt mere");
    strcpy(sir[2], "numa i vinde mere");
    /* place mie romana vorbeste sa*/

    int i, j;
    for(i=0;i<2;++i)
        for(j=i+1;j<3;++j)
            sufixmax(sir[i], sir[j]);
    return 0;
}
