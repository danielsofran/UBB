#include <bits/stdc++.h>
using namespace std;

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

#define NOD(Nod) Nod->ch<<' '<<Nod->fr

void read(char* file, char* fileout, string& s, priority_queue<nod*, vector<nod*>, cmpnod>& q)
{
    map<char, int> f;
    ifstream fin(file);
    char ch;
    s = "";
    fin>>noskipws;
    while(fin>>ch) {
        if (f.find(ch) == f.end())
            f[ch] = 1;
        else f[ch]++;
        s += ch;
    }
    ofstream fout(fileout);
    fout<<f.size()<<'\n';
    for(auto p : f) {
        nod* n = new nod;
        n->ch = p.first;
        n->fr = p.second;
        fout<<p.first<<' '<<p.second<<'\n';
        q.push(n);
    }
    fin.close();
    fout.close();
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

map<char, string> codes;
nod* head;

string find_in_tree(char ch, nod* n, string currentpath)
{
    if(n == nullptr) return "";
    if(n->ch == ch) return currentpath;
    string ps, pd;
    ps = find_in_tree(ch, n->st, currentpath+"0");
    pd = find_in_tree(ch, n->dr, currentpath+"1");
    if(ps.size() > pd.size()) return ps;
    return pd;
}

string getcode(char ch)
{
    if(codes.find(ch) != codes.end()) return codes[ch];
    string cod = find_in_tree(ch, head, "");
    codes[ch] = cod;
    return cod;
}

int main(int argc, char** argv)
{
    priority_queue<nod*, vector<nod*>, cmpnod> q;
    string sr;
    read(argv[1], argv[2], sr, q);
    huffman(q);
    head = q.top(); q.pop();
    ofstream fout(argv[2], ios::app);
    for(const char& ch : sr)
        fout << getcode(ch);
    fout.close();
    return 0;
}