package app.template.models;

import app.template.orm.annotations.*;

@DBEntity
public class Medic {
    @PK @AutoInc
    int id;
    int idSectie;
    String nume;
    int vechime;
    boolean rezident;

    public Medic(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSectie() {
        return idSectie;
    }

    public void setIdSectie(int idSectie) {
        this.idSectie = idSectie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVechime() {
        return vechime;
    }

    public void setVechime(int vechime) {
        this.vechime = vechime;
    }

    public boolean isRezident() {
        return rezident;
    }

    public void setRezident(boolean rezident) {
        this.rezident = rezident;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medic)) return false;

        Medic medic = (Medic) o;

        return id == medic.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
