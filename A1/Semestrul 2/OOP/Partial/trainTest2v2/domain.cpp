#include "domain.h"

Carte::Carte(string titlu_, string autor_, string gen_, int anap_){
	titlu = std::move(titlu_);
	autor = std::move(autor_);
	gen = std::move(gen_);
	anap = anap_;
}

const string& Carte::getTitlu() const {
	return titlu;
}

const string& Carte::getAutor() const {
	return autor;
}

const string& Carte::getGen() const {
	return gen;
}

const int& Carte::getAnap() const {
	return anap;
}

bool operator==(const Carte& val1, const Carte& val2){
	return val1.titlu == val2.titlu && val1.autor == val2.autor && val1.gen == val2.gen && val1.anap == val2.anap;
}

istream& operator>>(istream& in, Carte& val){
	char delim=',';
	string token;
	getline(in, token, delim);
	istringstream sin1(token);
	sin1>>val.titlu;
	getline(in, token, delim);
	istringstream sin2(token);
	sin2>>val.autor;
	getline(in, token, delim);
	istringstream sin3(token);
	sin3>>val.gen;
	getline(in, token, delim);
	istringstream sin4(token);
	sin4>>val.anap;
	return in;
}

ostream& operator<<(ostream& out, const Carte& val){
	char delim=',';
	out<<val.titlu<<delim;
	out<<val.autor<<delim;
	out<<val.gen<<delim;
	out<<val.anap<<delim;
	return out;
}

