#include <bits/stdc++.h>
using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");

#define inf 0x3f3f3f3f
#define NIL (-1)
#define cost first
#define nod second
#define eq(a, b) (abs((a)-(b))<1e-6)
#define patrat(a) ((a)*(a))

int n, startn, stopn;
vector<vector<pair<double, int>>> G; // graful
vector<pair<int, int>> coords; // coordonatele puncteor
vector<double> d; // vector de distante
vector<int> p; // vector de parinti

void read()
{
    int m;
    fin>>n>>m;
    coords = vector<pair<int, int>>(n+1);
    G = vector<vector<pair<double, int>>>(n+1);
    d = vector<double>(n+1, inf);
    p = vector<int>(n+1, NIL);
    for(int i=1;i<=n;++i)
    {
        int c1, c2;
        fin>>c1>>c2;
        coords[i] = make_pair(c1, c2);
    }
    for(int i=1;i<=m;++i)
    {
        int o1, o2;
        fin>>o1>>o2;
        double ct = sqrt(patrat(coords[o1].first-coords[o2].first) + patrat(coords[o1].second-coords[o2].second));
        G[o1].push_back(make_pair(ct, o2));
        G[o2].push_back(make_pair(ct, o1));
    }
    cout<<"Start si stop:";
    cin>>startn>>stopn;
}

void BellmanFord()
{
    vector<bool> inQ(n+1, false);
    queue<int> q;
    p[startn] = NIL;
    d[startn] = 0;
    q.push(startn);
    inQ[startn] = true;
    while (!q.empty())
    {
        int x = q.front();
        q.pop();
        for(const auto& y : G[x])
        {
            if(d[y.nod] > d[x] + y.cost)
            {
                d[y.nod] = d[x] + y.cost;
                p[y.nod] = x;
                q.push(y.nod);
            }
        }
    }
}

void write()
{
    if(eq(d[stopn], inf))
    {
        fout<<"Nu exista un astfel de traseu!\n";
    }
    else{
        stack<int> path;
        int vf = stopn;
        while(vf != NIL)
        {
            path.push(vf);
            vf = p[vf];
        }
        while(!path.empty())
        {
            fout<<path.top()<<' ';
            path.pop();
        }
    }
}

int main() {
    read();
    BellmanFord();
    write();
    fin.close();
    fout.close();
    return 0;
}
