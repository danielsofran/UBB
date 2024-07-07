#include <iostream>
using  namespace std;

int main() {
	int n, a;
	cin >> a;
	cin >> n;

	int answer, reminder;
	answer = 1;
	while (n != 0) {
		reminder = n % 2;
		if (reminder == 1) {
			answer = answer * a;
		}
		a = a * a;
		n = n / 2;
	}
	cout << answer;
	return 0;
}