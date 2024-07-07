package app.template.controller;

import app.template.models.*;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import app.template.ui.MessageBox;
import app.template.utils.events.ChangeEventType;
import app.template.utils.events.ChangedEvent;
import app.template.utils.observer.MyObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ClientController implements MyObserver<ChangedEvent<Reservation>> {
    Service service;
    Client client;

    ObservableList<Oferta> offers = FXCollections.observableArrayList();

    @FXML
    TableView<Oferta> table;

    public void setData(Service service, Client client)
    {
        this.service = service;
        this.client = client;

        List<Oferta> ofertas = service.getOferte(client);
        //MessageBox.showSuccessMessage(null, String.valueOf(ofertas.size()));
        offers.setAll(ofertas);
    }

    @FXML
    public void btnClicked() throws IOException {
        GUIMaker.openNewWindow("main.fxml", "Hotels for " + client.getName(), fxmlLoader -> {
            MainController controller = fxmlLoader.getController();
            controller.setService(service);
            controller.setClient(client);
        });
    }

    @FXML
    private void initialize(){
        GUIMaker.createTable(Oferta.class, table);
        table.setItems(offers);
    }

    @Override
    public void update(ChangedEvent<Reservation> reservationChangedEvent) {
        if(reservationChangedEvent.getType() == ChangeEventType.ADDED)
        {
            Reservation reservation = reservationChangedEvent.getData();
            Client client = service.getClient(reservation.getClientId());
            if(client.getHobbies() == this.client.getHobbies() && !client.equals(this.client))
            {
                MessageBox.showSuccessMessage(null, "Alert for "+this.client.getName()+": Inca un utilizator cu hobby "+client.getHobbies() +" a facut o rezervare!");
            }
        }
    }
}
