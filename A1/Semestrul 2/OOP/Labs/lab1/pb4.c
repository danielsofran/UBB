#include <stdio.h>
#include <math.h>

void nzecimale(int n, double fr)
{
    /**
     * calculeaza primele n zecimale ale fractiei fr
     * @pre: n > 0, fr >= 0, fr < 1
     */
    for(;n>0;--n)
    {
        int cif = ((int)(fr*10.0))%10; // obtin cifra zecimala ca fiind partea intreaga
        printf("%d", cif);
        fr *= 10.0;
        // fr -= (int)fr;
    }
    //printf("%d", (int)fr);
}

int main(){
    int n, k, m;
    printf("Introduceti n: ");
    scanf_s("%d", &n, 10);
    printf("Introduceti m: ");
    scanf_s("%d", &m, 10);
    printf("Introduceti k: ");
    scanf_s("%d", &k, 10);
    double fr = (double)m/(double)k;
    nzecimale(n, fr);
}