#include "domain.h"

Produs::Produs(string nume_, string tip_, double pret_, string producator_){
	nume = std::move(nume_);
	tip = std::move(tip_);
	pret = pret_;
	producator = std::move(producator_);
}

const string& Produs::getNume() const {
	return nume;
}

const string& Produs::getTip() const {
	return tip;
}

const double& Produs::getPret() const {
	return pret;
}

const string& Produs::getProducator() const {
	return producator;
}

bool operator==(const Produs& val1, const Produs& val2){
	return val1.nume == val2.nume && val1.tip == val2.tip && val1.pret == val2.pret && val1.producator == val2.producator;
}

istream& operator>>(istream& in, Produs& val){
	char delim=',';
	string token;
	getline(in, token, delim);
	istringstream sin1(token);
	sin1>>val.nume;
	getline(in, token, delim);
	istringstream sin2(token);
	sin2>>val.tip;
	getline(in, token, delim);
	istringstream sin3(token);
	sin3>>val.pret;
	getline(in, token, delim);
	istringstream sin4(token);
	sin4>>val.producator;
	return in;
}

ostream& operator<<(ostream& out, const Produs& val){
	char delim=',';
	out<<val.nume<<delim;
	out<<val.tip<<delim;
	out<<val.pret<<delim;
	out<<val.producator<<delim;
	return out;
}

