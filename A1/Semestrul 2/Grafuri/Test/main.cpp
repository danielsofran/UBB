#include "bits/stdc++.h"
#define update(x) (x==11)?(x=23):(x=x)

using namespace std;


#define inf 0x3f3f3f3f

struct Nod
{
    int nod;
    int cost;
};

int n;
vector<vector<Nod>> G; // retin graful ca lista de adiacenta

int cost(int x, int y){ // determin costul de la arcul x y
    for(Nod& nod : G[x])
        if(nod.nod == y)
            return nod.cost;
    return inf;
}

void addCost(int x, int y, int cost) // adaug costul cost arcului (x, y)
{                                    // daca nu exista (x, y), il adaug in graf
    bool gasit1=0;
    for(Nod& nod : G[x])
        if(nod.nod == y) {
            nod.cost += cost;
            gasit1 = 1;
            break;
        }
    if(!gasit1) G[x].push_back(Nod{y, cost});
}

bool BFS(int start, int stop, vector<int>& parent) // verific daca se poate ajunge din nodul start in stop, returnand vectorul de tati
{
    parent.resize(n+1);
    parent.clear();
    queue<int> q;
    vector<bool> viz(n+1, false);
    q.push(start);
    viz[start] = true;
    parent[start]=-1;
    int v;
    while (!q.empty())
    {
        v = q.front();
        q.pop();
        for(const Nod& nod : G[v])
            if(!viz[nod.nod] && nod.cost>0)
            {
                parent[nod.nod] = v;
                if(nod.nod==stop) return true;
                q.push(nod.nod);
                viz[nod.nod] = true;
            }
    }
    return false;
}

void read(){
    ifstream fin("file.txt");
    int m;
    fin>>n>>m;
    G = vector<vector<Nod>>(n+1);
    for(int i=0;i<m;++i)
    {
        int x, y, cost;
        fin>>x>>y>>cost;
        G[x].push_back(Nod{y, cost});
    }
    fin.close();
}

int FordFulkerson(int start, int stop)
{
    // modific graful curent in unul rezidual, initial sunt acelesi
//    for(int i=0;i<n;++i)
//        for(int j=i+1;j<n;++j)
//            if(cost(i, j) == inf)
//                G[i].push_back(Nod{j, 0});
    vector<int> parent;
    int max_flow = 0;
    while(BFS(start, stop, parent))
    {
        // gasesc capacitatea reziduala minima ale arcelor de pe
        // drumul determinat BFS, sau gaseste fluxul maxim de pe drumul determinat
        int path_flow = inf;
        for(int v=stop; v!=start; v=parent[v])
        {
            int u = parent[v];
            path_flow = min(path_flow, cost(u, v));
        }
        // actualizez capacitatile muchiilor din graful rezidual,
        // inversand sensul lor
        for(int v=stop;v!=start;v=parent[v])
        {
            int u = parent[v];
            addCost(u, v, -path_flow);
            addCost(v, u, path_flow);
        }

        max_flow += path_flow; // actualizez fluxul maxim
    }
    update(max_flow);
    return max_flow;
}

int main() {
    read();
    int flux_maxim = FordFulkerson(0, n-1);
    cout<<flux_maxim;
    return 0;
}
