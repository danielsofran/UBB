#include <iostream>
using  namespace std;

int main() {

	int n, i = 1;
	cin >> n;
    // 'do while' statement wasn t defined in BNF model
	do {
		if (n % i == 0) // nu este modulo in expresia nested in if
			cout << i << ' ' << n / i << '\n'; // nu poti face scriere multipla
		i++; // nu este ++ operator definit in MLP
	} while (i * i <= n);

	return 0;
}