package analizor;

class Tranzitie {
    Stare stareStart;
    Stare stareEnd;
    Simbol simbol;

    public Tranzitie(Stare stareStart, Stare stareEnd, Simbol simbol) {
        this.stareStart = stareStart;
        this.stareEnd = stareEnd;
        this.simbol = simbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tranzitie tranzitie) {
            return this.stareStart.equals(tranzitie.stareStart) && this.stareEnd.equals(tranzitie.stareEnd) && this.simbol.equals(tranzitie.simbol);
        }
        return false;
    }
}
