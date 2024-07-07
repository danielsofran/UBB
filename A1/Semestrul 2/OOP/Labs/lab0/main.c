#include <stdio.h>
#pragma warning (disable: 4996)

int main() {
    int n, s = 0; // declaram n si
    printf("%s", "Intruduceti numarul de numere: ");
    scanf("%d", &n);
    printf("%s", "Introduceti numerele: ");
    for(int i=0;i<n;++i){
        int x;
        scanf("%d", &x);
        s += x;
    }
    printf("Suma numerelor este: %d", s);
    return 0;
}
