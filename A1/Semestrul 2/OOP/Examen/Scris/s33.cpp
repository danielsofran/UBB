//
// Created by Daniel on 05.06.2022.
//

#include "bits/stdc++.h"
using namespace std;

class Channel{
public:
    virtual void send(const string& msg) = 0;
    virtual ~Channel() =default;
};

class Telefon : public Channel{
private:
    int nrTel;
public:
    Telefon(int nr) : nrTel(nr){}
    void send(const string& msg) override{
        int r = rand() % 10;
        if(r<4) throw exception();
        cout<<"dail:"<<nrTel;
    }
};

class Fax : public Telefon{
public:
    Fax(int nr) : Telefon(nr){}
    void send(const string& msg) override{
        try{
            Telefon::send(msg);
            cout<<"sending fax\n";
        }
        catch(...){
            throw exception();
        }
    }
};

class SMS : public Telefon{
public:
    SMS(int nr) : Telefon(nr){}
    void send(const string& msg) override{
        try{
            Telefon::send(msg);
            cout<<"sending sms\n";
        }
        catch(...){
            throw exception();
        }
    }
};

class FailOver : public Channel{
private:
    Channel &c1, &c2;
public:
    FailOver(Channel& ch1, Channel& ch2) : c1(ch1), c2(ch2){}
    void send(const string& msg) override{
        try{c1.send(msg);}
        catch(...){
            try{c2.send(msg);}
            catch(...){}
        }
    }
};

class Contact{
private:
    Channel* c1, *c2, *c3;
public:
    Contact(Channel* ch1, Channel* ch2, Channel* ch3) : c1(ch1), c2(ch2), c3(ch3){}
    Contact(const Contact&) = default;
    //Contact& operator=(const Contact&) = default;
    void sendAlarm(const string& msg){
        while(true){
            try{c1->send(msg); break;}
            catch(...){}
            try{c2->send(msg); break;}
            catch(...){}
            try{c3->send(msg); break;}
            catch(...){}
        }
    }
    ~Contact() {
        delete c1;
        delete c2;
        delete c3;
    };
};

Contact* creeaza(){
    Telefon* c1 = new Telefon(12345);
    Fax* c2 = new Fax(334234);
    SMS* c3 = new SMS(76383468);
    Contact* c = new Contact(c1, c2, c3);
    return c;
}

int main(){
    srand(time(0));
    Contact* c = creeaza();
    c->sendAlarm("hi");
    delete c;
    return 0;
}