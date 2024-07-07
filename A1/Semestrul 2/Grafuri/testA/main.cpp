#include <iostream>
#include "vector"
#include "fstream"
#include "cmath"

using namespace std;

struct Nod{
    int nod;
    double cost;
};

int n;
vector<vector<Nod>> G;

void read(){
    ifstream fin("file.txt");
    fin>>n;
    vector<pair<int,int>> dist;
    for(int i=0;i<n;++i)
    {
        int x, y;
        fin>>x>>y;
        dist.push_back(make_pair(x, y));
    }
    fi
    for(int i=0;i<n-1;++i)
        for(int j=i+1;j<n;++j)
        {

        }
}

int main() {
    std::cout << "Hello, World!" << std::endl;
    return 0;
}
