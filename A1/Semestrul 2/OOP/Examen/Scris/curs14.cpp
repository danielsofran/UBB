#include <string>
#include <vector>
#include <sstream>
#include <algorithm>
#include "cassert"
#include "iostream"
#include "memory"

using namespace std;

class Payable{
private:
    string ID;
public:
    Payable(string id) : ID(id){}
    string getID() const { return ID; }
    virtual double monthlyIncome() const = 0;
    string toString() const;
    virtual ~Payable(){}
};

string Payable::toString() const{
    ostringstream out;
    out<<ID<<" "<<monthlyIncome();
    return out.str();
}

class Student : public Payable{
private:
    double scholarship;
public:
    Student(string id, double ss) : Payable{id}, scholarship{ss}{}
    double monthlyIncome() const override { return scholarship; }
};

class Teacher : public Payable{
private:
    double salary;
public:
    Teacher(string id, double Salary) : Payable(id), salary(Salary){}
    double monthlyIncome() const override { return salary; }
};

class DuplicatedIDException{
private:
    string msg;
public:
    DuplicatedIDException(string Msg) : msg(Msg){}
    string getMessage() const {return msg; }
};

class University{
private:
    string name;
    vector<Payable*> v;
public:
    University(string Name) : name(Name){}
    Payable& findPayableByID(string id)
    {
        for(const auto& ptr : v)
            if(ptr->getID() == id)
                return *ptr;
    }
    void addPayable(Payable& payable) {
        auto it = find_if(v.begin(), v.end(), [&](Payable* p){ return p->getID() == payable.getID();});
        if(it == v.end()) throw DuplicatedIDException("ID Duplicat");
        v.push_back(&payable);
    }
    const vector<Payable>& getAllPayables() const { return v; }
    double totalAmountToPay(){
        double rez=0;
        for(const auto& p : v)
            rez+=p->monthlyIncome();
        return rez;
    }
};

int main(){
    University UBB("UBB");
    Student s1("1", 2000);
    Student s2("2", 1000);
    Student s3("3", 3000);
    Teacher t1("4", 5000);
    Teacher t2("5", 6000);
    Teacher t3("6", 7000);
    UBB.addPayable(s1);
    UBB.addPayable(s2);
    try{UBB.addPayable(s1); assert(false);}
    catch(DuplicatedIDException&){}
    UBB.addPayable(t1);
    UBB.addPayable(t2);
    for(const auto& el : UBB.getAllPayables())
        cout<<el.toString()<<'\n';
    cout<<"ST "<<UBB.totalAmountToPay();
    return 0;
}


