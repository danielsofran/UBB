package app.template.models;

import app.template.orm.annotations.AutoInc;
import app.template.orm.annotations.DBEntity;
import app.template.orm.annotations.PK;

@DBEntity
public class Table {
    @PK
    int id;

    public Table(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
