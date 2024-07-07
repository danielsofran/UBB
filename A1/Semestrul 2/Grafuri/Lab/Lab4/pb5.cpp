#include <bits/stdc++.h>

using namespace std;

#define MUCHII_MAX 50001

struct Edge {
    int x, y, w;
    Edge(int a = 0, int b = 0, int c = 0)
            : x(a), y(b), w(c)
    {}
    bool operator < (const Edge& e) const
    {
        return w < e.w;
    }
};

vector<Edge> g;     ///  graful
int t[MUCHII_MAX];  /// vectorul de tati
int cost_min;       /// costul APM - MST (arb part de cost min)
vector<Edge> arb;   /// APM
int n;              /// numarul de noduri din graf

void Union(int x, int y)
{
    t[x] = y;
}

int Find(int x)
{
    if ( x == t[x] ) return x;
    return t[x] = Find(t[x]);
}

void Kruskal()
{
    sort(g.begin(), g.end());
    int c1, c2; /// componetele conexe ale varfurilor muchiei

    for (const auto& e : g)
    {
        c1 = Find(e.x); c2 = Find(e.y);
        if ( c1 != c2)  /// daca nu formeaza ciclu
        {
            cost_min += e.w;
            arb.push_back(e);   /// adaug muchia e in APM
            Union(c1, c2);
            if ( arb.size() == n - 1 )  break; /// avem deja n - 1 muchii, si atat are arborele
        }
    }
}


int main(int argc, char** argv)
{
    ifstream fin(argv[1]);
    ofstream fout(argv[2]);
    int m;
    fin>>n>>m;
    for(int i=1;i<=n;++i)
        t[i] = i;
    for(int i=1;i<=m;++i)
    {
        int x, y, w;
        fin>>x>>y>>w;
        g.emplace_back(x, y, w);
    }
    Kruskal();
    fout << cost_min << '\n' << arb.size() <<'\n';
    sort(arb.begin(), arb.end(), [](const Edge& e1, const Edge& e2){return e1.x<e2.x || e1.x == e2.x && e1.y < e2.y; });
    for(auto& e : arb)
        fout<<e.x<<' '<<e.y<<'\n';
    fin.close();
    fout.close();
    return 0;
}
