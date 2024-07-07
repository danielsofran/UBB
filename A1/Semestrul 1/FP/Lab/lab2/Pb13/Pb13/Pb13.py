#count = int(input("Introduceti un numar natural: "))

def prim(n):
    for i in range(2, n//2+1):
        if(n%i==0): return False
    return True

def divizori(n):
    d = 2
    l = []
    while n>1:
        p = 0
        while n%d==0:
            n = n//d
            p = p+1
        if p!=0:
            for i in range(d): l.append(d)
        d = d + 1
        if(d*d>n): d = n
    return l

def getnr(count):
    n = 0 # index N
    current = 0

    while count>0:
        n = n+1
        if prim(n): 
            count = count-1
            current = n
        else: 
            l = divizori(n)
            if(len(l)<count):
                count = count-len(l) # am pus divizorii in sir
            else:
                current = l[count-1]
                count = -1
    return current

print(getnr(int(input())))