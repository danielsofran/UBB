package analizor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ElemAnalizaExtins {
    Simbol start;
    List<Simbol> stangaPunct;
    List<Simbol> dreaptaPunct;
    List<Simbol> urmator;

    ElemAnalizaExtins(ElemAnaliza elemAnaliza) {
        this.start = elemAnaliza.start;
        this.stangaPunct = elemAnaliza.stangaPunct;
        this.dreaptaPunct = elemAnaliza.dreaptaPunct;
        this.urmator = new ArrayList<>(List.of(elemAnaliza.urmator));
    }

    public void addUrmator(Simbol simbol) {
        this.urmator.add(simbol);
    }

    @Override
    public String toString() {
        AtomicReference<String> rez = new AtomicReference<>("[ " + start + " -> ");
        stangaPunct.forEach(simbol -> rez.updateAndGet(v -> v + simbol));
        rez.updateAndGet(v -> v + ".");
        dreaptaPunct.forEach(simbol -> rez.updateAndGet(v -> v + simbol));
        rez.updateAndGet(v -> v + ", ");
        urmator.forEach(simbol -> rez.updateAndGet(v -> v + simbol + "/"));
        rez.updateAndGet(v -> v.substring(0, v.length() - 1));
        rez.updateAndGet(v -> v + " ]");
        return rez.get();
    }
}
