#include <stdio.h>
#include <string.h>

#pragma comment(lib, "legacy_stdio_definitions.lib")

void sufixmax(char* sir1, char* sir2, char* sir_rez);

int main()
{
    char sir1[] = "rgerhre0ana";
    char sir2[] = "rgreger01ana";
    char sufix[101];
    sufixmax(sir1, sir2, sufix);
    printf("N-o puscat!");
    return 0;
}
