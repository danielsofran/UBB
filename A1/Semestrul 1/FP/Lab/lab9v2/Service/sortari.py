from random import randint

def bubblesort(vector, key=lambda x: x, reverse=False):
    """
    sorteaza iterabilul cu metoda bulelor
    :param vector: iterabil
    :param key: functie de sortare
    :param reverse: de la mare la mic sau nu
    :return: lista sortata
    """
    rez = vector[:]
    sortat = False
    while not sortat:
        sortat = True
        for i in range(len(rez)-1):
            if not reverse:
                if key(rez[i]) > key (rez[i+1]):
                    sortat = False
                    rez[i], rez[i+1] = rez[i+1], rez[i]
            if reverse:
                if key(rez[i]) < key (rez[i+1]):
                    sortat = False
                    rez[i], rez[i+1] = rez[i+1], rez[i]
    return rez

def insertionsort(vector, key=lambda x: x, reverse=False):
    """
    sorteaza iterabilul prin metoda insertiei
    :param vector: iterabil
    :param key: functie de sortare
    :param reverse: de la mare la mic sau nu
    :return: lista sortata
    """
    rez = []
    for elem in vector:
        for i in range(len(rez)):
            if not reverse:
                if key(elem) < key(rez[i]):
                    rez.insert(i, elem)
                    break
            if reverse:
                if key(elem) > key(rez[i]):
                    rez.insert(i, elem)
                    break
        else:
            rez.append(elem)
    return rez

def interclasare(lista1, lista2, key=lambda x: x, reverse=False):
    """
    interclaseaza lista1 si lista2
    :param lista1: prima lista
    :param lista2: a doua lista
    :param key: cheia dupa care comparam elementele
    :param reverse: determina daca consideram elementele in ordine crescatoare sau nu
    :return: noua lista formata din prima lista si a doua
    """
    lista = []
    index1 = index2 = 0
    while index1 < len(lista1) and index2 < len(lista2):
        if not reverse:
            if key(lista1[index1]) <= key(lista2[index2]):
                lista.append(lista1[index1])
                index1 = index1 + 1
            else:
                lista.append(lista2[index2])
                index2 = index2 + 1
        else:
            if key(lista1[index1]) >= key(lista2[index2]):
                lista.append(lista1[index1])
                index1 = index1 + 1
            else:
                lista.append(lista2[index2])
                index2 = index2 + 1
    while index1 < len(lista1):
        lista.append(lista1[index1])
        index1 = index1 + 1
    while index2 < len(lista2):
        lista.append(lista2[index2])
        index2 = index2 + 1
    return lista

def mergesort(vector, key=lambda x: x, reverse=False):
    """
    sorteaza iterabilul prin interclasare
    :param vector: iterabil
    :param key: functie de sortare
    :param reverse: de la mare la mic sau nu
    :return: lista sortata
    """
    if len(vector)<2: return vector
    m = len(vector)//2
    stanga = mergesort(vector[:m], key, reverse)
    dreapta = mergesort(vector[m:], key, reverse)
    #print(f"stanga: {stanga}\ndreapta: {dreapta}")
    return interclasare(stanga, dreapta, key, reverse)

def quicksort(vector, key=lambda x: x, reverse=False):
    """
    sorteaza iterabilul prin sortare rapida
    :param vector: iterabil
    :param key: functie de sortare
    :param reverse: de la mare la mic sau nu
    :return: lista sortata
    """
    if len(vector) < 2:
        return vector
    pivot = vector.pop()
    maimici = quicksort([x for x in vector if not reverse and key(x) <= key(pivot) or reverse and key(x) >= key(pivot)], key, reverse)
    maimari = quicksort([x for x in vector if not reverse and key(x) > key(pivot) or reverse and key(x) < key(pivot)], key, reverse)
    # print(maimici, maimari)
    vector.append(pivot)
    return maimici + [pivot] + maimari

def selectionsort(vector, key=lambda x: x, reverse=False):
    """
    sorteaza iterabilul prin sortare rapida
    :param vector: iterabil
    :param key: functie de sortare
    :param reverse: de la mare la mic sau nu
    :return: lista sortata
    """
    rez = vector[:]
    poz = dict([(rez[i], i) for i in range(len(rez))])
    for i in range(len(rez)-1):
        pmin = i
        for j in range(i+1, len(rez)):
            if not reverse:
                if key(rez[j]) < key(rez[pmin]):
                    pmin = j
                elif key(rez[j]) == key(rez[pmin]) and poz[rez[j]] <= poz[rez[pmin]]:
                    pmin = j
            else:
                if key(rez[j]) > key(rez[pmin]):
                    pmin = j
                elif key(rez[j]) == key(rez[pmin]) and poz[rez[j]] <= poz[rez[pmin]]:
                    pmin = j
        rez[i], rez[pmin] = rez[pmin], rez[i]
    return rez

def shellsort(vector, key= lambda x: x, reverse=False):
    """
    sorteaza iterabilul prin sortare shell
    :param vector: iterabil
    :param key: functie de sortare
    :param reverse: de la mare la mic sau nu
    :return: lista sortata
    """
    rez = vector[:]
    poz = dict([(rez[i], i) for i in range(len(rez))])
    raza = len(vector)//2
    while raza > 0:
        index_s = 0
        index_d = raza
        while index_d < len(vector):
            if not reverse:
                if key(rez[index_d]) < key(rez[index_s]) or key(rez[index_d]) == key(rez[index_s]) and poz[rez[index_d]] < poz[rez[index_s]]:
                    rez[index_s], rez[index_d] = rez[index_d], rez[index_s]
            else:
                if key(rez[index_d]) > key(rez[index_s]) or key(rez[index_d]) == key(rez[index_s]) and poz[rez[index_d]] < poz[rez[index_s]]:
                    rez[index_s], rez[index_d] = rez[index_d], rez[index_s]

            index_s += 1
            index_d += 1

            index_m = index_s
            while index_m - raza >= 0:
                if not reverse:
                    if key(rez[index_m-raza]) > key(rez[index_m]) or key(rez[index_m-raza]) == key(rez[index_m]) and poz[rez[index_m]] < poz[rez[index_m-raza]]:
                        rez[index_m-raza], rez[index_m] = rez[index_m], rez[index_m-raza]
                else:
                    if key(rez[index_m-raza]) < key(rez[index_m]) or key(rez[index_m-raza]) == key(rez[index_m]) and poz[rez[index_m]] < poz[rez[index_m-raza]]:
                        rez[index_m-raza], rez[index_m] = rez[index_m], rez[index_m-raza]
                index_m -= 1
        raza //= 2
    return rez

def combsort(vector, key= lambda x: x, reverse=False):
    """
    sorteaza iterabilul prin sortare comb
    :param vector: iterabil
    :param key: functie de sortare
    :param reverse: de la mare la mic sau nu
    :return: lista sortata
    """
    nextraza = lambda r: 1 if (r*10)/13<=1 else (r*10)//13
    length = len(vector)
    raza = length
    interschimbat = True
    rez = vector[:]
    poz = dict([(rez[i], i) for i in range(len(rez))])

    while raza>1 or interschimbat == True:
        raza = nextraza(raza)
        interschimbat = False
        for i in range(0, length-raza):
            if not reverse:
                if key(rez[i]) > key(rez[i+raza]) or key(rez[i]) == key(rez[i+raza]) and poz[rez[i+raza]] < poz[rez[i]]:
                    rez[i], rez[i+raza] = rez[i+raza], rez[i]
                    interschimbat = True
            else:
                if key(rez[i]) < key(rez[i+raza]) or key(rez[i]) == key(rez[i+raza]) and poz[rez[i+raza]] < poz[rez[i]]:
                    rez[i], rez[i+raza] = rez[i+raza], rez[i]
                    interschimbat = True
    return rez

def bingosort(vector, key= lambda x: x, reverse=False):
    """
    sorteaza iterabilul prin sortare prin selectie de la dreapta la stanga
    :param vector: iterabil
    :param key: functie de sortare
    :param reverse: de la mare la mic sau nu
    :return: lista sortata
    """
    rez = vector[:]
    poz = dict([(rez[i], i) for i in range(len(rez))])
    for index_d in range(len(rez) - 1, 0, -1):
        pmax = index_d
        for j in range(0, index_d):
            if not reverse:
                if key(rez[j]) > key(rez[pmax]):
                    pmax = j
                elif key(rez[j]) == key(rez[pmax]) and poz[rez[j]] >= poz[rez[pmax]]:
                    pmax = j
            else:
                if key(rez[j]) < key(rez[pmax]):
                    pmax = j
                elif key(rez[j]) == key(rez[pmax]) and poz[rez[j]] >= poz[rez[pmax]]:
                    pmax = j
        rez[index_d], rez[pmax] = rez[pmax], rez[index_d]
    return rez

def gnomesort(vector, key= lambda x: x, reverse=False):
    """
    sorteaza iterabilul prin sortare derivata din metoda bulelor
    :param vector: iterabil
    :param key: functie de sortare
    :param reverse: de la mare la mic sau nu
    :return: lista sortata
    """
    index = 0
    rez = vector[:]
    poz = dict([(rez[i], i) for i in range(len(rez))])
    while index < len(rez):
        if index == 0:
            index += 1
            continue
        if not reverse:
            if key(rez[index]) >= key(rez[index-1]) or key(rez[index]) == key(rez[index-1]) and poz[rez[index-1]] < poz[rez[index]]:
                index += 1
            else:
                rez[index], rez[index-1] = rez[index-1], rez[index]
                index -= 1
        else:
            if key(rez[index]) <= key(rez[index-1]) or key(rez[index]) == key(rez[index-1]) and poz[rez[index-1]] < poz[rez[index]]:
                index += 1
            else:
                rez[index], rez[index-1] = rez[index-1], rez[index]
                index -= 1
    return rez

def shakesort(vector, key= lambda x: x, reverse=False):
    """
    sorteaza iterabilul prin sortare derivata din metoda bulelor
    :param vector: iterabil
    :param key: functie de sortare
    :param reverse: de la mare la mic sau nu
    :return: lista sortata
    """
    left = 0
    right = len(vector)-1
    lastswap = 0
    rez = vector[:]
    while left < right:
        for index in range(right, left, -1):
            if not reverse:
                if key(rez[index-1]) > key(rez[index]):
                    rez[index-1], rez[index] = rez[index], rez[index-1]
                    lastswap = index
            else:
                if key(rez[index-1]) < key(rez[index]):
                    rez[index-1], rez[index] = rez[index], rez[index-1]
                    lastswap = index
        left = lastswap
        for index in range(left, right):
            if not reverse:
                if key(rez[index]) > key(rez[index + 1]):
                    rez[index + 1], rez[index] = rez[index], rez[index + 1]
                    lastswap = index
            else:
                if key(rez[index]) < key(rez[index + 1]):
                    rez[index + 1], rez[index] = rez[index], rez[index + 1]
                    lastswap = index
        right = lastswap
    return rez

def test():
    sortari = [bubblesort, selectionsort, insertionsort, quicksort, mergesort, shellsort, combsort, bingosort, gnomesort, shakesort]
    #sortari = [shakesort]
    for i in range(randint(1, 10)):
        l1 = [randint(1, 100) for i in range(randint(1, 10))]
        for sortare in sortari:
            assert sortare(l1) == sorted(l1)
            assert sortare(l1, key=lambda x: x%10) == sorted(l1, key=lambda x: x%10)
            assert sortare(l1, key=lambda x: x%10, reverse=True) == sorted(l1, key=lambda x: x%10, reverse=True)
        #print(f"Gata testul {i}!")

test()

mysorted = mergesort

