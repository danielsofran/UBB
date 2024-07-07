from Controller.errors import DuplicateIdError

class Repository: # clasa pentru reposity de tipul type
    def __init__(self, type): # constructor
        self._type = type
        self._file = None
        self._l = []

    @property
    def type(self): return self._type # getter pt tipul de reposity

    @property
    def l(self): return self._l # getter pentru lista respectiva reposity-ului

    @property
    def file(self): return self._file # getter pt numele fisierului
    @file.setter
    def file(self, val):
        self._file = val

    def __len__(self): # determina numeraul de elemente
        self.load()
        return len(self.l)

    def __iter__(self): # face reposity-ul iterabil
        self.load()
        return iter(self.l)

    def __bool__(self): # verificarea daca contine elemente
        self.load()
        return len(self) > 0

    def __contains__(self, item): # verificare apartenenta
        if not isinstance(item, self.type): raise TypeError(
            f"Imposibil de cautat elementul de tipul {type(item)} in reposity-ul de tipul {self.type} datorita diferentelor de tip!")
        self.load()
        return item in self.l

    def __getitem__(self, item): # indexator de tip get
        if isinstance(item, int):
            self.load()
            return self.l[item]
        elif isinstance(item, self.type):
            self.load()
            for i in range(len(self.l)):
                if self.l[i] == item:
                    return self.l[i]

    def __setitem__(self, key, value): # indexator de tip set
        if isinstance(key, int):
            self.load()
            self.l[key] = value
            self.save()
        elif isinstance(key, self.type):
            self.load()
            for i in range(len(self.l)):
                if self.l[i] == key:
                    self.l[i] = value
            self.save()

    def __eq__(self, other): # operator de egalitate care nu tine cont de ordinea elementelor
        try:
            if len(self) != len(other): return False
            self.load()
            for elem in other:
                if not elem in self:
                    return False
            for elem in self:
                if not elem in other:
                    return False
            return True
        except: return False

    def __str__(self):
        s = ""
        self.load()
        for elem in self:
            s+=str(elem)+"\n"
        return s[:-1]

    def append(self, other):
        '''
        adaugarea unui element in lista
        :param other: un alt elemet de tipul tip
        :raise: TypeError, in caz ca other nu are tipul type
                DuplicateIdError, in caz ca id-ul a mai fost adaugat odata in lista
        '''
        if not isinstance(other, self.type): raise TypeError(
            f"Imposibil de adaugat elementul de tipul {type(other)} in reposity-ul de tipul {self.type} datorita diferentelor de tip!")
        #for elem in self:
        #    if elem.id == other.id:
        #        raise DuplicateIdError
        self.load()
        self.l.append(other)
        self.save()

    def extend(self, other): # adauga succesiv elementele
        try:
            for elem in other: pass
        except: raise TypeError(f"Imposibil de parcurs elementele din {other}")
        self.load()
        for elem in other:
            self.append(elem)
        self.save()

    def remove(self, other): # stergerea unui element
        if not isinstance(other, self.type): raise TypeError(
            f"Imposibil de sters elementul de tipul {type(other)} in reposity-ul de tipul {self.type} datorita diferentelor de tip!")
        self.load()
        self.l.remove(other)
        self.save()

    def clear(self): # sterge intreaga lista
        self.l.clear()
        self.save()

    def index(self, other, start=0, stop=-1): # gaseste index-ul unui element
        if not isinstance(other, self.type): raise TypeError(
            f"Imposibil de cautat elementul de tipul {type(other)} in reposity-ul de tipul {self.type} datorita diferentelor de tip!")
        self.load()
        return self.l.index(other, start, stop)

    def pop(self):  # elimina ultimul element
        self.load()
        elem = self.l.pop()
        self.save()
        return elem

    def copy(self):  # returneaza o copie
        self.load()
        r = Repository(self.type)
        r._l = self.l[:]
        return r

    def sort(self, key=None, reverse=False):  # sorteaza lista
        self.load()
        self.l.sort(key=key, reverse=reverse)
        self.save()

    def where(self, **kwargs):  # cauta elemente in lista comform specificatiilor todo
        raise NotImplementedError

    def load(self):  # incarca elementele dintr-un fisier
        if self.file is None: return
        self.l.clear()
        with open(self.file) as f:
            for line in f:
                try:
                    self.append(self.type.fromStr(line))
                except: pass

    def save(self):  # salveaza elementele intr-un fisier
        if self.file is None: return
        l = self.l.copy()
        with open(self.file, "w") as f:
            self.l.clear()
            while len(l) > 0:
                f.write(l.pop().save())
                f.write('\n')
