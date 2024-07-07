package app.template.controller;

import app.template.models.Localitate;
import app.template.models.Rau;
import app.template.models.Riscuri;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import app.template.utils.events.ChangedEvent;
import app.template.utils.observer.MyObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class LocalitatiController implements MyObserver<ChangedEvent<Rau>> {
    Service service;

    ObservableList<Localitate> localitatesScazut = FXCollections.observableArrayList();
    ObservableList<Localitate> localitatesMediu = FXCollections.observableArrayList();
    ObservableList<Localitate> localitatesRidicat = FXCollections.observableArrayList();

    @FXML
    private TableView<Localitate> table1;

    @FXML
    private TableView<Localitate> table2;

    @FXML
    private TableView<Localitate> table3;

    public void setService(Service service)
    {
        this.service = service;
        loadData();
    }

    private void loadData(){
        localitatesScazut.setAll(service.findLocalitati(Riscuri.Scazut));
        localitatesMediu.setAll(service.findLocalitati(Riscuri.Mediu));
        localitatesRidicat.setAll(service.findLocalitati(Riscuri.Ridicat));
    }

    @FXML
    public void initialize(){
        GUIMaker.createTable(Localitate.class, table1);
        GUIMaker.createTable(Localitate.class, table2);
        GUIMaker.createTable(Localitate.class, table3);
        table1.setItems(localitatesScazut);
        table2.setItems(localitatesMediu);
        table3.setItems(localitatesRidicat);
    }

    @Override
    public void update(ChangedEvent<Rau> rauChangedEvent) {
        loadData();
    }
}