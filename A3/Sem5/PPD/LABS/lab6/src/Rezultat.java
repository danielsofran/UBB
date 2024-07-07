public class Rezultat implements Comparable<Rezultat> {
    String concurent;
    int punctaj;

    public Rezultat(String concurent, int punctaj) {
        this.concurent = concurent;
        this.punctaj = punctaj;
    }

    public String getConcurent() {
        return concurent;
    }

    public int getPunctaj() {
        return punctaj;
    }

    public void setConcurent(String concurent) {
        this.concurent = concurent;
    }

    public void setPunctaj(int punctaj) {
        this.punctaj = punctaj;
    }

    public String toString() {
        return concurent + " " + punctaj;
    }

    public boolean equals(Object o) {
        if(o == null) return false;
        if(!(o instanceof Rezultat r)) return false;
        return concurent.equals(r.concurent);
    }

    @Override
    public int compareTo(Rezultat o) {
        // ordine descrescatoare dupa punctaj
        return Integer.compare(o.punctaj, punctaj);
    }
}
