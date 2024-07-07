package app.template.controller;

import app.template.exceptions.MyException;
import app.template.models.Client;
import app.template.models.Flight;
import app.template.models.Ticket;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import app.template.ui.MessageBox;
import app.template.utils.events.ChangedEvent;
import app.template.utils.observer.MyObserver;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.List;

public class ClientController implements MyObserver<ChangedEvent<Ticket>> {
    Service service;
    Client client;

    ObservableList<Flight> items = FXCollections.observableArrayList();

    @FXML
    private TableView<Flight> table;

    @FXML
    private Button btn;

    @FXML
    private Label currentPageLabel;

    @FXML
    private VBox vbox;

    @FXML
    private Button btnCumpara;

    @FXML
    private ComboBox<String> cmbFrom;

    @FXML
    private ComboBox<String> cmbTo;

    @FXML
    private DatePicker datePicker;

    public void setData(Service service, Client client)
    {
        this.service = service;
        this.client = client;

        cmbFrom.setItems(FXCollections.observableArrayList(service.getFrom()));
        cmbTo.setItems(FXCollections.observableArrayList(service.getTo()));
    }

    @FXML
    public void initialize(){
        createTable(table);
        table.setItems(items);
    }

    @FXML
    void btnClicked() {
        try{
            List<Flight> flights = service.getFlights(datePicker.getValue(), cmbFrom.getValue(), cmbTo.getValue());
            lastFlights = flights;
            createTables(flights);
            items.setAll(flights);
        }
        catch(MyException ex) {
            MessageBox.showErrorMessage(null, ex.getMessage());
        }
    }

    @FXML
    void btnCumparaClicked() {
        try{
            TableView<Flight> tableView = table;
            if(index >= 0)
                tableView = flightTables.get(index);
            Ticket ticket = service.cumpara(client, tableView.getSelectionModel().getSelectedItem());
            MessageBox.showSuccessMessage(null, "Biletul cu nr "+ticket.getId()+" a fost achizitionat!");
            table.getSelectionModel().select(-1);
        }
        catch(MyException ex) {
            MessageBox.showErrorMessage(null, ex.getMessage());
        }
    }

    @Override
    public void update(ChangedEvent<Ticket> ticketChangedEvent) {
        table.refresh();
        for(TableView<Flight> tableView : flightTables)
            tableView.refresh();
    }

    private void createTable(TableView<Flight> table){
        GUIMaker.createTable(Flight.class, table);
        TableColumn<Flight, Integer> locuriDispCol = new TableColumn<>();
        locuriDispCol.setCellValueFactory(param -> {
            return new ObservableValueBase<Integer>() {
                @Override
                public Integer getValue() {
                    Flight flight = param.getValue();
                    return service.getNrLocuriDisponibile(flight);
                }
            };
        });
        locuriDispCol.setText("Locuri libere");
        table.getColumns().add(locuriDispCol);
    }

    List<Flight> lastFlights = new LinkedList<>();
    List<TableView<Flight>> flightTables = new LinkedList<>();
    List<ObservableList<Flight>> flightsObservableList = new LinkedList<>();
    int index = -1;

    private void createTables(List<Flight> flights)
    {
        flightTables.clear();
        flightsObservableList.clear();
        List<Flight> source = new LinkedList<>();
        for(Flight flight : flights)
        {
            source.add(flight);
            if(source.size() == 2)
            {
                ObservableList<Flight> observableList = FXCollections.observableArrayList(source);
                TableView<Flight> flightTableView = new TableView<>();
                createTable(flightTableView);
                flightTableView.setItems(observableList);
                flightTables.add(flightTableView);
                flightsObservableList.add(observableList);
                source.clear();
            }
        }
        if(source.size() == 1)
        {
            ObservableList<Flight> observableList = FXCollections.observableArrayList(source);
            TableView<Flight> flightTableView = new TableView<>();
            createTable(flightTableView);
            flightTableView.setItems(observableList);
            flightTables.add(flightTableView);
            flightsObservableList.add(observableList);
            source.clear();
        }
        index = 0;
        putFlightTable(index); // ati zis ca nu da eroare :)
    }

    private void putFlightTable(int index)
    {
        vbox.getChildren().clear();
        if(index < flightTables.size()) {
            vbox.getChildren().add(flightTables.get(index));
            currentPageLabel.setText((index + 1) + "/" + flightTables.size());
        }
        //MessageBox.showSuccessMessage(null, "Page "+index+" loaded!");
        //MessageBox.showSuccessMessage(null, "Nr pages: "+flightTables.size());
    }

    @FXML
    void prevClicked(){
        if(index > 0)
        {
            index --;
            putFlightTable(index);
        }
    }

    @FXML
    void nextClicked(){
        if(index<flightTables.size()-1)
        {
            index ++;
            putFlightTable(index);
        }
    }
}