package analizor;

import java.util.ArrayList;
import java.util.List;

class ElemAnaliza {
    Simbol start;
    List<Simbol> stangaPunct;
    List<Simbol> dreaptaPunct;
    Simbol urmator;

    ElemAnaliza(Simbol start, List<Simbol> stangaPunct, List<Simbol> dreaptaPunct, Simbol urmator) {
        this.start = start;
        this.stangaPunct = stangaPunct;
        this.dreaptaPunct = dreaptaPunct;
        this.urmator = urmator;
    }

    ElemAnaliza(Productie productie, Simbol urmator) {
        this.start = productie.stanga;
        this.stangaPunct = new ArrayList<>();
        this.dreaptaPunct = productie.dreapta;
        this.urmator = urmator;
    }

    public Simbol getFirstDreaptaDupaPunct() {
        if(this.dreaptaPunct.size() > 0)
            return this.dreaptaPunct.get(0);
        return Simbol.EPSILON;
    }

    public List<Simbol> getBetaReunitCuUrmatorul() { // beta + predictie
        List<Simbol> betaReunitCuUrmatorul = new ArrayList<>();
        try{
            betaReunitCuUrmatorul.addAll(this.dreaptaPunct.subList(1, this.dreaptaPunct.size()));
        } catch (Exception ignored) {}
        return Simbol.concat(betaReunitCuUrmatorul, new ArrayList<>(List.of(this.urmator)));
    }

    private boolean isReducere() {
        return this.dreaptaPunct.size() == 0;
    }

    public Productie getReducere(Gramatica gramatica) {
        for(Productie productie : gramatica.productii) {
            if(productie.stanga.equals(this.start) && productie.dreapta.equals(this.stangaPunct))
                return productie;
        }
        throw new RuntimeException("Nu exista o reducere pentru " + this);
    }

    public int getReducereIndex(Gramatica gramatica) {
        for(int i = 0; i < gramatica.productii.size(); i++) {
            Productie productie = gramatica.productii.get(i);
            if(productie.stanga.equals(this.start) && productie.dreapta.equals(this.stangaPunct))
                return i;
        }
        throw new RuntimeException("Nu exista o reducere pentru " + this);
    }

    private boolean isAcceptare(Gramatica gramatica) {
        return this.start.equals(gramatica.start) && this.dreaptaPunct.size() == 0;
    }

    private boolean isShift() {
        return this.dreaptaPunct.size() > 0;
    }

    public Actiune getActiune(Gramatica gramatica) {
        if(this.isAcceptare(gramatica))
            return Actiune.ACCEPTARE;
        if(this.isReducere())
            return Actiune.REDUCERE;
        if(this.isShift())
            return Actiune.SHIFTARE;
        return Actiune.EROARE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElemAnaliza that)) return false;

        if (!start.equals(that.start)) return false;
        if (!stangaPunct.equals(that.stangaPunct)) return false;
        if (!dreaptaPunct.equals(that.dreaptaPunct)) return false;
        return urmator.equals(that.urmator);
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + stangaPunct.hashCode();
        result = 31 * result + dreaptaPunct.hashCode();
        result = 31 * result + urmator.hashCode();
        return result;
    }

    public String toString() {
        String s = "[" + this.start.toString() + " -> ";
        for (Simbol simbol : this.stangaPunct) {
            s += simbol.toString();
        }
        s += ".";
        for (Simbol simbol : this.dreaptaPunct) {
            s += simbol.toString();
        }
        s += ", " + this.urmator.toString() + "]";
        return s;
    }
}
