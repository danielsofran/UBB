package app.template.controller;

import app.template.exceptions.ControllerException;
import app.template.exceptions.MyException;
import app.template.exceptions.ServiceException;
import app.template.models.Client;
import app.template.models.Hotel;
import app.template.models.Reservation;
import app.template.models.SpecialOffer;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import app.template.ui.MessageBox;
import app.template.utils.Utils;
import app.template.utils.events.ChangeEventType;
import app.template.utils.events.ChangedEvent;
import app.template.utils.observer.MyObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OferteController {
    Service service;
    Hotel hotel;
    Client client = null;

    ObservableList<SpecialOffer> offers = FXCollections.observableArrayList();

    @FXML
    DatePicker datePickerStart;

    @FXML
    DatePicker datePickerEnd;

    @FXML
    TableView<SpecialOffer> table;

    @FXML
    TextField startRez;

    @FXML
    Spinner<Integer> spnNopti;

    public void setData(Service service, Hotel hotel)
    {
        this.service = service;
        this.hotel = hotel;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    @FXML
    public void btnClicked(){
        LocalDate date1 = datePickerStart.getValue();
        LocalDate date2 = datePickerEnd.getValue();
        List<SpecialOffer> specialOffers = service.getSpecialOffers(date1, date2, hotel);
        offers.setAll(specialOffers);
    }

    @FXML
    public void rezerva(){
        SpecialOffer offer = table.getSelectionModel().getSelectedItem();
        Integer nrNopti = spnNopti.valueProperty().getValue();
        LocalDateTime start;
        try{start=LocalDateTime.parse(startRez.getText(), Utils.DATE_TIME_FORMATTER);}
        catch (Exception ex){
            MessageBox.showErrorMessage(null,"Please enter a valid DateTime!");
            return;
        }
        try {
            service.rezerva(client, hotel, offer, start, nrNopti);
        }
        catch (MyException ex)
        {
            MessageBox.showErrorMessage(null, ex.getMessage());
        }
    }

    @FXML
    private void initialize(){
        GUIMaker.createTable(SpecialOffer.class, table);
        table.setItems(offers);

        spnNopti.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1));
    }
}
