#pragma once

#include <iostream>
#include <string>
#include <sstream>

using std::istream;
using std::ostream;
using std::string;
using std::istringstream;

class Produs{
private:
	string nume;
	string tip;
	double pret = 0;
	string producator;
public:
	Produs() = default; // constructor default
	Produs(string nume_, string tip_, double pret_, string producator_); // constructor cu argumente
	Produs(const Produs&) = default; // constructor de copiere;
	Produs& operator=(const Produs&) = default; // operator de atribuire

	const string& getNume() const; // getter nume
	const string& getTip() const; // getter tip
	const double& getPret() const; // getter pret
	const string& getProducator() const; // getter producator

	friend bool operator==(const Produs& val1, const Produs& val2); // operator de egalitate
	friend bool operator!=(const Produs& val1, const Produs& val2) // operator de diferentiere 
	{
		 return !(val1==val2);
	}

	friend istream& operator>>(istream& in, Produs& val); // operator de citire
	friend ostream& operator<<(ostream& out, const Produs& val); // operator de afisare
};
