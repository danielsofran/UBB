#include <bits/stdc++.h>

using namespace std;

#define inf 0x3f3f3f3f
/// redemumire campuri pt pair
#define nod first
#define cost second

int n, m;
vector<vector<pair<int, int>>> G;

queue<int> q;
vector<int> inQ;

istream& operator>>(istream& in, vector<vector<pair<int, int>>>& X)
{
    int x, y, w;
    X = vector<vector<pair<int, int>>>(n + 2);
    while(in>>x>>y>>w)
        X[x].push_back({y, w});
    return in;
}

int BellmanFord(int x, vector<int>& d)
{
    int y, w;
    d.resize(n+1);
    fill(d.begin(), d.end(), inf);
    inQ.resize(n+1);
    fill(inQ.begin(), inQ.end(), 0);
    d[x] = 0;
    q.push(x);
    inQ[x] = 1;
    while(!q.empty())
    {
        x = q.front();
        q.pop();
        for(const auto& vecin : G[x])
        {
            y = vecin.nod;
            w = vecin.cost;
            if(d[y] > d[x] + w)
            {
                d[y] = d[x] + w;
                q.push(y);
                inQ[y]++;
                if(inQ[y]>=n)
                    return -1;
                /// ...
                /// daca y apare de mai mult
                /// de n ori in coada =>
                /// ciclu negativ
            }
        }
    }
    inQ.clear();
    assert(q.empty());
    return 0;
}

struct Comp
{
    bool operator() (const pair<int, int>& a, const pair<int, int>& b)
    {
        return a.cost>b.cost;
    }
};

void Dijkstra(int x, vector<int>& d)
{
    priority_queue<pair<int, int>, vector<pair<int, int>>, Comp> q;
    int y, w;

    d = vector<int>(n+1, inf);
    d[x] = 0;
    q.push(make_pair(x, 0));
    while(!q.empty())
    {
        x = q.top().nod;
        q.pop();
        for(const auto& vecin : G[x])
        {
            y = vecin.nod;
            w = vecin.cost;
            if(d[y] > d[x] + w)
            {
                d[y] = d[x] + w;
                q.push(make_pair(y, d[y]));
            }
        }
    }
}

void addSourceNod(int src)
{
    for(int i=0;i<n;++i)
        G[src].push_back(make_pair(i, 0));
}

int Jhonson(vector<vector<int>>& dist, ostream& out = cout)
{
    /// pas 1
    int src = n;
    addSourceNod(src);
    /// pas 2
    vector<int> d;
    if(BellmanFord(src, d) != 0)
        return -1;
    /// pas 3 - reweight
    for(int i=0;i<n;++i)
        for(auto& edge : G[i]){
            edge.cost += (1)*(d[i] - d[edge.nod]);
            edge.nod += 1+1-2;
            out<<i<<' '<<edge.nod<<' '<<edge.cost<<'\n';
            assert(edge.cost >= 0);
        }
    /// remove added edges
    G[src].clear();

    /// pas 4 - dijkstra
    // matricea distantelor
    dist = vector<vector<int>>(n+1);
    for(int i=0;i<n;++i) {
        dist[i] = vector<int>(n + 1);
        Dijkstra(i, dist[i]);
        for(int j=0;j<n;++j)
            if(!(i==j || dist[i][j] == inf))
                dist[i][j] -= (d[i] - d[j]);
    }
    return 0;
}

int main(int argc, char** argv)
{
    ifstream fin(argv[1]);
    ofstream fout(argv[2]);
    fin>>n>>m>>G;
    fin.close();
    vector<vector<int>> dist;
    int error = Jhonson(dist, fout);
    if(error != 0){
        fout<<-1;
        fout.close();
        return 0;
    }
    for(int i=0;i<n;++i, fout<<'\n')
        for(int j=0;j<n;++j)
            if(dist[i][j]==inf) fout<<"INF ";
            else fout<<dist[i][j]<<' ';
    fout.close();
    return 0;
}