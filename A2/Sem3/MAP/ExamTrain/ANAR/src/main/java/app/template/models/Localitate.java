package app.template.models;

import app.template.orm.annotations.DBEntity;
import app.template.orm.annotations.PK;

@DBEntity
public class Localitate {
    @PK
    String nume;
    Rau rau;
    int cotaMinimaDeRisc;
    int cotaMaximaAdmisa;

    public Localitate(){}

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Rau getRau() {
        return rau;
    }

    public void setRau(Rau rau) {
        this.rau = rau;
    }

    public int getCotaMinimaDeRisc() {
        return cotaMinimaDeRisc;
    }

    public void setCotaMinimaDeRisc(int cotaMinimaDeRisc) {
        this.cotaMinimaDeRisc = cotaMinimaDeRisc;
    }

    public int getCotaMaximaAdmisa() {
        return cotaMaximaAdmisa;
    }

    public void setCotaMaximaAdmisa(int cotaMaximaAdmisa) {
        this.cotaMaximaAdmisa = cotaMaximaAdmisa;
    }
}
