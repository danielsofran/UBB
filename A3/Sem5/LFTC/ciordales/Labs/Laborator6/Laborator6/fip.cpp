#include "fip.h"

void FIP::addEntry(int atomCode, int indexTS) {
	listFIP.push_back({ atomCode, indexTS });
}

string FIP::toString() {
    string text = "";
    for (const pair<int,int>& itemFIP : listFIP) {
        string line = "" + to_string(itemFIP.first) + " " + to_string(itemFIP.second) + "\n";
        text = text + line;
    }
    return text;
}