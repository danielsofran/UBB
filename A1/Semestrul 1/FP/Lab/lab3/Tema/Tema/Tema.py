"""
    acesta este fisierul main
    implementeaza meniu conform cerintelor date
"""

from menu import *
import secvente as secv

def citire(lista):
    # citeste o lista de numere intregi de la tastatura separate prin spatiu
    lista.clear()
    print(cl.Fore.LIGHTGREEN_EX + "Introduceți șirul de numere întregi: ", cl.Style.RESET_ALL, end='')
    s = input()
    if len(s)==0: return
    l = s.split(" ")
    for elem in l:
        try: lista.append(int(elem))
        except Exception: 
            lista.clear()
            print(cl.Fore.LIGHTRED_EX + "Șirul nu poate conține alte valori decât numerele întregi!\n", cl.Style.RESET_ALL)
            return

def cerinta2(lista):
    # rezolva cerinta 2:
    # det. secventa de lungime maxima cu prop. ca diferenta dintre 2 termeni 
    # consecutivi este un numar prim
    rez = secv.dif_prima(lista)
    try: last = rez.pop()
    except Exception: 
        print(cl.Fore.LIGHTRED_EX + "Șirul introdus nu conține astfel de secvențe!", cl.Style.RESET_ALL)
        return 
    print(cl.Fore.CYAN + "Rezultat: ", cl.Style.RESET_ALL, end='')
    for secventa in rez:
        print(secventa, ", ", sep="", end=" ")
    print(last)

def cerinta3(lista):
    # rezolva cerinta 3
    # det. secventa de lungime maxima cu proprietatea ca fiecare element al 
    # secventei apartine intervalului [0, 10] dat

    '''
    s = input("Introduceți capetele intervalului închis inclus în [0, 10] între care doriți ca termenii șirului să se încadreze:\n\t")
    #try: 
    #    s = s.split(' ')
    #    s = (int(s[0]), int(s[1]))
    #    if(s[0]<0 or s[1]>10): raise "Interval depasit"
    #except Exception: 
    #    print("Ne pare rău, nu am putut determina intervalul pe care l-ați introdus.\nIntervalul va avea valoare inițială [0, 10]")
    #    s = (0, 10)'''
    rez = secv.din_interval(lista)
    try: last = rez.pop()
    except Exception: 
        print(cl.Fore.LIGHTRED_EX + "Șirul introdus nu conține astfel de secvențe!", cl.Style.RESET_ALL)
        return 
    print(cl.Fore.CYAN + "Rezultat: ", cl.Style.RESET_ALL, end='')
    for secventa in rez:
        print(secventa, ", ", sep="", end=" ")
    print(last)

def cerinta4(lista):
    # rezolvam cerina suplimentara
    # returneaza secventa de lungime maxima cu suma maxima
    rez = secv.suma_maxima(lista)
    try: last = rez.pop()
    except Exception: 
        print(cl.Fore.LIGHTRED_EX + "Șirul introdus nu conține astfel de secvențe!", cl.Style.RESET_ALL)
        return 
    print(cl.Fore.CYAN + "Rezultat: ", cl.Style.RESET_ALL, end='')
    for secventa in rez:
        print(secventa, ", ", sep="", end=" ")
    print(last)

def simple_menu(): # meniul 
    # selectarea optiunilor are loc prin tastarea 
    print("\n\tMeniu opțiuni\n")
    print("1 - Citire șir")
    print("2 - Secvența de lungime maximă a căror elemente diferă printr-un număr prim")
    print("3 - Secvența de lungime maximă care are toate elementele în intervalul [0, 10] dat")
    print("4 - Secvența de lungime maximă a cărei sumă este maximă")
    print("5 - Ieșire din aplicație", '\n')

    lista = []
    while True:
        s = input("Introduceți o opțiune din meniu: ")
        if s=='1': citire(lista)
        elif s=='2': cerinta2(lista)
        elif s=='3': cerinta3(lista)
        elif s=='4': cerinta4(lista)
        elif s=='5': break
        else: print("Opțiunea nu a fost găsită!\n")

def my_menu(): 
    # ruleaza meniul aplicatiei
    meniu = Meniu("\n\tMENIU DE OPȚIUNI\n", clear_after_input=False)
    lista = []
    #meniu.addVariabila("lista", [])
    meniu += Optiune("1", "Citire șir", cl.Fore.LIGHTGREEN_EX, citire, lista)
    meniu += Optiune("2", "Secvența de lungime maximă a căror elemente diferă printr-un număr prim", cl.Fore.LIGHTCYAN_EX, cerinta2, lista)
    meniu += Optiune("3", "Secvența de lungime maximă care are toate elementele în intervalul [0, 10] dat", cl.Fore.LIGHTCYAN_EX, cerinta3, lista)
    meniu += Optiune("4", "Secvența de lungime maximă a cărei sumă este maximă", cl.Fore.LIGHTCYAN_EX, cerinta4, lista)
    meniu += Optiune("5", "Ieșire din aplicație", cl.Fore.LIGHTRED_EX, lambda: exit(0))
    meniu.show()

my_menu()