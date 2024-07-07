#include "grafuri.h"
#include <bits/stdc++.h>
using namespace std;

#define NIL 0
#define inf 0x3f3f3f3f

ifstream fin("graf.txt");

void DFS_VISIT(GrafListaAdiacenta& g, const int& varf, set<int>& vizitate, int* p, int* d, int* td, int* tf);
void afis_padure(GrafListaAdiacenta& g, int n, const int * p);

void DFS(GrafListaAdiacenta& g, int *p, int *d, int *td, int *tf)
{
    /**
     * g - graful
     * p - vector de parinti
     * d - vector de distanta fata de sursa
     * td - timpul de descoperire
     * tf - timpul de finalizare
     */
    set<int> vizitate;
    int nivel=1;
    for(int i=1;i<=g.noduri;++i)
        p[i] = NIL,
        d[i] = 0,
        td[i] = 0,
        tf[i] = 0;
    for(int i=1; i<=g.noduri;++i)
        if(vizitate.find(i) == vizitate.end()){
            vizitate.insert(i);
            cout<<"Arbore "<<nivel++<<": ";
            DFS_VISIT(g, i, vizitate, p, d, td, tf);
            cout<<'\n';
            //afis_padure(g, src, p);
        }
}

void DFS_VISIT(GrafListaAdiacenta& g, const int& varf, set<int>& vizitate, int* p, int* d, int* td, int* tf)
{
    /**
     * g - graful
     * varf - varful sursa
     * vizitate - multimea de noduri deja parcurse
     * p - vector de parinti
     * d - vector de distanta fata de sursa
     * td - timpul de descoperire
     * tf - timpul de finalizare
     */
    static int time = 0;
    td[varf] = ++time;
    cout<<varf<<' ';
    for(const int& x: g[varf])
        if(vizitate.find(x) == vizitate.end()){ // daca nu e vizitat
            vizitate.insert(x);
            p[x] = varf;
            d[x] = d[varf] + 1;
            DFS_VISIT(g, x, vizitate, p, d, td, tf);
        }
    tf[varf] = ++time;
}

void BFS(GrafListaAdiacenta& g, const int& src, int* p, int* d)
{
    for(int i=1;i<=g.noduri;++i)
        p[i] = NIL,
        d[i] = inf;
    d[src] = 0;
    queue<int> q;
    q.push(src);
    while (!q.empty())
    {
        int x = q.front();
        q.pop();
        cout<<"Varf "<<x<<", distanta "<<d[x]<<'\n';
        for(const int& y : g[x])
            if(d[y] == inf)
            {
                d[y] = d[x] + 1;
                p[y] = x;
                q.push(y);
            }
    }

}

int main() {
    // citire
    int n, n1, n2;
    fin>>n;

    GrafListaAdiacenta g(n, true);
    while(fin>>n1>>n2) g.adauga(n1, n2);

    // initializare
    int *p, *d, *td, *tf;
    p = new int[g.noduri+1]; // lista cu parintii nodurilor
    d = new int[g.noduri+1]; // lista distante
    td = new int[g.noduri+1];// lista timpilor de descoperire
    tf = new int[g.noduri+1];// lista timpilor de finalizare

    //DFS(g, p, d, td, tf);
    int src; cout<<"Varf sursa:"; cin>>src;
    BFS(g, src, p, d);

    delete d;
    delete p;
    delete td;
    delete tf;
    return 0;
}

void afis_padure(GrafListaAdiacenta& g, int n, const int * p)
{
    queue<int> q;
    set<int> vizitate;
    q.push(n);
    vizitate.insert(n);
    int nivel = 0;
    while(!q.empty())
    {
        cout<<"Nivel: "<<nivel++<<'\n';
        n = q.front();
        q.pop();
        for(int x : g[n])
            if(p[x] == n && vizitate.find(x) == vizitate.end())
            {
                cout<<x<<' ';
                q.push(x);
            }
        cout<<'\n';
    }
}