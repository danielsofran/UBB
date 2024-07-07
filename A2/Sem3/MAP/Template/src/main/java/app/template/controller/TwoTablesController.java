package app.template.controller;

import app.template.service.Service;
import app.template.ui.GUIMaker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class TwoTablesController {
    Service service;

    ObservableList<Object> items1 = FXCollections.observableArrayList();
    ObservableList<Object> items2 = FXCollections.observableArrayList();

    @FXML
    private TableView<Object> table1;

    @FXML
    private TableView<Object> table2;

    @FXML
    private Button btn;

    public void setData(Service service)
    {
        this.service = service;

    }

    @FXML
    public void initialize(){
        GUIMaker.createTable(Object.class, table1);
        GUIMaker.createTable(Object.class, table2);
        table1.setItems(items1);
        table2.setItems(items2);
    }

    @FXML
    void btnClicked() {

    }
}