from itertools import permutations

spect = [2, 4]

def exista(ore_date):
    caz = 3
    indici = [i for i in range(len(spect))]
    for lg in range(1, len(spect)+1):
        perms = list(permutations(indici, lg))
        for perm in perms:
            s = sum([spect[perm[i]] for i in range(lg)])
            if s == ore_date:
                caz = min(caz, 1)
            if ore_date % s == 0:
                caz = min(caz, 2)
    return caz

print(exista(8))
