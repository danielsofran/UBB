package analizor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class ColectieCanonica {
    Gramatica gramatica;
    List<Stare> stari;
    List<Tranzitie> tranzitii;

    public ColectieCanonica(Gramatica gramatica) {
        this.gramatica = gramatica;
        Productie productie = gramatica.productii.get(0);
        this.stari = new ArrayList<>();
        this.tranzitii = new ArrayList<>();
        List<ElemAnaliza> elemAnaliza = new ArrayList<>();
        elemAnaliza.add(new ElemAnaliza(productie, Simbol.DOLLAR));

        Queue<Stare> queue = new LinkedList<>();
        Stare stareInitiala = Stare.closure(new Stare(this.gramatica, elemAnaliza));
        queue.add(stareInitiala);
        while(!queue.isEmpty()) {
            Stare stare = queue.poll();
            if (stari.contains(stare))
                continue;
            stari.add(stare);
            for (Simbol simbol : stare.getAllFirstDreaptaDupaPunct()) {
                Stare s = stare.goTo(simbol);
                tranzitii.add(new Tranzitie(stare, s, simbol));
                queue.add(s);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stari.size(); i++) {
            sb.append("s").append(i).append(": ").append(stari.get(i)).append("\n");
        }
        return sb.toString();
    }
}
