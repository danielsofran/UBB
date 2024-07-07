package analizor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stare {
    Gramatica gramatica;
    List<ElemAnaliza> elemAnaliza;

    Stare(Gramatica gramatica, List<ElemAnaliza> elemAnaliza) {
        this.gramatica = gramatica;
        this.elemAnaliza = elemAnaliza;
    }

    public List<ElemAnalizaExtins> getElemAnalizaExtins() {
        List<ElemAnalizaExtins> elemAnalizaExtinsList = new ArrayList<>();
        for (ElemAnaliza elemAnaliza : this.elemAnaliza) {
            // search for elemAnalizaExtins with same start, stangaPunct, dreaptaPunct
            Optional<ElemAnalizaExtins> elemAnalizaExtins1 = elemAnalizaExtinsList.stream()
                    .filter(elemAnalizaExtins2 -> elemAnalizaExtins2.start.equals(elemAnaliza.start)
                            && elemAnalizaExtins2.stangaPunct.equals(elemAnaliza.stangaPunct)
                            && elemAnalizaExtins2.dreaptaPunct.equals(elemAnaliza.dreaptaPunct))
                    .findFirst();
            if (elemAnalizaExtins1.isPresent()) {
                elemAnalizaExtins1.get().addUrmator(elemAnaliza.urmator);
            } else {
                elemAnalizaExtinsList.add(new ElemAnalizaExtins(elemAnaliza));
            }
        }
        return elemAnalizaExtinsList;
    }

    public static Stare closure(Stare stare) {
        List<ElemAnaliza> elemAnalizaList = new ArrayList<>(stare.elemAnaliza);
        boolean added = true;
        while (added) {
            added = false;
            List<ElemAnaliza> elemAnalizaToAdd = new ArrayList<>();
            for (ElemAnaliza elemAnaliza : elemAnalizaList) {
                var firstDreaptaDupaPunct = elemAnaliza.getFirstDreaptaDupaPunct(); // B
                var betaReunitCuUrmatorul = elemAnaliza.getBetaReunitCuUrmatorul(); // beta + predictie
                var urmatorii = stare.gramatica.getFIRST(betaReunitCuUrmatorul);
                if (firstDreaptaDupaPunct.isNeterminal()) {
                    List<Productie> productii = stare.gramatica.productii.stream()
                            .filter(productie -> productie.stanga.equals(firstDreaptaDupaPunct))
                            .toList();
                    for (Productie productie : productii) {
                        for (Simbol urmator : urmatorii) {
                            ElemAnaliza elem = new ElemAnaliza(productie, urmator);
                            if (!elemAnalizaList.contains(elem)) {
                                elemAnalizaToAdd.add(elem);
                                added = true;
                            }
                        }
                    }
                }
            }
            elemAnalizaList.addAll(elemAnalizaToAdd.stream().distinct().toList());
        }
        return new Stare(stare.gramatica, elemAnalizaList);
    }

    public Stare goTo(Simbol simbol) {
        // determin starile in care se poate ajunge din starea curenta prin simbol
        List<ElemAnaliza> elemAnaliza = this.elemAnaliza.stream()
                .filter(elem -> elem.getFirstDreaptaDupaPunct().equals(simbol))
                .toList();
        List<ElemAnaliza> elemAnalizaRez = new ArrayList<>();
        for (ElemAnaliza elem : elemAnaliza) {
            ElemAnaliza elemRez = movePunct(elem);
            elemAnalizaRez.add(elemRez);
        }
        // returnez o noua stare
        return closure(new Stare(this.gramatica, elemAnalizaRez));
    }

    private static ElemAnaliza movePunct(ElemAnaliza elem) {
        List<Simbol> stangaPunct = new ArrayList<>(elem.stangaPunct);
        stangaPunct.add(elem.getFirstDreaptaDupaPunct());
        List<Simbol> dreaptaPunct = new ArrayList<>(elem.dreaptaPunct);
        dreaptaPunct.remove(0);
        return new ElemAnaliza(elem.start, stangaPunct, dreaptaPunct, elem.urmator);
    }

    public List<Simbol> getAllFirstDreaptaDupaPunct() {
        List<Simbol> rez = new ArrayList<>();
        for (ElemAnaliza elemAnaliza : this.elemAnaliza) {
            if (!elemAnaliza.getFirstDreaptaDupaPunct().equals(Simbol.EPSILON))
                rez.add(elemAnaliza.getFirstDreaptaDupaPunct());
        }
        return rez.stream().distinct().toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stare stare)) return false;

        if (this.elemAnaliza.size() != stare.elemAnaliza.size()) return false;
        for (ElemAnaliza elemAnaliza : this.elemAnaliza) {
            if (!stare.elemAnaliza.contains(elemAnaliza))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = gramatica.hashCode();
        result = 31 * result + elemAnaliza.hashCode();
        return result;
    }

    public String toString() {
        String s = "{ ";
        for (ElemAnalizaExtins elemAnalizaExtins : this.getElemAnalizaExtins()) {
            s += elemAnalizaExtins.toString() + ", ";
        }
        s += " }";
        return s;
    }
}