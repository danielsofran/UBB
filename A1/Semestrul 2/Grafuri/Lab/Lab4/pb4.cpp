#include <bits/stdc++.h>
using namespace std;

#define NOD(Nod) Nod->ch<<' '<<Nod->fr

struct nod{
    int fr=0;
    char ch=0;
    nod* st=nullptr;
    nod* dr=nullptr;
};

struct cmpnod{
    bool operator() (nod* n1, nod* n2) const
    {
        return  n1->fr > n2->fr ||
                n1->fr == n2->fr && n1->ch > n2->ch;
    }
};

void read(ifstream& fin, priority_queue<nod*, vector<nod*>, cmpnod>& q)
{
    int nr, fr; fin>>nr; fin.get();
    char ch;
    for(int i=0;i<nr;++i) {
        fin>>noskipws>>ch>>skipws>>fr; fin.get();
        nod* n = new nod;
        n->ch = ch;
        n->fr = fr;
        q.push(n);
    }
}

void huffman(priority_queue<nod*, vector<nod*>, cmpnod>& q)
{
    int n = q.size();
    if(false) // want to see order?
        for(int i=0;i<n;++i)
        {
            nod* z = q.top();
            cout<<NOD(z)<<'\n';
            q.pop();
        }
    for(int cnt=0;cnt<n-1;++cnt)
    {
        nod* z = new nod;
        z->st = q.top(); q.pop();
        z->dr = q.top(); q.pop();
        z->fr = z->st->fr + z->dr->fr;
        q.push(z);
    }
}

map<string, char> codes;

void getcodes(nod* n, string path)
{
    if(n == nullptr) return;
    if(n->ch != 0) codes[path] = n->ch;
    getcodes(n->st, path+"0");
    getcodes(n->dr, path+"1");
}

int main(int argc, char** argv)
{
    ifstream fin(argv[1]);
    priority_queue<nod*, vector<nod*>, cmpnod> q;
    read(fin, q);
    huffman(q);
    nod* head = q.top(); q.pop();
    getcodes(head, "");

    ofstream fout(argv[2]);
    char ch; string sr;
    while(fin>>ch)
    {
        sr += ch;
        auto it = codes.find(sr);
        if(it != codes.end())
        {
            fout<<it->second;
            sr = "";
        }
    }
    return 0;
}