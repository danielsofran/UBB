//
// Created by Daniel on 07.06.2022.
//

#ifndef MODEL2_SERVICE_H
#define MODEL2_SERVICE_H

#include "repository.h"
#include "UI/observer.h"
#include "algorithm"

class Service : public Observable{
private:
    Repository& r;
public:
    Service(Repository &r); // constructor cu repo ca param

    /**
     * modifica titlul si rankul melodiei cu id-ul id
     * @param id-ul melodiei
     * @param titlu-ul melodiei
     * @param rank-ul melodiei
     *
     * @throw ServiceException, daca nu exista
     */
    void modify(int id, string titlu, int rank);

    /**
     * sterge melodia cu id ul id
     * @param id
     * @throw ServiceException daca nu exista
     * @throw ServiceException daca e ultima melodia a artistului, si nu o sterge
     */
    void sterge(int id);
    /**
     *
     * @param rank
     * @return nr de melodii cu rank ul dat
     */
    int nrMelodiiRank(int rank) const;
    const vector<Melodie>& getAll(); // returneaza toate melodiile
    vector<Melodie> getAllSorted() const; // returneaza toate melodiile sortate dupa rank(fisieul nu se modifica)
};

#endif //MODEL2_SERVICE_H
