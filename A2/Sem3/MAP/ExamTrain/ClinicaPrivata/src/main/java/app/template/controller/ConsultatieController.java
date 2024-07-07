package app.template.controller;

import app.template.exceptions.MyException;
import app.template.models.Medic;
import app.template.models.Sectie;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import app.template.ui.MessageBox;
import app.template.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultatieController {
    Service service;
    Sectie sectie;

    ObservableList<Medic> medici = FXCollections.observableArrayList();

    @FXML
    private TableView<Medic> table;

    @FXML
    private Button btn;

    @FXML
    TextField cnpTF;

    @FXML
    TextField numeTF;

    @FXML
    DatePicker datePicker;

    @FXML
    TextField oraTF;

    public void setData(Service service, Sectie sectie)
    {
        this.service = service;
        this.sectie = sectie;

        medici.setAll(service.getMedici(sectie));
    }

    @FXML
    public void initialize(){
        GUIMaker.createTable(Medic.class, table);
        table.setItems(medici);
    }

    @FXML
    void btnClicked() {
        Medic medic = table.getSelectionModel().getSelectedItem();
        String nume = numeTF.getText();
        String cnp = cnpTF.getText();
        LocalDate data = datePicker.getValue();
        LocalTime ora;
        try{ora=LocalTime.parse(oraTF.getText());}
        catch (Exception ex)
        {
            MessageBox.showErrorMessage(null, "Invalid time!");
            return;
        }
        try {
            service.programeaza(medic, cnp, nume, data, ora);
        }
        catch (MyException ex)
        {
            MessageBox.showErrorMessage(null, ex.getMessage());
        }
    }
}