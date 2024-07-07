package app.template.controller;

import app.template.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class TwoTablesController {
    Service service;

    @FXML
    private TableView<Object> table1;

    @FXML
    private TableView<Object> table2;

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