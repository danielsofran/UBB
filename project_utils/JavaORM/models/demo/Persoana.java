package models.demo;

import orm.annotations.AutoInc;
import orm.annotations.DBEntity;
import orm.annotations.PK;
import orm.annotations.rules.Cascade;

@DBEntity
public class Persoana {
    @PK @AutoInc
    private int id;
    @Cascade
    MData data;
    private String nume;

    public Persoana() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MData getData() {
        return data;
    }

    public void setData(MData data) {
        this.data = data;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
