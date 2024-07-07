#include "domain.h"

Device::Device(string tip_, string model_, int an_, string culoare_, int pret_){
	tip = std::move(tip_);
	model = std::move(model_);
	an = an_;
	culoare = std::move(culoare_);
	pret = pret_;
}

const string& Device::getTip() const {
	return tip;
}

const string& Device::getModel() const {
	return model;
}

const int& Device::getAn() const {
	return an;
}

const string& Device::getCuloare() const {
	return culoare;
}

const int& Device::getPret() const {
	return pret;
}

bool operator==(const Device& val1, const Device& val2){
	return val1.tip == val2.tip && val1.model == val2.model && val1.an == val2.an && val1.culoare == val2.culoare && val1.pret == val2.pret;
}

istream& operator>>(istream& in, Device& val){
	char delim=',';
	string token;
	getline(in, token, delim);
	istringstream sin1(token);
	sin1>>std::noskipws>>val.tip;
	getline(in, token, delim);
	istringstream sin2(token);
	sin2>>std::noskipws>>val.model;
	getline(in, token, delim);
	istringstream sin3(token);
	sin3>>std::noskipws>>val.an;
	getline(in, token, delim);
	istringstream sin4(token);
	sin4>>std::noskipws>>val.culoare;
	getline(in, token, delim);
	istringstream sin5(token);
	sin5>>std::noskipws>>val.pret;
	return in;
}

ostream& operator<<(ostream& out, const Device& val){
	char delim=',';
	out<<val.tip<<delim;
	out<<val.model<<delim;
	out<<val.an<<delim;
	out<<val.culoare<<delim;
	out<<val.pret<<delim;
	return out;
}

