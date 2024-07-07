#include <iostream>
using namespace std;

// the declaration block is declared before of the main function against of our defined BNF model
int a, bz, rz; // the name 'bz', 'rz is not valid

int main() {

    cin >> a >> bz;

    while (bz != 0) {
        rz = a % bz; // the name 'rz' is not valid
        a = bz;
        bz = rz;
    }
    cout << "The cmmdc is: " << a;
    return 0;
}

