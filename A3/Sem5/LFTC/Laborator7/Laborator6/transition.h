#pragma once
#include <string>
using namespace std;

class Transition {
private:
	string source;
	string destination;
	string weight;

public:
	Transition(const string& source, const string& destination, const string& weight) : source{ source }, destination{ destination },
		weight{ weight }{

	}

    string getSource() {
        return this->source;
    }

    void setSource(const string& source) {
        this->source = source;
    }

    string getDestination() {
        return this->destination;
    }

    void setDestination(const string& destination) {
        this->destination = destination;
    }

    string getWeight() {
        return this->weight;
    }

    void setWeight(const string& weight) {
        this->weight = weight;
    }

    string toString();
};
