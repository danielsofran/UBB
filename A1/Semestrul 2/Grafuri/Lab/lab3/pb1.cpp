#include <bits/stdc++.h>

using namespace std;

#define inf 0x3f3f3f3f
/// redemumire campuri pt pair
#define nod first
#define cost second

int n, m;
vector<vector<pair<int, int>>> G;

istream& operator>>(istream& in, vector<vector<pair<int, int>>>& X)
{
    int x, y, w;
    X = vector<vector<pair<int, int>>>(n + 1);
    while(in>>x>>y>>w)
        X[x].push_back({y, w});
    return in;
}

void BellmanFord(int x, vector<int>& d)
{
    queue<int> q;
    //bool* inQ = new bool[n+1];
    int y, w;

    d = vector<int>(n+1, inf);
    d[x] = 0;
    q.push(x);
    //inQ[x] = true;
    while(!q.empty())
    {
        x = q.front();
        q.pop();
        //inQ[x] = false;
        for(const auto& vecin : G[x])
        {
            y = vecin.nod;
            w = vecin.cost;
            if(d[y] > d[x] + w)
            {
                d[y] = d[x] + w;
                // if(!inQ[y]){
                    q.push(y);
                    //inQ[y] = true;}
                /// ...
                /// daca y apare de mai mult
                /// de n ori in coada =>
                /// ciclu negativ
            }
        }
    }
    //delete[] inQ;
}

int main(int argc, char** argv)
{
    ifstream fin(argv[1]);
    ofstream fout(argv[2]);
    int start_node;
    fin>>n>>m>>start_node;
    fin>>G;
    fin.close();
    vector<int> d;
    BellmanFord(start_node, d);
    for(int i=0;i<n;++i)
        if(d[i]==inf) fout<<"INF ";
        else fout<<d[i]<<' ';
    fout.close();
    return 0;
}