package app.template.models;

import app.template.orm.annotations.AutoInc;
import app.template.orm.annotations.DBEntity;
import app.template.orm.annotations.PK;

import java.time.LocalDateTime;

@DBEntity
public class Order {
    @PK @AutoInc
    int id;
    Table table;
    LocalDateTime date;
    OrderStatus status;

    public Order(){}

    public Order(Table table, LocalDateTime date, OrderStatus status) {
        this.table = table;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        return id == order.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
