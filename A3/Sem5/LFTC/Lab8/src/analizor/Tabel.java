package analizor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Tabel {
    ColectieCanonica colectieCanonica;
    Map<Simbol, Integer> numeColoane;
    ActiuneData[][] tabel;

    public Tabel(ColectieCanonica colectieCanonica) {
        this.colectieCanonica = colectieCanonica;
        List<Simbol> simboluri = colectieCanonica.gramatica.getTerminale();
        simboluri.add(Simbol.DOLLAR);
        simboluri.addAll(colectieCanonica.gramatica.getNeterminale());
        simboluri.remove(colectieCanonica.gramatica.start);
        createNumeColoane(simboluri);

        tabel = new ActiuneData[colectieCanonica.stari.size()][simboluri.size()];
        createTabel();
    }

    public ActiuneData get(int stare, String simbol) {
        Simbol s = new Simbol(simbol);
        Integer col = numeColoane.get(s);
        if(col == null)
            return null;
        return tabel[stare][col];
    }

    private void createNumeColoane(List<Simbol> simboluri) {
        numeColoane = new HashMap<>();
        for (int i = 0; i < simboluri.size(); i++) {
            Simbol simbol = simboluri.get(i);
            numeColoane.put(simbol, i);
        }
    }

    private void put(int i, int j, ActiuneData actiuneData) {
        if(tabel[i][j] == null)
            tabel[i][j] = actiuneData;
        else if (!actiuneData.equals(tabel[i][j])){
            Exception e1 = new Exception("Conflict la tabel pentru\n\t i=" + i + " j=" + j + "\n\told value=" + tabel[i][j]+ " actiuneData=" + actiuneData);
            throw new RuntimeException(e1);
        }
    }

    private void createTabel() {
        Gramatica gramatica = colectieCanonica.gramatica;
        for (int i = 0; i < colectieCanonica.stari.size(); i++) {
            Stare stare = colectieCanonica.stari.get(i);
            for(ElemAnaliza elemAnaliza : stare.elemAnaliza) {
                Actiune actiune = elemAnaliza.getActiune(gramatica);
                switch (actiune) {
                    case ACCEPTARE -> {
                        int col = numeColoane.get(elemAnaliza.urmator);
                        put(i, col, new ActiuneData(actiune, -1));
                    }
                    case REDUCERE -> {
                        int index = elemAnaliza.getReducereIndex(gramatica);
                        Simbol urmator = elemAnaliza.urmator;
                        int col = numeColoane.get(urmator);
                        put(i, col, new ActiuneData(actiune, index));
                    }
                    case SHIFTARE -> {
                        List<Tranzitie> tranzitii = colectieCanonica.tranzitii.stream()
                                .filter(t -> t.stareStart.equals(stare))
                                .distinct().toList(); // toate tranzitiile care pornesc din starea curenta
                        for (Tranzitie tranzitie : tranzitii) {
                            Simbol simbol = tranzitie.simbol;
                            int col = numeColoane.get(simbol);
                            int index = colectieCanonica.stari.indexOf(tranzitie.stareEnd);
                            put(i, col, new ActiuneData(actiune, index));
                        }
                    }
                }
            }
        }
    }

    public void print() {
        System.out.println(colectieCanonica);
        String space = "     ";
        System.out.println("analizor.Tabel:");
        System.out.print("   ");
        for(Integer i = 0; i < tabel[0].length; i++) {
            final int index = i;
            var simbol = numeColoane.entrySet().stream()
                    .filter(e -> e.getValue().equals(index))
                    .map(Map.Entry::getKey)
                    .findFirst().orElse(null);
            System.out.format("%-5s", simbol);
        }
        System.out.println();
        for (int i = 0; i < tabel.length; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < tabel[i].length; j++) {
                ActiuneData actiuneData = tabel[i][j];
                if(actiuneData == null)
                    System.out.print(space);
                else
                    System.out.format("%-5s", actiuneData);
            }
            System.out.println();
        }
    }
}
