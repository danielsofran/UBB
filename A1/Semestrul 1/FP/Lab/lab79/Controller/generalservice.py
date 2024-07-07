class ServiceCRUD:  # clasa generica service
    def __init__(self, type, repo, validator, singular, plural):  # constructor
        self._type = type
        self._repo = repo()
        self._validator = validator
        self._msg = ""
        self._singular = singular
        self._plural = plural

    @property
    def type(self):
        return self._type  # getter pt tip

    @type.setter
    def type(self, val):
        self._type = val  # setter pt tip

    @property
    def repo(self):
        return self._repo  # getter la repository

    @repo.setter
    def repo(self, val):
        self._repo = val  # setter la repository

    @property
    def Validator(self):
        return self._validator  # getter la Validator

    @Validator.setter
    def Validator(self, val):
        self._validator = val  # setter la validator

    @property
    def msg(self):
        return self._msg  # getter pt mesajul rezultat

    @msg.setter
    def msg(self, txt):
        self._msg = txt  # setter pt mesajul rezultat

    @property
    def singular(self):
        return self._singular  # getter la singular (numele obiectului)

    @singular.setter
    def singular(self, val: str):
        self._singular = val  # setter la singular (numele obiectului)

    @property
    def plural(self):
        return self._plural  # getter la plural (numele unei colectii de obiecte)

    @plural.setter
    def plural(self, val: str):
        self._plural = val  # setter la plural (numele unei colectii de obiecte)

    def __str__(self):
        return self.msg

    def __len__(self):
        return len(self.repo)

    def __showDict(self, **kwargs):  # afiseaza dictionarul de proprietati a obiectului/cautarii/stergerii
        rez = ""
        for key, value in kwargs.items():
            rez += key + " " + str(value) + ", "
        return rez[:-2]

    def adauga(self, other):  # adauga un iterabil in repository
        try:
            self.Validator.iterable(other)
            other = self.type.fromIterable(other)
        except Exception as ex:
            self.msg = ex.args[0]
            return
        for elem in self.repo:
            if elem.id == other.id:
                self.msg = f"Id {other.id} duplicat in lista de {self.plural}!"
                break
        else:
            self.repo.append(other)
            self.msg = f"{self.singular[0].upper()}{self.singular[1:]} {other.show()} s-a adaugat cu success!"
            return other

    def sterge(self, **kwargs):  # sterge elementele cu proprietatile date
        if len(kwargs) == 0:
            self.msg = f"Imposibil de sters {self.singular}ul cu {self.__showDict(**kwargs)} din lista din cauza faptului ca nu a fost adaugat!"
            return
        rez = self.repo.where(**kwargs)
        if len(rez) == 0:
            self.msg = f"Nu exista {self.plural} care sa respecte criteriile date!\n{self.repo}"
            return
        self.msg = f"{self.plural[0].upper()}{self.plural[1:]} "
        for elem in rez:
            self.repo.l.remove(elem)
            self.msg += elem.show() + " "
        self.msg += "s-au sters cu succes!"
        return rez

    def modificare(self, initial, final):  # inlocuieste iterabilul initial cu cel final
        try:
            self.Validator.iterable(initial)
            initial = self.type.fromIterable(initial)
        except Exception as ex:
            self.msg = ex.args[0]
            return
        try:
            self.Validator.iterable(final)
            final = self.type.fromIterable(final)
        except Exception as ex:
            self.msg = ex.args[0]
            return
        if not initial in self.repo:
            self.msg = f"Imposibil de actualizat {self.singular}ul {initial.show()} din lista din cauza faptului ca nu a fost adaugat!"
        elif str(initial) == str(final):
            self.msg = f"Cele doua elemente de tip {self.singular} coincid!"
        else:
            self.msg = f"{self.singular[0].upper()}{self.singular[1:]} {initial.show()} s-a modificat cu success!"
            self.repo[initial] = final
            return initial, self.repo[final]

    def vizualizare(self):  # afiseaza toate filmele din repository
        if len(self.repo) == 0:
            self.msg = f"Nu exista {self.plural} in lista!"
        else:
            self.msg = f"Lista de {self.plural[0].upper()}{self.plural[1:]}:\n" + str(self.repo)

    def cautare(self, **kwargs):  # cauta dupa niste criterii in repository
        if len(kwargs) == 0:
            self.msg = "Nu ati introdus criterii de cautare!"
            return None
        else:
            rez = self.repo.where(**kwargs)
            if len(rez) == 0:
                self.msg = f"Nu exista {self.plural} care sa respecte criteriile date!"
                return
            else:
                self.msg = f"Rezultate cautare {self.plural}:\n" + str(rez)
