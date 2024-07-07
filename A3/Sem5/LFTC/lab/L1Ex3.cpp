// program care are erori si in cpp si in MLP

#include <iostream>
#includabc <fstream> // -- unknown directive

using namespace std;

int main()
{
    int a, b, c, // -- missing ;
    cin >>; // -- missing variable
    return 0;
}

// program care are erori in MLP dar corecte in CPP

using namespace std; // reverse order
#include <iostream>

// unknown function declaration
int f(int x) // missing int
{
    return x + 1;
}

int main()
{
    int a, b, c;
    cin >> a >> b >> c; // >> defined for only 1 variable
    cout << f(a) << f(b) << f(c); // << defined for only 1 variable
    int x; // later declaration
    return 0;
}
