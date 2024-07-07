package app.template.controller;

import app.template.models.Client;
import app.template.models.Hotel;
import app.template.models.Location;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import app.template.ui.MessageBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;

public class MainController {
    Service service;
    Client client = null;
    ObservableList<Location> locations = FXCollections.observableArrayList();
    ObservableList<Hotel> hotels = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Location> comboBox;

    @FXML
    private TableView<Hotel> table;

//    @FXML
//    private Button btnSelect;

    public void setService(Service service)
    {
        this.service = service;
        locations.setAll(service.getLocations());
        hotels.setAll(service.getHotels());
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    @FXML
    public void initialize(){
        GUIMaker.createTable(Hotel.class, table);
        table.setItems(hotels);
        comboBox.setItems(locations);
    }

    @FXML
    private void cmbItemChoosed(){
        Location location = comboBox.getValue();
        hotels.setAll(service.getHotels(location));
    }

    @FXML
    void btnClicked() throws IOException {
        Hotel hotel = table.getSelectionModel().selectedItemProperty().getValue();
        //MessageBox.showSuccessMessage(null, hotel.getHotelName());
        String title = "Oferte Speciale ";
        if(client != null)
            title += client.getName();
        GUIMaker.openNewWindow("oferte.fxml", title, fxmlLoader -> {
            OferteController controller = fxmlLoader.getController();
            controller.setData(service, hotel);
            controller.setClient(client);
        });
    }
}