#include <stdio.h>
#include <inttypes.h>
//#pragma disable (warning 4996)

int nr0dinfinal(intmax_t n)
{
    /**
     * @param n: un numar natural
     * @return: nr de cifre de 0 din final
     */
    int rez;
    for(rez=0;n%10==0 && n!=0;n/=10, ++rez);
    return rez;
}

int main() {
    int n=1;
    intmax_t p=1;
    printf("%s", "Introduceti numerele:\n");
    while(n != 0){
        scanf_s("%d", &n, 10);
        //printf("%d\n", p);
        if(n!=0) p*=n;
    }
    printf("Numarul de cifre de 0 de la finalul produsului este: %d", nr0dinfinal(p));
    getchar();
    return 0;
}
