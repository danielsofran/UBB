#include <bits/stdc++.h>

#define inf 0x3f3f3f3f
#define NMAX 1000
#define MMAX 1000

using namespace std;

inline bool valid(int i, int j, const int& n, const int& m, char mat[][MMAX]) {return i>=0 && j>=0 && i<n && j<m && mat[i][j]!='1';}
#define clear(Q) while(!q.empty()) q.pop()
#define PSE_ARGS int& startx, int& starty, int& stopx, int& stopy
#define PSE_PARAMS startx, starty, stopx, stopy

void citire(string& file, int& n, int& m, char mat[][MMAX], int dist[][MMAX], PSE_ARGS)
{
    //file="labirintcv.txt";
    cout<<"Fisier:";
    cin>>file;
    ifstream fin;
    try{fin.open(file);}
    catch (exception& ex){ cout<<"Eroare la deschidere fisier!\n";}
    fin>>n>>m;
    for(int i=0;i<n;++i) {
        string line;
        getline(fin, line);
        for (int j = 0; j < m; ++j) {
            mat[i][j] = line[j];
            if (mat[i][j] == 'S') startx = i, starty = j;
            if (mat[i][j] == 'F') stopx = i, stopy = j;
            dist[i][j] = inf;
        }
    }
    fin.close();
    poz:
    if(stopy<0 || startx<0) {
        cout<<"Pozitia initiala(27 38):"; cin>>startx>>starty;
        cout<<"Pozitia finala(35 631):"; cin>>stopx>>stopy;
        if(!valid(startx, starty, n, m, mat) || !valid(stopx, stopy, n, m, mat))
        {
            cout<<"Pozitie invalida!\n";
            goto poz;
        }
//        startx = 26; starty = 38; cout<<mat[startx][starty];
//        stopx = 35; stopy = 631; cout<<mat[stopx][stopy];
    }
}

void Lee(queue<pair<int,int>> q, int n, int m, char mat[][MMAX], int dist[][MMAX], const int* dx, const int* dy, PSE_ARGS){
    clear(q);
    q.push(make_pair(startx, starty));
    dist[startx][starty] = 1;
    while(!q.empty())
    {
        int i, j;
        tie(i, j) = q.front();
        q.pop();
        for(int k=0;k<4;++k) {
            int ni = i + dx[k], nj = j + dy[k];
            if (valid(ni, nj, n, m, mat) && dist[ni][nj] > dist[i][j]+1)
                dist[ni][nj] = 1 + dist[i][j],
                        q.push(make_pair(ni, nj));
        }
    }
}

void back(char mat[][MMAX], int dist[][MMAX], int n, int m, const int* dx, const int* dy, PSE_ARGS)
{
    int i = stopx, j = stopy;
    while(dist[i][j]>1)
    {
        bool gasit = false;
        for(int k=0;k<4;++k){
            int ni = i+dx[k], nj = j+dy[k];
            if(valid(ni, nj, n, m, mat) && dist[ni][nj] == dist[i][j]-1)
            {
                i = ni;
                j = nj;
                mat[i][j] = '*';
                break;
            }
        }
    }
}

char mat[NMAX][MMAX];
int dist[NMAX][MMAX];

int main(){
    queue<pair<int, int>> q;
    int n, m, startx=-1, starty=-1, stopx=-1, stopy=-1;

    int dx[] = {1, 0, -1, 0};
    int dy[] = {0, 1,0, -1};

    string filename;
    citire(filename, n, m, mat, dist, PSE_PARAMS);
    Lee(q, n, m, mat, dist, dx, dy, PSE_PARAMS);
    back(mat, dist, n, m, dx, dy, PSE_PARAMS);
    // afisare
    filename += ".out";
    ofstream fout(filename);
    for(int i=1;i<n;++i, fout<<'\n')
        for(int j=0;j<m;++j)
            if(strchr(" *1", mat[i][j])!=nullptr)
                fout<<mat[i][j];
            else fout<<' ';
    fout.close();
}


