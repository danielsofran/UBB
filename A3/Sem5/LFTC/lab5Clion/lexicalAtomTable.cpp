#include "lexicalAtomTable.h"


void LexicalAtomTable::addLexicalAtom(const string& lexicalAtom) {

	if (lexicalTable.find(lexicalAtom) == lexicalTable.end()) {
		lexicalTable[lexicalAtom] = this->increment;
        this->increment = this->increment + 1;
	}
}

string LexicalAtomTable::toString() {
    string text = "";
    map<int, string> hashMap;
    map<string, int>::iterator it;
    for (it=lexicalTable.begin();it!=lexicalTable.end();it++) {
        hashMap[it->second] = it->first;
    }

    for (int i = 0; i < lexicalTable.size(); i++) {
        string line = " " + hashMap[i] + " " + to_string(i) + "\n";
        text = text + line;
    }
    return text;
}