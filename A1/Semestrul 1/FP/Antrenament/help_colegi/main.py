from sortari import test_sortat

def minim(l, st, dr):
    if dr - st <= 1:
        return l[st]
    mij = (st+dr)//2
    min1 = minim(l, st, mij)
    min2 = minim(l, mij, dr)
    return min(min1, min2)

def negative(l, st, dr):
    if dr - st <= 1:
        if l[st] >= 0:
            return 0
        return 1
    mij = (st+dr)//2
    nr1 = negative(l, st, mij)
    nr2 = negative(l, mij, dr)
    return nr1 + nr2

def suma(l, st, dr):
    if dr - st <= 1:
        return l[st]
    mij = (st+dr)//2
    min1 = suma(l, st, mij)
    min2 = suma(l, mij, dr)
    return min1 +  min2

# l = [1, 4, 6, 2, 2, 5]
# print(merge(l[:3], l[3:]))
test_sortat(suma, 0, 0)
