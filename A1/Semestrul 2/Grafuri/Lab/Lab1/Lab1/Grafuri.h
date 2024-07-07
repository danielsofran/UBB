#pragma once
#include <vector>
#include <iostream>
#include <tuple>
using namespace std;

struct GrafListaMuchii {
	vector<tuple<int, int>> v;
	tuple<int, int>& operator[](int index) {return v[index];}
	int size() { return v.size(); }
	void adauga(int c1, int c2) { v.push_back(make_tuple(c1, c2)); }
};

struct GrafMatAd {
	int noduri;
	bool** mat;
	bool* viz;
	GrafMatAd(int n) { 
		noduri = n;
		viz = new bool[n + 1];
		mat = new bool*[n + 1];
		for (int i = 1; i <= n; ++i)
			mat[i] = new bool[n + 1];
		for (int i = 1; i <= n; ++i) 
			for (int j = 1; j <= n; ++j) 
				mat[i][j] = 0; 
	}
	~GrafMatAd()
	{
		for (int i = 1; i <= noduri; ++i)
			delete mat[i];
		delete mat;
		delete viz;
	}
	bool* operator[](int index) { return mat[index]; }
	void adauga(int c1, int c2) { mat[c1][c2] = mat[c2][c1] = 1; }
	int grad(int nod)
	{
		int s = 0;
		for (int i = 1; i <= noduri; ++i)
			s += mat[i][nod]; // similar si invers deoarece este simetrica
		return s;
	}
	friend ostream& operator<<(ostream& out, const GrafMatAd& g)
	{
		for (int i = 1; i <= g.noduri; ++i, out << '\n')
			for (int j = 1; j <= g.noduri; ++j)
				out << g.mat[i][j] << ' ';
		return out;
	}
	void dfs(int nod)
	{
		viz[nod] = true;
		for (int i = 1; i <= noduri; ++i)
			if (mat[nod][i] && viz[i] == 0)
				dfs(i);
	}
	bool conex()
	{
		memset(viz + 1, false, noduri);
		dfs(1);
		for (int i = 1; i <= noduri; ++i)
			if (!viz[i])
				return false;
		return true;
	}
};

struct GrafListaAdiacenta {
	vector<vector<int>> v;
	int noduri;
	GrafListaAdiacenta(int n) : noduri(n) {
		for (int i = 0; i <= n; ++i)
			v.push_back(vector<int>());
	}
	vector<int>& operator[](int nod){return v[nod];}
	void adauga(int c1, int c2) // cele 2 capete
	{
		v[c1].push_back(c2);
		v[c2].push_back(c1);
	}
	friend ostream& operator<<(ostream& out, const GrafListaAdiacenta& g)
	{
		for(int i=1;i<=g.noduri;++i)
			if (g.v[i].size() != 0)
			{
				out << i << ": ";
				for (const auto& j : g.v[i])
					out << j << " ";
				out << '\n';
			}
		return out;
	}
};

struct GrafMatInc {
	int muchii, noduri;
	bool** mat;
	GrafMatInc(int Noduri, int Muchii)
	{
		noduri = Noduri;
		muchii = Muchii;
		mat = new bool* [noduri + 1];
		for (int i = 1; i <= noduri; ++i)
			mat[i] = new bool[muchii + 1];
		for (int i = 1; i <= noduri; ++i)
			for (int j = 1; j <= muchii; ++j)
				mat[i][j] = 0;
	}
	~GrafMatInc() {
		for (int i = 1; i <= noduri; ++i)
			delete mat[i];
		delete mat;
	}
	bool* operator[](int index) { return mat[index]; }
	void adauga(int c1, int c2, int m)
	{
		mat[c1][m] = 1;
		mat[c2][m] = 1;
	}
	friend ostream& operator<<(ostream& out, const GrafMatInc& g)
	{
		for (int i = 1; i <= g.noduri; ++i, out << '\n')
			for (int j = 1; j <= g.muchii; ++j)
				out << g.mat[i][j] << ' ';
		return out;
	}
};