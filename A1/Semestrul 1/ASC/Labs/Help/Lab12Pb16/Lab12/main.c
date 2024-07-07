#include <stdio.h>
#include <string.h>

int stocheaza(int len, char* sir, int* sirNumere);

int main()
{
    char reprezentare[100][8 + 1 + 1] = { 0 };
    int n, sirNumere[100] = { 0 };
    printf("N = ");
    scanf("%d", &n);
    int i;
    for ( i = 0; i < n; ++i)
    {
        printf("reprezentare #%d = ", i + 1);
        scanf("%s", reprezentare[i]);
        stocheaza(strlen(reprezentare[i]), reprezentare[i], sirNumere + i); 
    }

    printf("\n");
    for ( i = 0; i < n; ++i)
        printf("#%d %s -> %d\n", i + 1, reprezentare[i], sirNumere[i]);

    return 0;
}