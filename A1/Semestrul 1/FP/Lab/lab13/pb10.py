n = int(input("Introduceti n: "))
v = [int(token) for token in input("Introduceti lista: ").split()]
p = [False for i in range(n+1)]

def consistent(lista):
    global v
    if len(lista) < 2: return True
    if len(lista) != len(set(lista)): return False
    for i in range(len(lista)-1):
        if v[lista[i]] > v[lista[i+1]]:
            return False
        ok = False
        for c in str(v[lista[i]]):
            if c in str(v[lista[i+1]]):
                ok = True
        if not ok: return False
    return True

def afisare(lista):
    global v
    for i in range(len(lista)):
        print(v[lista[i]], end=" ")
    print()

def backRec(lista):
    global v, p, n
    lista.append(None)
    for i in range(0, n):
        if not p[i]:
            lista[-1] = i
            p[i] = True
            if consistent(lista):
                if len(lista) > 2:
                    afisare(lista)
                if len(lista) <= len(v):  # useless, toate is puse
                    backRec(lista[:])
            p[i] = False

def backIter():
    x = [-1]
    while(len(x)>0):
        choosed = False
        while not choosed and x[-1]<n-1:
            x[-1] = x[-1]+1
            choosed = consistent(x)
        if choosed:
            if len(x)>2:
                afisare(x)
            x.append(-1)
        else:
            x = x[:-1]

backIter()


