import math as m

def PB7():
    # funcție care calculează și afișează produsul divizorilor proprii al unui număr 
    # natural introdus de la tastatură
    numar = int(input("Introduceți un număr natural: ")) 
    produs_divizori = 1
    # parcurgem posibilii divizori până la radical de ordinul 2 din numărul dat
    for divizor in range(2, int(m.sqrt(numar))+1): 
        if(numar%divizor == 0): # dacă numărul din parcurgere este un divizor
            produs_divizori*=divizor     # actualizăm produsul cu divizorul curent
            if(divizor*divizor < numar): # dacă divizorul complementar nu este identic cu cel curent
                produs_divizori*=numar//divizor; # actualizăm produsul cu divizorul complementar
    # divizor complementar = n // divizor curent
    print("Produsul divizorilor proprii este: " + str(produs_divizori)) # afișare rezultat

#PB7()

def PB8():
    # functie care afișează numărul natural maxim rezultat format cu cifrele numărului dat
    numar = input("Introduceți un număr natural: ")
    zindex = 0 # indexul de la care numărul nu mai are cifre de 0 la început
    while numar[zindex]=='0': zindex = zindex + 1
    numar = list(numar[zindex:])
    rezultat = ""
    for cifra in "9876543210": # cifrele in ordine descrescătoare
        while cifra in numar:
            rezultat = rezultat + cifra # o adăugam la rezultat
            numar.remove(cifra) # ștergem cifra din numarul inițial
    print("Numărul maxim construit cu cifrele numărului dat este: " + rezultat)

#PB8()

