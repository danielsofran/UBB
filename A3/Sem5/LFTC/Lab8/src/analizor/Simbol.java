package analizor;

import java.util.ArrayList;
import java.util.List;

class Simbol {
    String s;

    public static Simbol EPSILON = new Simbol("");
    public static Simbol DOLLAR = new Simbol("$");

    Simbol(String s) {
        this.s = s;
    }

    public boolean isTerminal() {
        return !this.isNeterminal();
    }

    public boolean isNeterminal() {
        if(s.length() < 1) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if(!Character.isUpperCase(this.s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isEpsilon() {
        return this.s.equals("");
    }

    public static List<Simbol> concat(List<Simbol> a, List<Simbol> b) {
        List<Simbol> c = new ArrayList<>();
        // concateneaza a si b in c, eliminand duplicatele si epsilon
        for (Simbol simbol : a) {
            if(!c.contains(simbol) && !simbol.isEpsilon()) {
                c.add(simbol);
            }
        }
        for (Simbol simbol : b) {
            if(!c.contains(simbol) && !simbol.isEpsilon()) {
                c.add(simbol);
            }
        }
        if(a.size() + b.size() > 0 && c.isEmpty()) {
            c.add(new Simbol("")); // epsilon
        }
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Simbol simbol)) return false;

        return s.equals(simbol.s);
    }

    @Override
    public int hashCode() {
        return s.hashCode();
    }

    public String toString() {
        return this.s;
    }
}
