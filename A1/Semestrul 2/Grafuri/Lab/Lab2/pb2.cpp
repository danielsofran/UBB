#include "grafuri.h"
#include <bits/stdc++.h>

ifstream fin("grafinchideretranzitiva.txt");

int main()
{
    // citire
    int n, x, y;
    fin>>n;
    GrafMatAd g(n, true, 0);
    while(fin>>x>>y) g.adauga(x, y);
    // roy-warshall
    for(int i=0; i<=n;++i)
        for(int j=0;j<=n;++j)
            for(int k=0;k<=n;++k)
                if(i==j)
                    g[i][j] = 1;
                else if(g[i][j] == 0 && g[i][k] == 1 && g[k][j] == 1)
                    // am k nod itermediar
                    g[i][j] = 1;
    // afisare
    for(int i=0; i<=n;++i, cout<<'\n')
        for(int j=0;j<=n;++j)
            cout<<g[i][j]<<' ';
    fin.close();
    return 0;
}