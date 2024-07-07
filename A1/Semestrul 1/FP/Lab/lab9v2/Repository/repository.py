from Exceptii.exceptions import RepositoryError, DuplicatedIDError

class Repository: # clasa de baza pt repository
    def __init__(self, tip):
        """
        construieste un repository ca lista de obiecte de tipul tip
        :param tip: clasa/tip de date
        """
        self._tip = tip
        self._container = []

    def __len__(self): # numarul de obiecte stocate
        return len(self._container)

    def get_all(self): # obectele stocate
        return self._container[:]

    def cauta_id(self, id): # cautare dupa id
        for elem in self._container:
            if elem.id == id:
                return elem
        raise RepositoryError("Elementul cu id-ul dat nu a fost gasit!")

    def adauga(self, obj): # adaugare
        if not isinstance(obj, self._tip):
            raise TypeError("Tip gresit la adaugare in repository!")
        for elem in self._container:
            if elem == obj:
                raise DuplicatedIDError("Id duplicat!")
        self._container.append(obj)

    def stergere(self, id): # stergere
        for elem in self._container:
            if elem.id == id:
                self._container.remove(elem)
                break
        else:
            raise RepositoryError("Elementul cu acest id nu a fost gasit!")

    def modificare(self, id, obj): # modificare dupa id
        gasit = False
        for i in range(len(self._container)):
            if self._container[i].id == id:
                self._container[i] = obj
                gasit = True
            elif self._container[i].id == obj.id:
                raise DuplicatedIDError("Id duplicat la modificare!")
        if not gasit: raise RepositoryError("Elementul nu poate fi gasit!")

class FileRepository(Repository): # repository asociat unui fisier
    def __init__(self, tip, file, number_of_lines): # constructor
        """
        creeaza un repository asociat cu un fisier dat
        :param tip: clasa/tip de date
        :param file: numele fisierului
        """
        Repository.__init__(self, tip)
        self.__file = file
        self.__number_of_lines = number_of_lines

    def _read_from_file(self): # citire din fisier
        with open(self.__file, 'r') as f:
            self._container = []
            lines = f.readlines()
            while len(lines)>0:
                sir = ""
                for i in range(self.__number_of_lines):
                    sir += lines[0]
                    lines.pop(0)
                obj = self._tip.fromStr(sir)
                self._container.append(obj)

    def _write_to_file(self): # scriere in fisier
        with open(self.__file, 'w') as f:
            for elem in self._container:
                f.write(str(elem)+"\n")

    def __len__(self): # numarul de obiecte din fisier
        self._read_from_file()
        return Repository.__len__(self)

    def get_all(self): # obiectele din fisier
        self._read_from_file()
        return Repository.get_all(self)

    def cauta_id(self, id): # cautare dupa id in fisier
        self._read_from_file()
        return Repository.cauta_id(self, id)

    def adauga(self, obj):  # adaugare obiect in memorie si fisier
        self._read_from_file()
        super(FileRepository, self).adauga(obj)
        self._write_to_file()

    def stergere(self, id): # stergere obiect din memorie si fisier
        self._read_from_file()
        super(FileRepository, self).stergere(id)
        self._write_to_file()

    def modificare(self, id, obj): # modificare obiect in memorie si fisier
        self._read_from_file()
        super(FileRepository, self).modificare(id, obj)
        self._write_to_file()
