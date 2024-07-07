package app.template.controller;

import app.template.models.Order;
import app.template.models.OrderDto;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import app.template.ui.MessageBox;
import app.template.utils.events.ChangedEvent;
import app.template.utils.observer.MyObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.util.LinkedList;
import java.util.List;

public class StaffController implements MyObserver<ChangedEvent<Order>> {
    Service service;

    ObservableList<OrderDto> orders = FXCollections.observableArrayList();

    @FXML
    private TableView<OrderDto> table;

    public void setService(Service service)
    {
        this.service = service;
        loadData();
    }

    void loadData(){
        List<OrderDto> rez = service.getOrders();
        MessageBox.showErrorMessage(null, String.valueOf(rez.size()));
        orders.setAll(rez);
    }

    @FXML
    public void initialize(){
        GUIMaker.createTable(OrderDto.class, table);
        table.setItems(orders);
    }

    @Override
    public void update(ChangedEvent<Order> orderChangedEvent) {
        loadData();
    }
}