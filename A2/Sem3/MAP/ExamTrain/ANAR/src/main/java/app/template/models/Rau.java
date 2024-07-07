package app.template.models;

import app.template.orm.annotations.DBEntity;
import app.template.orm.annotations.PK;

@DBEntity
public class Rau {
    @PK
    String nume;
    int cotaMedie;

    public Rau(){}

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getCotaMedie() {
        return cotaMedie;
    }

    public void setCotaMedie(int cotaMedie) {
        this.cotaMedie = cotaMedie;
    }

    @Override
    public String toString() {
        return nume;
    }
}
