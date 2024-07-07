package app.template.models;

import app.template.orm.annotations.*;

import java.time.LocalDate;
import java.time.LocalTime;

@DBEntity
public class Consultatie {
    @PK @AutoInc
    int id;
    @FK(Table = Medic.class, RefCol = "id")
    int idMedic;
    String CNPPacient;
    String NumePacient;
    LocalDate Data;
    LocalTime Ora;

    public Consultatie(){}

    public Consultatie(int idMedic, String CNPPacient, String numePacient, LocalDate data, LocalTime ora) {
        this.idMedic = idMedic;
        this.CNPPacient = CNPPacient;
        NumePacient = numePacient;
        Data = data;
        Ora = ora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(int idMedic) {
        this.idMedic = idMedic;
    }

    public String getCNPPacient() {
        return CNPPacient;
    }

    public void setCNPPacient(String CNPPacient) {
        this.CNPPacient = CNPPacient;
    }

    public String getNumePacient() {
        return NumePacient;
    }

    public void setNumePacient(String numePacient) {
        NumePacient = numePacient;
    }

    public LocalDate getData() {
        return Data;
    }

    public void setData(LocalDate data) {
        Data = data;
    }

    public LocalTime getOra() {
        return Ora;
    }

    public void setOra(LocalTime ora) {
        Ora = ora;
    }
}
