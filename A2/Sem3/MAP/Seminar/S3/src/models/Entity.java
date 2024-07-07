package models;

import java.io.Serializable;

public class Entity<ID> implements Serializable {
    private static final long serialVersionUID = 403L;
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
