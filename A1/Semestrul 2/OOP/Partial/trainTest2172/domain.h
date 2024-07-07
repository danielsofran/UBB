#pragma once

#include <iostream>
#include <string>
#include <sstream>

using std::istream;
using std::ostream;
using std::string;
using std::istringstream;

class Device{
private:
	string tip;
	string model;
	int an = 0;
	string culoare;
	int pret = 0;
public:
	Device() = default; // constructor default
	Device(string tip_, string model_, int an_, string culoare_, int pret_); // constructor cu argumente
	Device(const Device&) = default; // constructor de copiere;
	Device& operator=(const Device&) = default; // operator de atribuire

	const string& getTip() const; // getter tip
	const string& getModel() const; // getter model
	const int& getAn() const; // getter an
	const string& getCuloare() const; // getter culoare
	const int& getPret() const; // getter pret

	friend bool operator==(const Device& val1, const Device& val2); // operator de egalitate
	friend bool operator!=(const Device& val1, const Device& val2) // operator de diferentiere 
	{
		 return !(val1==val2);
	}

	friend istream& operator>>(istream& in, Device& val); // operator de citire
	friend ostream& operator<<(ostream& out, const Device& val); // operator de afisare
};
