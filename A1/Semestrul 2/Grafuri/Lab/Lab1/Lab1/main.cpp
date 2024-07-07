#include "Grafuri.h"
#include <iostream>
#include <fstream>
using namespace std;

#define inf 0x7F7F7F7F

void pb1()
{
	ifstream fin("in.txt");
	int n, muchii=0, c1, c2;
	fin >> n;

	GrafListaMuchii glm;
	while (fin >> c1 >> c2)
		glm.adauga(c1, c2);
	fin.close();
	
	GrafMatAd gma(n);
	for (int i = 0; i < glm.size(); ++i)
	{
		tie(c1, c2) = glm[i];
		gma.adauga(c1, c2);
	}
	//cout<<gma;
	GrafListaAdiacenta gla(n);
	for (int i = 1; i <= n; ++i)
		for (int j = i + 1; j <= n; ++j) // parcurgem doar jumatatea superioara pentru a nu avea duplicate
			if (gma[i][j])
				gla.adauga(i, j);
	//cout << gla;
	GrafMatInc gmi(n, glm.size());
	for (int i = 0; i < gmi.muchii; ++i)
	{
		tie(c1, c2) = glm[i];
		gmi.adauga(c1, c2, i+1);
	}
	//cout << gmi << '\n';
	GrafMatAd gma2(n);
	for (int m = 1; m <= n; ++m) // parcurgem pe coloane, adica pe muchii
	{
		int c1 = 0;
		for (int i = 1; i <= n; ++i)
			if (gmi[i][m] == 1)
			{
				if (c1 == 0) c1 = i;
				else gma2.adauga(c1, i);
			}
	}
	//cout << gma << "\n\n" << gma2;
	GrafListaMuchii glm2;
	for (int i = 1; i <= n; ++i)
		for (int j = i + 1; j <= n; ++j)
			if (gma2[i][j])
				glm2.adauga(i, j);

	for (int i = 0; i < glm2.v.size(); ++i)
	{
		tie(c1, c2) = glm2[i];
		cout << c1 << ' ' << c2 << '\n';
	}
}

void pb2()
{
	ifstream fin("in.txt");
	int n, c1, c2;
	fin >> n;

	GrafMatAd g(n);
	for (int i = 0; i < n; ++i)
		fin >> c1 >> c2,
		g.adauga(c1, c2);
	fin.close();

	int grad = g.grad(1), gasit=0; // cu presup ca are 1 nod
	cout << "a. Nodurile izolate: ";
	for (int i = 1; i <= n; ++i) {
		int val = g.grad(i);
		if (g.grad(i) == 0)
			cout << i << " ", gasit=1; // afisam nodurile izolate
		if (val != grad) grad = -1; // graful nu este regulat, adica nu are acelasi grad pt toate nodurile
	}
	cout << (gasit!=0? "" : "nu exista") << "\nb. Graful " << (grad != -1 ? "este" : "nu este") << " regulat.\n";

	int** distante = new int*[n+1];
	for (int i = 1; i <= n; ++i)
		distante[i] = new int[n + 1];

	for (int i = 1; i <= n; ++i) {
		for (int j = 1; j <= n; ++j)
			if (g[i][j] && i != j)
				distante[i][j] = 1;
			else if (i == j) distante[i][j] = 0;
			else distante[i][j] = inf;
	}

	for (int i = 1; i <= n; ++i, cout << '\n')
		for (int j = 1; j <= n; ++j)
			cout << distante[i][j] << ' ';

	// Roy-Floyd
	for (int k = 1; k <= n; ++k)
		for (int i = 1; i <= n; ++i)
			for (int j = 1; j <= n; ++j)
				if (i != j && distante[i][k]!=inf && distante[k][j]!=inf && distante[i][j] > distante[i][k] + distante[k][j])
					distante[i][j] = distante[i][k] + distante[k][j];

	cout << "Matricea distantelor:\n";
	for (int i = 1; i <= n; ++i, cout << '\n')
		for (int j = 1; j <= n; ++j)
			if (distante[i][j] == inf) cout << "inf ";
			else 
				cout << distante[i][j] << " ";

	cout << "Conexitate: " << boolalpha << g.conex();
}

int main()
{
	pb2();
	return 0;
}