package analizor;

import java.util.ArrayList;
import java.util.List;

class Productie {
    Simbol stanga;
    List<Simbol> dreapta;

    Productie(Simbol stanga, List<Simbol> dreapta) {
        this.stanga = stanga;
        this.dreapta = dreapta;
    }

    public static List<Productie> fromString(String s) {
        List<Productie> productii = new ArrayList<>();
        String[] parts = s.split("->");
        Simbol stanga = new Simbol(parts[0].trim());

        String dreaptaString = parts.length > 1 ? parts[1] : "";
        String[] variante = dreaptaString.trim().split("(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)\\|");
        for (String varianta : variante) {
            List<Simbol> dreapta = new ArrayList<>();
            String[] simboluri = varianta.trim().split(" ");
            for (String simbol : simboluri)
                dreapta.add(new Simbol(simbol.trim()));
            Productie productie = new Productie(stanga, dreapta);
            if(!productii.contains(productie))
                productii.add(productie);
        }
        return productii;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Productie productie)) return false;

        if (!stanga.equals(productie.stanga)) return false;
        return dreapta.equals(productie.dreapta);
    }

    @Override
    public int hashCode() {
        return 31 * stanga.hashCode() + dreapta.hashCode();
    }

    public String toString() {
        String s = this.stanga.toString() + " -> ";
        for (Simbol simbol : this.dreapta) {
            s += simbol.toString();
        }
        return s;
    }
}
