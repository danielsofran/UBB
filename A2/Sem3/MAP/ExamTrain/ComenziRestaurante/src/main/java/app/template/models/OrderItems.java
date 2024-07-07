package app.template.models;

import app.template.orm.annotations.*;

@DBEntity
public class OrderItems {
    @PK @AutoInc
    int id;
    Order order;
    MenuItem menuItem;

    public OrderItems(){}

    public OrderItems(Order order, MenuItem menuItem) {
        this.order = order;
        this.menuItem = menuItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
}
