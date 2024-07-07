"""
    partea de algoritmica si calcul
    contine functii de test
"""

def prim(n): # verifica daca numarul este prim
    i = 2
    if n == 2: return True
    if n < 2: return False
    while i*i<=n:
        if n%i==0:
            return False
        i = i + 1
    return True

def dif_prima(lista):
    # returneaza secventele de lungime maxima care au diferenta elementelor prima
    rez = []
    l = []
    lmax = 2
    if len(lista)==0: return rez
    for i in range(0, len(lista)-1):
        l.append(lista[i])
        if not prim(abs(lista[i+1]-lista[i])):
            if len(l)>lmax:
                rez.clear()
                rez.append(l)
                lmax = len(l)
            elif len(l)==lmax:
                rez.append(l)
            l = []
    l.append(lista[len(lista)-1])
    if len(l)>lmax:
        rez.clear()
        rez.append(l)
        lmax = len(l)
    elif len(l)==lmax:
        rez.append(l)
    return rez

def din_interval(lista, interval=(0, 10)):
    # returneaza secventele de lungime maxima ale caror elemente apartin intervalului dat
    rez = []
    l = []
    lmax = 0
    for elem in lista:
        if(elem >= interval[0] and elem <= interval[1]):
            l.append(elem)
            if len(l)>lmax:
                lmax = len(l)
                rez.clear()
                rez.append(l)
            elif len(l)==lmax:
                rez.append(l)
        else: l = []
    return rez

def suma_maxima(lista):
    # returneaza secventa de lungime maxima care au suma maxima
    rez = []
    sp = []
    s = 0
    smax = 0
    lmax = 0
    for elem in lista:
        s += elem
        sp.append(s)
        if s>=smax:
            smax = s
            rez.clear()
            rez.append(lista[:len(sp)])
            lmax = len(sp)
        #elif s==smax:
        #    rez.append(lista[:len(sp)])
    for i in range(1, len(lista)):
        for j in range(i, len(lista)):
            if sp[j]-sp[i-1]>smax:
                smax = sp[j]-sp[i-1]
                rez.clear()
                rez.append(lista[i:j+1])
            elif sp[j]-sp[i-1]==smax:
                if j-i+1>lmax:
                    rez.clear()
                    rez.append(lista[i:j+1])
                elif j-i+1==lmax:
                    rez.append(lista[i:j+1])
    return rez

def test_suma_maxima():
    assert suma_maxima([1, 2, 3]) == [[1, 2, 3]]
    assert suma_maxima([-1, 2, 3]) == [[2, 3]]
    assert suma_maxima([2, 5, 3, -1]) == [[2, 5, 3]]
    assert suma_maxima([1, 2, 3, -2, 3, 5]) == [[1, 2, 3, -2, 3, 5]]

def test_prim(): # testeaza functia prim
    assert prim(0) == False
    assert prim(1) == False
    assert prim(2) == True
    assert prim(-1) == False
    assert prim(-100) == False
    assert prim(2) == True
    assert prim(7) == True
    assert prim(29) == True
    assert prim(14) == False

def test_din_interval(): # testeaza din_interval
    assert din_interval([]) == []
    assert din_interval([1, 2, 3, 23, -5, 0]) == [[1, 2, 3]]
    assert din_interval([-1, 1, 3, 11]) == [[1, 3]]
    assert din_interval([0, -3, 3, -4, 4]) == [[0], [3], [4]]
    assert din_interval([0, -2, 1, 3, 24, 3, 4, 10, -15, 9, 0, 9]) == [[3, 4, 10], [9, 0, 9]]

def test_dif_prima(): # testeaza dif_prima
    assert dif_prima([]) == []
    assert dif_prima([2, 1, 3, 2, 2, 2]) == [[1, 3]]
    assert dif_prima([2, 0, 2, 2, 1, 3, 8]) == [[2, 0, 2], [1, 3, 8]]
    assert dif_prima([-4, -2, 1, 1, 3, 4, -10, -5, 6]) == [[-4, -2, 1], [-10, -5, 6]]
    assert dif_prima([1,2,3,4,5]) == []
    assert dif_prima([2, 3, 5, -1, 0, 0, 1, 2]) == [[3, 5]]

test_prim()
test_dif_prima()
test_din_interval()
test_suma_maxima()