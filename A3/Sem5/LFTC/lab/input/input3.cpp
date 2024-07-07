#include <iostream>
using namespace std;

int main() {
    int n, s, x;
    cin >> n;
    s = 0;
    while(n != 0) {
        cin >> x;
        s = s + x;
        n = n - 1;
    }
    cout << s;
    return 0;
}