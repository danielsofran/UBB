#include <iostream>

using namespace std;

int main() {
    double r, pi, area, perimeter;
    pi = 3.14159;
    cin >> r;
    area = pi * r * r;
    perimeter = 2 * pi * r;
    cout << area;
    cout << perimeter;
    return 0;
}
