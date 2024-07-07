def cifre(numar):
    # functie care returneaza o multime cu cifrele numarului n
    # n - intreg sau sir de caractere
    vector_caracteristic = dict()
    if isinstance(numar, str):
        zindex = 0
        while numar[zindex]=='0' or numar[zindex]=='-': zindex = zindex+1
        numar = numar[zindex:]
        for cifra in numar:
            vector_caracteristic[int(cifra)] = 1
    else:
        numar = abs(numar)
        while numar!=0:
            vector_caracteristic[numar%10] = 1
            numar = numar // 10
    return set(vector_caracteristic.keys())

def test_cifre(): # testeaza functia cifre(numar) pentru cateva seturi de valori
    assert cifre(12)=={1,2}
    assert cifre("12")=={1, 2}
    assert cifre("000034343") == {3, 4}
    assert cifre(220340) == {0, 2, 3, 4}

def secvente(lista):
    # determina toate secventele de lungime mexima cu proprietatea ceruta
    secv = []    # o lista de perechi (st, dr)
    st = dr = 0        # indecsii unei secvente de forma lista[st], ..., lista[dr]
    lmax = 1           # lungimea maxima a unei secvente
    prop = {}          # proprietatea ceruta a secventei pe care o analizam
                       # cifrele numerelor din secventa curenta
    
    for index in range(len(lista)):
        if prop == cifre(lista[index]):
            dr = dr + 1
            if dr-st+1>lmax:
                lmax = dr-st+1
                secv.clear()
                secv.append(lista[st: dr+1])
            elif dr-st+1==lmax:
                secv.append(lista[st: dr+1])
        else: 
            prop = cifre(lista[index])
            st = dr = index
            if dr-st+1==lmax:
                secv.append(lista[st: dr+1])
    return secv

def test_secvente():
    # 2 secvente distincte
    assert secvente([1, 331, 113, 31, 13, 1, 3, 30, 0, 13, 31, 331, 1313, 0, 9, 3467, 81, 2]) == [[331, 113, 31, 13], [13, 31, 331, 1313]]
    # un singur element
    assert secvente([345]) == [[345]]
    # toate elementele sunt secvente
    assert secvente([1, 12, 34, 56, 78]) == [[1], [12], [34], [56], [78]]
    # o secventa
    assert secvente([24, 42, 224, 442, 4444444444442, 2222222222224]) == [[24, 42, 224, 442, 4444444444442, 2222222222224]]
    # 3 secvente - st, mijl, dr
    assert secvente([12, 21, 112, 1, 34, 2, 45, 55545, 45, 67, 85, 808, 908, 99088, 89890]) == [[12, 21, 112], [45, 55545, 45], [908, 99088, 89890]]
    # secvente in ordine crescatoare
    assert secvente([1, 20, 220, 31, 331, 3131, 42, 442, 242424, 42, 513, 5513, 3513, 553311, 55555555513]) == [[513, 5513, 3513, 553311, 55555555513]]
    # cu numere negative
    assert secvente([13, 331, -13, "-1331"]) == [[13, 331, -13, "-1331"]]

test_cifre()
test_secvente()
lista = input("Introduceti numere naturale: ").split(" ")
for secv in secvente(lista): print(secv)

