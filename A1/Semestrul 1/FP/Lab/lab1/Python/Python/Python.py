import math as m

def suma():
    print("LAB 1 pb 1")
    sep = " "
    l = input("Introduceti numere naturale: ").split(sep)
    s = 0
    for x in l:
        if(not x in sep and x is not ""):
            s += int(x)
    print("suma lor este " + str(s))

suma()

def prim(n):
    if(n == 2): return True
    if(n < 2): return False
    if(n%2 == 0): return False
    for i in range(3, int(m.sqrt(n))+1):
        if(n%i == 0): return False
    return True

print("LAB 1 pb 2")
print("Valoarea de adevar a propozitiei 'numarul este prim' este " + str(prim(int(input()))))  

def cmmdc(a, b):
    while(b!=0):
        r = a%b
        a = b
        b = r
    return a

print("LAB 1 pb 3")
il = input("Introduceti doua numere: ").split(" ")
print("Cel mai mare divizor comun a celor 2 numere este: " + str(cmmdc(int(il[0]), int(il[1])))) 

def PB7():
    print("LAB 2 pb 7")
    n = int(input("Introduceti un numar: "))
    p = 1
    for i in range(2, int(m.sqrt(n))+1):
        if(n%i == 0):
            p*=i
            if(i*i < n): p*=n//i;
    print("Produsul divizorilor proprii este: " + str(p))

PB7()
