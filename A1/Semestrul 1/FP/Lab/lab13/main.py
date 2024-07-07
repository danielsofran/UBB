dim = int(input("Introduceti n: "))
has_prop = [True]  # vector caracteristic determinand daca secvanta pana la indexul dat din lista are sau nu proprietatea extra data
pus = [False for i in range(dim+1)]  # vector caracteristic care retine daca un element a fost pus in lista sau nu
number = 0

# domeniu: multimea range(1, n)
# solutie candidat:
# lista = (elem 0, elem 1, ..., elem m-1), elem i apartine range(1, m), iar i apartine range(0, m-1), m<= n
# solutie consistent
# lista definita anterior de lg n e consistenta daca elementele sunt diferite 2 cate 2 si
# pentru orice pozitie i intre 1 si n-1, exista un element pe pozitia j, -1< j < i+1, lista precedenta lista' = (elem 0, elem 1, ..., elem 2),
# astfel incat modulul diferentei dintre elementul de pe pozitia i si cel de pe pozitia j este egal cu 1

def last_has_prop(lista):  # verifica daca exista vreun element care are diferenta in modul 1 fata de orice element anterior
    if len(lista) == 1:
        return True
    for elem in lista[:-1]:
        if abs(elem-lista[-1]) == 1:
            return True
    return False

def backRec(lista):
    global has_prop, pus, dim, number
    lista.append(0)
    has_prop.append(0)
    for i in range(1, dim+1):  # alegem candidatul
        if not pus[i]:  # daca elementul nu mai apare in solutie
            lista[-1] = i  # adaugam candidatul la solutie
            pus[i] = True  # il marcam ca fiind pus in solutie pentru a nu fi readaugat
            has_prop[-1] = has_prop[-2] and last_has_prop(lista)  # verificam daca respecta proprietatea suplimentara
            if has_prop[-1]:  # posibila solutie
                if len(lista)==dim:  # daca e solutie
                    print(lista)  # afisam solutia
                    number += 1  # numarul de solutii, pentru verificare
                else: backRec(lista[:])
            pus[i] = False  # il marcam ca fiind "scos" din solutie

def consistent(lista):  # functia care verifica daca multimea obtinuta este o consistenta
    for elem in lista:
        if lista.count(elem) > 1:
            return False
    for i in range(1, len(lista)):
        for j in range(0, i):
            if abs(lista[i]-lista[j])==1:
                break
        else: return False
    return True


def backIter():
    global dim
    lista = [0]  # candidate solution
    while len(lista) > 0:
        choosed = False
        while not choosed and lista[-1] < dim:
            lista[-1] = lista[-1] + 1  # increase the last component
            choosed = consistent(lista)
        if choosed:
            if len(lista) == dim:
                print(lista)
            lista.append(0)  # expand candidate solution
        else:
            lista = lista[:-1]  # go back one component

backIter()
