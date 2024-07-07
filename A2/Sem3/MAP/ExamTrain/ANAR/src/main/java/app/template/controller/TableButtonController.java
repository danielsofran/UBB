package app.template.controller;

import app.template.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class TableButtonController {
    Service service;

    @FXML
    private TableView<Object> table;

    @FXML
    private Button btn;

    public void setService(Service service)
    {
        this.service = service;

    }

    @FXML
    public void initialize(){

    }

    @FXML
    void btnClicked() {

    }
}