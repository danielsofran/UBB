package app.template.controller;

import app.template.exceptions.MyException;
import app.template.models.Rau;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import app.template.ui.MessageBox;
import app.template.utils.events.ChangedEvent;
import app.template.utils.observer.MyObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableView;

import java.util.List;

public class RauriController implements MyObserver<ChangedEvent<Rau>> {
    Service service;

    ObservableList<Rau> rauObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Rau> table;

    @FXML
    private Button btn;

    @FXML
    private Spinner<Integer> spnCota;

    public void setService(Service service)
    {
        this.service = service;
        List<Rau> raus = service.findAllRauri();
        rauObservableList.setAll(raus);
    }

    @FXML
    public void initialize(){
        GUIMaker.createTable(Rau.class, table);
        table.setItems(rauObservableList);

        spnCota.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @FXML
    void btnClicked() {
        Integer cota = spnCota.getValue();
        Rau rau = table.getSelectionModel().selectedItemProperty().getValue();
        try{
            service.updateRau(rau, cota);
        }
        catch (MyException ex)
        {
            MessageBox.showErrorMessage(null, ex.getMessage());
        }
    }

    @Override
    public void update(ChangedEvent<Rau> rauChangedEvent) {
        List<Rau> raus = service.findAllRauri();
        rauObservableList.setAll(raus);
    }
}