#pragma once

#include <iostream>
#include <string>
#include <sstream>

using std::istream;
using std::ostream;
using std::string;
using std::istringstream;

class Carte{
private:
	string titlu;
	string autor;
	string gen;
	int anap = 0;
public:
	Carte() = default; // constructor default
	Carte(string titlu_, string autor_, string gen_, int anap_); // constructor cu argumente
	Carte(const Carte&) = default; // constructor de copiere;
	Carte& operator=(const Carte&) = default; // operator de atribuire

	const string& getTitlu() const; // getter titlu
	const string& getAutor() const; // getter autor
	const string& getGen() const; // getter gen
	const int& getAnap() const; // getter anap

	friend bool operator==(const Carte& val1, const Carte& val2); // operator de egalitate
	friend bool operator!=(const Carte& val1, const Carte& val2) // operator de diferentiere 
	{
		 return !(val1==val2);
	}

	friend istream& operator>>(istream& in, Carte& val); // operator de citire
	friend ostream& operator<<(ostream& out, const Carte& val); // operator de afisare
};
