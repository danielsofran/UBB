package analizor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gramatica {
    Simbol start;
    List<Productie> productii = new ArrayList<Productie>();
    Map<Simbol, List<Simbol>> FIRST;

    public Gramatica(List<String> lines) {
        loadFromLines(lines);
        computeFIRST();
    }

    public List<Simbol> getTerminale() {
        List<Simbol> terminale = new ArrayList<>();
        for (Productie productie : this.productii) {
            for (Simbol simbol : productie.dreapta) {
                if(simbol.isTerminal())
                    terminale.add(simbol);
            }
        }
        return terminale;
    }

    public List<Simbol> getNeterminale() {
        List<Simbol> neterminale = new ArrayList<>();
        for (Productie productie : this.productii) {
            neterminale.add(productie.stanga);
            for (Simbol simbol : productie.dreapta) {
                if(simbol.isNeterminal())
                    neterminale.add(simbol);
            }
        }
        return neterminale.stream().distinct().toList();
    }

    public List<Simbol> getFIRST(List<Simbol> simboluri) {
        List<Simbol> first = new ArrayList<>();
        for (int i = 0; i < simboluri.size(); i++) {
            Simbol simbol = simboluri.get(i);
            if(simbol.isTerminal()) {
                first.add(simbol);
                break;
            }
            if(simbol.isNeterminal()) {
                List<Simbol> firstSimbol = this.FIRST.get(simbol);
                if(firstSimbol == null)
                    throw new RuntimeException("Simbolul " + simbol + " nu are FIRST");
                boolean hasEpsilon = false;
                for(Simbol s : firstSimbol) {
                    if(s.isEpsilon())
                        hasEpsilon = true;
                    else if(!first.contains(s))
                        first.add(s);
                }
                if(!hasEpsilon)
                    break;
                if(i == simboluri.size() - 1)
                    first.add(new Simbol(""));
            }
        }
        return first;
    }

    public String toString() {
        String s = "";
        for (Productie productie : this.productii) {
            s += productie.toString() + "\n";
        }
        return s;
    }

    private void loadFromLines(List<String> lines) {
        boolean firstLine = true;
        for (String line : lines) {
            List<Productie> productii = Productie.fromString(line);
            for (Productie productie : productii) {
                if(firstLine) {
                    Simbol start = productie.stanga;
                    // imbogatim gramatica cu o productie SS -> S
                    String startName = start.s + start.s;
                    this.start = new Simbol(startName);
                    Productie suplimentar = new Productie(this.start, new ArrayList<>(List.of(start)));
                    if(!this.productii.contains(suplimentar))
                        this.productii.add(suplimentar);
                    firstLine = false;
                }
                if(!this.productii.contains(productie))
                    this.productii.add(productie);
            }
        }
    }

    private boolean firstContains(Simbol stanga, Simbol dreapta) {
        if (this.FIRST.containsKey(stanga)) {
            List<Simbol> first = this.FIRST.get(stanga);
            return first.contains(dreapta);
        }
        return false;
    }

    private void addToFIRST(Simbol stanga, Simbol dreapta) {
        if (this.FIRST.containsKey(stanga)) {
            List<Simbol> first = this.FIRST.get(stanga);
            if (!first.contains(dreapta)) {
                first.add(dreapta);
            }
        } else {
            this.FIRST.put(stanga, new ArrayList<>(List.of(dreapta)));
        }
    }

    private void computeFIRST() {
        FIRST = new HashMap<>();
        for (Simbol terminal : this.getTerminale()) {
            this.FIRST.put(terminal, new ArrayList<>(List.of(terminal)));
        }
        for (Productie productie : this.productii) {
            Simbol stanga = productie.stanga;
            List<Simbol> dreapta = productie.dreapta;
            if (dreapta.get(0).isTerminal())
                addToFIRST(stanga, dreapta.get(0));
        }
        boolean changed = true;
        while(changed) {
            changed = false;
            for (Productie productie : this.productii) {
                Simbol stanga = productie.stanga;
                List<Simbol> dreapta = productie.dreapta;
                for (int i = 0; i < dreapta.size(); i++) {
                    Simbol value = dreapta.get(i);
                    if (value.isTerminal()) {
                        addToFIRST(stanga, value);
                        break;
                    } else if (value.isNeterminal() && this.FIRST.containsKey(value)) {
                        List<Simbol> first = this.FIRST.get(value);
                        boolean hasEpsilon = false;
                        for (Simbol simbol : first) {
                            if (simbol.isEpsilon()) {
                                hasEpsilon = true;
                            } else if (!this.firstContains(stanga, simbol)) {
                                addToFIRST(stanga, simbol);
                                changed = true;
                            }
                        }
                        if (!hasEpsilon) {
                            break;
                        }
                        if (i == dreapta.size() - 1 && !this.firstContains(stanga, new Simbol(""))) {
                            addToFIRST(stanga, new Simbol(""));
                            changed = true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
