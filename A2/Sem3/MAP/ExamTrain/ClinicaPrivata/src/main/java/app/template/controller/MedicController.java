package app.template.controller;

import app.template.models.Consultatie;
import app.template.models.Medic;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import app.template.utils.events.ChangedEvent;
import app.template.utils.observer.MyObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.util.Timer;
import java.util.TimerTask;

public class MedicController implements MyObserver<ChangedEvent<Consultatie>> {
    Service service;
    Medic medic;

    ObservableList<Consultatie> consultatieObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Consultatie> table;

    @FXML
    private Button btn;

    public void setData(Service service, Medic medic)
    {
        this.service = service;
        this.medic = medic;
        //loadData();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                loadData();
            }
        }, 10, 1000);
    }

    void loadData(){
        consultatieObservableList.setAll(service.getConsultatii(medic));
    }

    @FXML
    public void initialize(){
        btn.setVisible(false);
        GUIMaker.createEntityTable(Consultatie.class, table);
        table.setItems(consultatieObservableList);
    }

    @FXML
    void btnClicked() {

    }

    @Override
    public void update(ChangedEvent<Consultatie> consultatieChangedEvent) {
        loadData();
    }
}