#pragma once

#include "repository.h"
#include "memory"

using std::unique_ptr;


class ActiuneUndo{
public:
    virtual void doUndo() = 0;
};

class UndoAdauga : public ActiuneUndo{
    FileRepository& repo;
    Carte c;
public:
    UndoAdauga(FileRepository& r, const Carte& carte) : repo{r}, c{carte} {}
    void doUndo() override{
        repo.remove(c);
    }
};

class UndoSterge : public ActiuneUndo{
    FileRepository& repo;
    Carte c;
public:
    UndoSterge(FileRepository& r, const Carte& carte) : repo{r}, c{carte} {}
    void doUndo() override{
        repo.add(c);
    }
};

class UndoModifica : public ActiuneUndo{
    FileRepository& repo;
    Carte c1, c2;
public:
    UndoModifica(FileRepository& r, const Carte& carte1, const Carte& carte2) : repo{r}, c1{carte1}, c2{carte2} {}
    void doUndo() override{
        repo.modify(c2, c1);
    }
};

class Service{
private:
    FileRepository& repo;
    vector<unique_ptr<ActiuneUndo>> undolist;
public:
    explicit Service(FileRepository& r) : repo(r) {} // constructor

    /** creez elementul cu datele date
     * adaug in repository
     * arunc ServiceException daca apare o eroare
     */
	void add(string titlu, string autor, string gen, int anap){
		Carte elem{std::move(titlu), std::move(autor), std::move(gen), anap};
		try {
            repo.add(elem);
            undolist.push_back(std::make_unique<UndoAdauga>(repo, elem));
        }
		catch(RepoException& re) {
			throw ServiceException(re.getMessage());
		}
	}

    /** creez elementul cu datele date
     * sterg din repository elementul dat
     * arunc ServiceException daca apare o eroare
     */
	void remove(string titlu, string autor, string gen, int anap){
		Carte elem{std::move(titlu), std::move(autor), std::move(gen), anap};
		try {
            repo.remove(elem);
            undolist.push_back(std::make_unique<UndoSterge>(repo, elem));
        }
		catch(RepoException& re) {
			throw ServiceException(re.getMessage());
		}
	}

    /** creez cele 2 elemente cu datele date
     * daca primul nu exista arunc ServiceException
     * altfel modific valoarea primului element
     */
	void modify(string titlu1, string autor1, string gen1, int anap1, string titlu2, string autor2, string gen2, int anap2){
		Carte elem1{std::move(titlu1), std::move(autor1), std::move(gen1), anap1};
		Carte elem2{std::move(titlu2), std::move(autor2), std::move(gen2), anap2};
		try {
            repo.modify(elem1, elem2);
            undolist.push_back(std::make_unique<UndoModifica>(repo, elem1, elem2));
        }
		catch(RepoException& re) {
			throw ServiceException(re.getMessage());
		}
	}

    /** creez elementul cu datele date
     * returnez adevarat daca exista in service sau false altfel
     */
	bool contains(string titlu, string autor, string gen, int anap){
		Carte elem{std::move(titlu), std::move(autor), std::move(gen), anap};
		return repo.find(elem);
	}

    void sortTitlu() // sortez elem dupa titlu
    {
        auto lambda = [](const Carte& c1, const Carte& c2) {return c1.getTitlu() < c2.getTitlu(); };
        repo.sort(lambda);
    }

    void sortAutor() // sortez elem dupa autor
    {
        auto lambda = [](const Carte& c1, const Carte& c2) {return c1.getAutor() < c2.getAutor(); };
        repo.sort(lambda);
    }

    void sortAnapGen() // sortez elem dupa anul aparitiei si gen
    {
        auto lambda = [](const Carte& c1, const Carte& c2) {
            return c1.getAnap() < c2.getAnap() ||
                    c1.getAnap() == c2.getAnap() && c1.getGen() < c2.getGen();
        };
        repo.sort(lambda);
    }

    vector<Carte> filtruTitlu(const string& titlu)
    {
        vector<Carte> rez;
        for (const Carte& c : repo)
            if(c.getTitlu() == titlu)
                rez.push_back(c);
        return rez;
    }

    vector<Carte> filtruAnAp(const string& anap)
    {
        int an;
        try{ an = std::stoi(anap);}
        catch(...) {throw ServiceException("An invalid!\n"); }
        vector<Carte> rez;
        for (const Carte& c : repo)
            if(c.getAnap() == an)
                rez.push_back(c);
        return rez;
    }

    void undo(){
        if(undolist.empty()) throw ServiceException("Actiune imposibila!\n");
        undolist.back()->doUndo();
        undolist.pop_back();
    }

    FileRepository& getAll() { return repo; }
};
