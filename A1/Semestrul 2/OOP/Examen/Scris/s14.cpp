//
// Created by Daniel on 05.06.2022.
//

#include "bits/stdc++.h"

using namespace std;

template<class T>
class Measurement{
private:
    T val;
public:
    Measurement(T v) : val(v){}
    Measurement& operator+(T elem){
        val+=elem;
        return *this;
    }
    bool operator<(const Measurement& m) const
    {
        return val < m.val;
    }
    T value() const {return val;}
};

int main() {
//creaza un vector de masuratori cu valorile (10,2,3)
    std::vector<Measurement<int>> v{ 10,2,3 };
    v[2] + 3 + 2; //aduna la masuratoarea 3 valuarea 5
    std::sort(v.begin(), v.end()); //sorteaza masuratorile
//tipareste masuratorile (in acest caz: 2,8,10)
    for (const auto& m : v) std::cout << m.value() << ",";
    return 0;
}