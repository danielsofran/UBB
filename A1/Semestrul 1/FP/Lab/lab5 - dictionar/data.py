"""
    modul in care se declara clasele care stocheaza datele
    - Cheltuiala
    - Cheltuieli
    - Istoric
"""

class Cheltuiala:
    '''
        Cheltuiala - tipul de date care retine datele unei cheltuieli
        zi : intreg - reprezinta numarul zilei din luna
        suma : numar real - reprezinta suma cheltuita
        tip : tipul cheltuielii
        tipcheltuieli : lista cu categoriile posibile de cheltuieli
    '''

    tipcheltuilei = ['mancare', 'intretinere', 'imbracaminte', 'telefon', 'altele'] # categoriile posibile de cheltuieli

    def __init__(self, zi, suma, tip): # constructor
        t = self.convert(zi, suma, tip)
        zi, suma, tip = t
        self.verify_data(zi, suma, tip)
        self.zi = zi
        self.suma = suma
        self.tip = tip

    def __eq__(self, other): # verifica egalitatea dinre doua cheltuieli
        epsilon = 0.001
        try: return self.zi == other.zi and self.suma - other.suma < epsilon and self.tip == other.tip
        except Exception as ex:
            raise Exception("Eroare la inegalitate")
            return False

    def actualizare(self, zi, suma, tip): # functie care actualizeaza datele despre cheltuiala
        t = self.convert(zi, suma, tip)
        zi, suma, tip = t
        self.verify_data(zi, suma, tip)
        self.zi = zi
        self.suma = suma
        self.tip = tip

    def verify_data(self, zi, suma, tip):
        '''
            verifica daca datele sunt valide
            :raise: ValueError
        '''
        ex = ""  # string de exceptie
        if zi < 1 or zi > 31:
            ex += "Ziua trebuie sa fie cuprinsa intre 1 si 31!\n"
        if suma <= 0:
            ex += "Suma nu poate fi negativa sau nula!\n"
        else:
            ok = True
            try:
                str(suma).index('.')
            except ValueError:
                ok = False
            if ok and len(str(suma).rsplit('.')[-1]) > 2:
                ex += "Suma are mai mult de 2 zecimale!\n"
        try:
            assert Cheltuiala.tipcheltuilei.index(tip) >= 0
        except Exception:
            ex += "Acest tip de cheltuieli nu exista!\n"
        if ex != "": raise ValueError(ex)

    def convert(self, zi, suma, tip):
        '''
            incearca sa converteasca valorile date la tipurile membrilor clasei
            :raise: TypeError daca conversia este imposibila
            :return: un tuple de tip (zi, suma, an), in care valorile au fost convertite
        '''
        try:
            epsilon = 0.0000001
            if isinstance(zi, float) and zi-int(zi)>epsilon: raise Exception
            zi = int(zi)
        except Exception: raise TypeError("Ziua trebuie sa fie un numar intreg!\n")
        try:
            suma = float(suma)
        except Exception: raise TypeError("Suma trebuie sa fie un numar intreg sau real!\n")
        if not isinstance(tip, str):
            raise TypeError("Tipul trebuie sa fie un cuvant!\n")
        return (zi, suma, tip)

    def __str__(self): # conversie la sir de caractere
        s = self.suma
        epsilon = 0.0000001
        if s-int(s)<epsilon:
            s = int(s)
        return f"{self.zi} {s} {self.tip}"

    def show(self): # convertirea la un sir inteligibil dpdv al utilizatorului
        s = self.suma
        epsilon = 0.0000001
        if s - int(s) < epsilon:
            s = int(s)
        return f"Cheltuiala in ziua: {str(self.zi)}, suma: {str(s)}, tipul: {str(self.tip)}"

class Cheltuieli:
    '''
        lista - lista de cheltuileli
        dictionar - dictionarul de cheltuieli
        istoric - istoricul cheltuielilor
    '''
    def __init__(self, *params): # adauga parametrii de tip Cheltuiala si liste de cheltuieli, ignorandu-i pe ceilalti
        self.lista = []
        self.dictionar = dict()
        index = 0
        for param in params:
            if isinstance(param, list) or isinstance(param, tuple):
                for elem in param:
                    if isinstance(elem, Cheltuiala):
                        self.lista.append(elem)
                        self.dictionar[index] = elem
                        index = index + 1
            elif isinstance(param, Cheltuiala):
                self.lista.append(param)
                self.dictionar[index] = param
                index = index + 1
        self.istoric = Istoric()

    def __len__(self): # determina numarul de cheltuieli
        return len(self.dictionar.values())
        #return len(self.lista)

    def __iter__(self):# face interabila clasa
        return iter(self.dictionar.values())

    def index(self, value: Cheltuiala): # gaseste primul index al valorii date in lista, sau -1 daca nu este gasita
        for i in range(len(self.dictionar)):
            if self.dictionar[i] == value:
                return i
        return -1
        """for i in range(len(self.lista)):
            if self.lista[i] == value:
                return i
        return -1"""

    def __add__(self, other): # adauga o cheltuiala in lista
        if isinstance(other, Cheltuiala):
            self.lista.append(other)
            self.dictionar[len(self.dictionar)] = other
            return self
        elif isinstance(other, (Cheltuieli, list, tuple)): # sau mai multe
            for c in other:
                if isinstance(c, Cheltuiala):
                    self.lista.append(c)
                    self.dictionar[len(self.dictionar)] = c
            return self
        return self

    def append(self, other): # adauga o cheltuiala in lista
        if isinstance(other, Cheltuiala):
            self.lista.append(other)
            self.dictionar[len(self.dictionar)] = other
        elif isinstance(other, (Cheltuieli, list, tuple)): # sau mai multe
            for c in other:
                if isinstance(c, Cheltuiala):
                    self.lista.append(c)
                    self.dictionar[len(self.dictionar)] = c

    def removeAt(self, index: int): # sterge din lista elementul cu indexul index
        self.lista.remove(self.lista[index])
        self.dictionar.pop(index)
    def remove(self, value: Cheltuiala): # sterge din lista prima aparitie a valorii date
        self.lista.remove(value)
        keys = []
        for key, val in self.dictionar.items():
            if val == value:
                keys.append(key)
        for key in keys:
            self.dictionar.pop(key)

    def __getitem__(self, item: int): # indexator
        return self.dictionar[item]
        #if item < len(self):
        #    return self.lista[item]
        #return None

    def __setitem__(self, item: int, value: Cheltuiala): # indexator
        if item <len(self):
            self.lista[item].actualizare(value.zi, value.suma, value.tip)
            self.dictionar[item].actualizare(value.zi, value.suma, value.tip)

    def __eq__(self, other): # pentru a testa mai usor, verifica egalitatea elementelor si ordinea lor
        if isinstance(other, Cheltuieli):
            if len(other.dictionar) != len(self.dictionar): return False
            for i in range(len(self.dictionar)):
                if self.dictionar[i] != other[i]:
                    return False
            return True
            #return self.lista == other.lista
        elif isinstance(other, list):
            for i in range(len(other)):
                if self.dictionar[i] != other[i]:
                    return False
            return True
        return False

    def where(self, **kvargs):
        '''
        returneaza cheltuielile care respecta o anumita UNICA proprietate
        :param kvargs: dictionar de forma (proprietate: valoare),
                       proprietatea - cea a unei cheltuieli, adica ziua, suma sau tipul
                                    - function
                       valoarea - un literal specific
                                - functia : poate fi o functie booleana de tip predicat
        :return: o lista de cheltuieli care respecta o anumita proprietate - prin functie sau daca au o valoare a unui membru
        :rtype: list
        '''
        rez = Cheltuieli()
        for key, value in kvargs.items():
            if (key=='zi' or key=='ziua') and isinstance(value, int):
                for c in self.dictionar.values():
                    if c.zi == value:
                        rez+=c
            elif key=='suma' and (isinstance(value, int) or isinstance(value, float)):
                for c in self.dictionar.values():
                    epsilon = 0.0000001
                    if abs(c.suma - value)<epsilon:
                        rez+=c
            elif key=='tip' and isinstance(value, str):
                for c in self.dictionar.values():
                    if c.tip == value:
                        rez+=c
            elif key=='function' and str(type(value)) == "<class 'function'>":
                for c in self.dictionar.values():
                    if (value(c)):
                        rez += c
                return rez
        return rez

    def __str__(self): # conversia la sir de caractere, scop in testare
        rez=""
        for c in self.dictionar.values():
            rez += str(c) + "*"
        return rez

    def undo(self): # reface ultima adaugare/stergere/actualizare
        s = self.istoric.undo() # obtinem stringul ce trebuie sa il efectuam

class Istoric:
    '''
        memoreaza activitatile efectuate
        tasklist - lista activitatilor efectuate
                 - este o lista de siruri de caractere de forma "+ 1 1 altele*+ 10 20 mancare*- 1 1 altele"
                 - + inseamna adaugare
                 - - inseamna stergere
                 - ~ inseamna actualizare
    '''
    def __init__(self): # constructor
        self.tasklist = []
        #self.index = 0

    def __add__(self, other): # adaugarea unei activitati
        #self.index = self.index + 1
        if isinstance(other, str): # sir dat
            self.tasklist.append(other)
        elif isinstance(other, Cheltuiala): # o cheltuiala
            self.tasklist.append("+ " + str(other))
        elif isinstance(other, Cheltuieli): # mai multe cheltuieli
            s = ""
            for c in other: # parcurg cheltuielile
                s += "+ " + str(c) + "*"
            self.tasklist.append(s)
        else: raise Exception("Eroare la adaugare!")
        return self

    def __sub__(self, other): # stergerea unei activitati
        #self.index = self.index + 1
        if isinstance(other, str): # sir dat
            self.tasklist.append(other)
        elif isinstance(other, Cheltuiala): # o cheltuiala
            self.tasklist.append("- " + str(other))
        elif isinstance(other, Cheltuieli): # mai multe cheltuieli
            s = ""
            for c in other: # parcurg cheltuielile
                s += "- " + str(c) + "*"
            s[-1] = ""
            print(s)
            self.tasklist.append(s)
        else: raise Exception("Eroare la stergere!")
        return self

    def append(self, type: str, other): # adauga un task in lista
        if isinstance(other, str): # sir dat
            self.tasklist.append(type + " " + other)
        elif isinstance(other, Cheltuiala): # o cheltuiala
            self.tasklist.append(type + " " + str(other))
        elif isinstance(other, Cheltuieli): # mai multe cheltuieli
            s = ""
            for c in other: # parcurg cheltuielile
                s += type + " " + str(c) + "*"
            s[-1] = ""
            self.tasklist.append(s)
        else: raise Exception("Eroare la append!")

    def __len__(self): # numarul de activitati memorate
        return len(self.tasklist)

    def undo(self):
        '''
        va determina operatiile inverse ultimei operatii efectuate
        + zi suma tip -> - zi suma tip
        - zi suma tip -> + zi suma tip
        ~ zi1 suma1 tip1*zi2 suma2 tip2 -> ~ zi2 suma2 tip2*zi1 suma1 tip1
        :return: lista operatiilor care se executa pentru ca operatia sa fie corecta
        :raise: IndexError daca nu exista task-uri in lista
        :rtype: str
        '''
        if len(self.tasklist) == 0:
            raise IndexError("Nu exista cheltuieli in lista! Operatia de Undo nu se poate efectua!")
        if self.tasklist[-1][0] != '~':
            ops = list(self.tasklist[-1]) # ultimele operatii efectuate, ca lista de caractere
            for i in range(len(ops)):
                if ops[i] == '+': ops[i] = '-'
                elif ops[i] == '-': ops[i] = '+'
            del self.tasklist[-1]         # stergem task-ul din lista, deoarece acesta se va efectua invers
            return "".join(ops)
        else:
            s = self.tasklist[-1]
            si = s.index('*')
            c1 = s[2:si]
            c2 = s[si+1:]
            del self.tasklist[-1]         # stergem task-ul din lista, deoarece acesta se va efectua invers
            return f"~ {c2}*{c1}"




