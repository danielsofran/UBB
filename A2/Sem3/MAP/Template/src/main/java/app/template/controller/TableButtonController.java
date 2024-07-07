package app.template.controller;

import app.template.service.Service;
import app.template.ui.GUIMaker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class TableButtonController {
    Service service;

    ObservableList<Object> items = FXCollections.observableArrayList();

    @FXML
    private TableView<Object> table;

    @FXML
    private Button btn;

    public void setData(Service service)
    {
        this.service = service;

    }

    @FXML
    public void initialize(){
        GUIMaker.createTable(Object.class, table);
        table.setItems(items);
    }

    @FXML
    void btnClicked() {

    }
}