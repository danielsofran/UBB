#include <utility>

//
// Created by Daniel on 14.05.2022.
//

#ifndef UNTITLED_SERVICE_H
#define UNTITLED_SERVICE_H

class Service{
private:
    Repository& repo;
public:
    explicit Service(Repository& r) : repo(r) {}
    void add(string nrinmat, string prod, string model, string tip)
    {
        Masina m{nrinmat, prod, model, tip};
        if(valid(nrinmat) && valid(prod) && valid(model) && valid(tip))
            repo.add(m);
        else throw ServiceException("Masina Invalida!\n");
    }
    void remove(string nrinmat, string prod, string model, string tip)
    {
        Masina m{nrinmat, prod, model, tip};
        if(valid(nrinmat) && valid(prod) && valid(model) && valid(tip))
            repo.remove(m);
        else throw ServiceException("Masina Invalida!\n");
    }
    const Masina& find(string nrinmat)
    {
        if(!valid(nrinmat)) throw ServiceException("Masina Invalida!\n");
        return repo.find([&nrinmat](Masina& m){return m.getNrInmat() == nrinmat;});
    }
    void modify(string nrinmat1, string prod1, string model1, string tip1,
                string nrinmat, string prod, string model, string tip){
        if(valid(nrinmat) && valid(prod) && valid(model) && valid(tip) &&
                valid(nrinmat1) && valid(prod1) && valid(model1) && valid(tip1)) {
            Masina m2{std::move(nrinmat), std::move(prod), std::move(model), std::move(tip)};
            Masina m1{std::move(nrinmat1), std::move(prod1), std::move(model1), std::move(tip1)};
            repo.modify(m1, m2);
        } else throw ServiceException("Element invalid!\n");
    }
    Repository& getAll() { return repo; }
};

#endif //UNTITLED_SERVICE_H
