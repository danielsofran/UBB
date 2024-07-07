#pragma once

#include "repository.h"

class Service{
private:
    FileRepository& repo;
public:
    explicit Service(FileRepository& r) : repo(r) {} // constructor

    /** creez elementul cu datele date
     * adaug in repository
     * arunc ServiceException daca apare o eroare
     */
	void add(string nume, string tip, double pret, string producator){
		Produs elem{std::move(nume), std::move(tip), pret, std::move(producator)};
		try { repo.add(elem); }
		catch(RepoException& re) {
			throw ServiceException(re.getMessage());
		}
	}

    /** creez elementul cu datele date
     * sterg din repository elementul dat
     * arunc ServiceException daca apare o eroare
     */
	void remove(string nume, string tip, double pret, string producator){
		Produs elem{std::move(nume), std::move(tip), pret, std::move(producator)};
		try { repo.remove(elem); }
		catch(RepoException& re) {
			throw ServiceException(re.getMessage());
		}
	}

    /** creez cele 2 elemente cu datele date
     * daca primul nu exista arunc ServiceException
     * altfel modific valoarea primului element
     */
	void modify(string nume1, string tip1, double pret1, string producator1, string nume2, string tip2, double pret2, string producator2){
		Produs elem1{std::move(nume1), std::move(tip1), pret1, std::move(producator1)};
		Produs elem2{std::move(nume2), std::move(tip2), pret2, std::move(producator2)};
		try { repo.modify(elem1, elem2); }
		catch(RepoException& re) {
			throw ServiceException(re.getMessage());
		}
	}

    /** creez elementul cu datele date
     * returnez adevarat daca exista in service sau false altfel
     */
	bool contains(string nume, string tip, double pret, string producator){
		Produs elem{std::move(nume), std::move(tip), pret, std::move(producator)};
		return repo.find(elem);
	}

    FileRepository& getAll() { return repo; }
};
