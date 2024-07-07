package app.template.controller;

import app.template.models.Sectie;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import app.template.ui.MessageBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.io.IOException;

public class SectieController {
    Service service;

    ObservableList<Sectie> secties = FXCollections.observableArrayList();

    @FXML
    private TableView<Sectie> table;

    @FXML
    private Button btn;

    public void setService(Service service)
    {
        this.service = service;
        secties.setAll(service.getAllSectii());
    }

    @FXML
    public void initialize(){
        GUIMaker.createTable(Sectie.class, table);
        table.setItems(secties);
    }

    @FXML
    void btnClicked() throws IOException {
        Sectie sectie = table.getSelectionModel().getSelectedItem();
        if(sectie == null)
        {
            MessageBox.showErrorMessage(null, "Please select one sectie!");
            return;
        }
        GUIMaker.openNewWindow("consultatie.fxml", "Programeaza o consultatie", fxmlLoader -> {
            ConsultatieController controller = fxmlLoader.getController();
            controller.setData(service, sectie);
        });
    }
}