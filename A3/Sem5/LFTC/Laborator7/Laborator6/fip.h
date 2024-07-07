#pragma once
#include <string>
#include <vector>
using namespace std;

class FIP {
private:
	vector<pair<int, int>> listFIP;

public:
	FIP() = default;

	void addEntry(int atomCode, int indexTS);

	string toString();
};
