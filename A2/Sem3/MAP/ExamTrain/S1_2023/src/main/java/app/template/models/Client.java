package app.template.models;

import app.template.orm.annotations.DBEntity;
import app.template.orm.annotations.PK;

@DBEntity
public class Client {
    @PK
    Long clientId;
    String name;
    int fidelityGrade;
    int varsta;
    Hobbies hobbies;

    public Client(){}

    public Client(Long clientId, String name, int fidelityGrade, int varsta, Hobbies hobbies) {
        this.clientId = clientId;
        this.name = name;
        this.fidelityGrade = fidelityGrade;
        this.varsta = varsta;
        this.hobbies = hobbies;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFidelityGrade() {
        return fidelityGrade;
    }

    public void setFidelityGrade(int fidelityGrade) {
        this.fidelityGrade = fidelityGrade;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public Hobbies getHobbies() {
        return hobbies;
    }

    public void setHobbies(Hobbies hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        return clientId.equals(client.clientId);
    }

    @Override
    public int hashCode() {
        return clientId.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
