//2 b (0.5p)

#include "bits/stdc++.h"
using namespace std;

#include <vector>
#include <iostream>
using namespace std;
class A {
public:
    A() {cout<<"A()";}
    virtual void f() {cout << "f din A";}
};
class B:public A{
public:
    B() {cout<<"B()"; }
    void f() override {
        cout << "f din B";
    }
};
class C :public B {
public:
    C() {cout<<"C()"; }
    void f() override {
        cout << "f din C";
    }
};
int main() {
    vector<A> v;
    B b; cout<<endl;
    v.push_back(b);
    C c; cout<<endl;
    v.push_back(c);
    for (auto& e : v) { e.f(); }
    return 0;
}