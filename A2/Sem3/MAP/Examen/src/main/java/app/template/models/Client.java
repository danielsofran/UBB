package app.template.models;

import app.template.orm.annotations.DBEntity;
import app.template.orm.annotations.PK;

@DBEntity
public class Client {
    @PK
    String username;
    String nume;

    public Client(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
