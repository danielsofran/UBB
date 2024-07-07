package app.template.models;

import app.template.orm.annotations.*;

@DBEntity
public class Sectie {
    @PK @AutoInc
    int id;
    String nume;
    @FK(Table = Medic.class, RefCol = "id")
    int idSefDeSectie;
    int pretPerConsultatie;
    int durataMaximaConsultatie;

    public Sectie(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getIdSefDeSectie() {
        return idSefDeSectie;
    }

    public void setIdSefDeSectie(int idSefDeSectie) {
        this.idSefDeSectie = idSefDeSectie;
    }

    public int getPretPerConsultatie() {
        return pretPerConsultatie;
    }

    public void setPretPerConsultatie(int pretPerConsultatie) {
        this.pretPerConsultatie = pretPerConsultatie;
    }

    public int getDurataMaximaConsultatie() {
        return durataMaximaConsultatie;
    }

    public void setDurataMaximaConsultatie(int durataMaximaConsultatie) {
        this.durataMaximaConsultatie = durataMaximaConsultatie;
    }
}
