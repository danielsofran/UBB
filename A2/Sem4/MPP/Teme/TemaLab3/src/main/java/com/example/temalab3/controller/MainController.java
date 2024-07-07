package com.example.temalab3.controller;

import com.example.temalab3.domain.Show;
import com.example.temalab3.domain.User;
import com.example.temalab3.exceptions.ServiceException;
import com.example.temalab3.service.ShowService;
import com.example.temalab3.service.TicketService;
import com.example.temalab3.ui.GUIMaker;
import com.example.temalab3.ui.MessageBox;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class MainController {
    private Stage stage;
    private User user;
    private ShowService showService;
    private TicketService ticketService;

    ObservableList<Show> listSpectacole = FXCollections.observableArrayList();
    ObservableList<Show> listArtisti = FXCollections.observableArrayList();

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button btnCauta;

    @FXML
    private Button btnCumpara;

    @FXML
    private Button btnLogout;

    @FXML
    private TableView<Show> tableSpectacole;

    @FXML
    private TableView<Show> tableArtisti;

    @FXML
    private TableColumn<Show, String> colT1Nume;

    @FXML
    private TableColumn<Show, LocalDate> colT1Data;

    @FXML
    private TableColumn<Show, String> colT1Loc;

    @FXML
    private TableColumn<Show, Integer> colT1LocuriDisponibile;

    @FXML
    private TableColumn<Show, Integer> colT1LocuriVandute;

    @FXML
    private TableColumn<Show, String> colT2Nume;

    @FXML
    private TableColumn<Show, String> colT2Loc;

    @FXML
    private TableColumn<Show, LocalTime> colT2Ora;

    @FXML
    private TableColumn<Show, Integer> colT2LocuriDisponibile;

    public void setData(ShowService service, User user, TicketService ticketService, Stage stage)
    {
        this.showService = service;
        this.user = user;
        this.ticketService = ticketService;
        this.stage = stage;

        listSpectacole.setAll(showService.findAllArtisti());
    }

    @FXML
    public void initialize(){
        tableArtisti.setItems(listArtisti);
        tableSpectacole.setItems(listSpectacole);

        init_cols();
        init_color();
    }

    public void cumpara(){
        Show show = tableArtisti.getSelectionModel().getSelectedItem();
        if(show == null) {
            MessageBox.showErrorMessage(null, "Nu ati selectat niciun spectacol din al doilea tabel!");
            return;
        }
        try {
            GUIMaker.openNewWindow("cumpara.fxml", "Cumpara bilet", (fxmlLoader, stage) -> {
                TicketController controller = fxmlLoader.getController();
                controller.setData(show, ticketService, stage);
            });
        }
        catch (IOException ex){
            MessageBox.showErrorMessage(null, "Eroare la deschiderea ferestrei!\n" + ex.getMessage());
        }
    }

    public void cauta() {
        LocalDate date = datePicker.getValue();
        try{
            listArtisti.setAll(showService.findShowsOnDate(date));
        }
        catch (ServiceException ex){
            MessageBox.showErrorMessage(null, ex.getMessage());
        }
    }

    public void logout() {
        stage.close();
    }

    void init_color(){
        tableArtisti.setRowFactory(tv -> new TableRow<Show>() {
            @Override
            protected void updateItem(Show item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && item.getAvailableSeats() == 0)
                    setStyle("-fx-background-color: rgba(255,0,0,0.3);");
                else setStyle("");
            }
        });

        tableSpectacole.setRowFactory(tv -> new TableRow<Show>() {
            @Override
            protected void updateItem(Show item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && item.getAvailableSeats() == 0)
                    setStyle("-fx-background-color: rgba(255,0,0,0.3);");
                else setStyle("");
            }
        });
    }

    void init_cols(){
        colT1Nume.setCellValueFactory(cellData -> {
            return new ObservableValueBase<String>() {
                @Override
                public String getValue() {
                    return cellData.getValue().getArtist().getName();
                }
            };
        });
        colT1Data.setCellValueFactory(cellData -> {
            return new ObservableValueBase<LocalDate>() {
                @Override
                public LocalDate getValue() {
                    return cellData.getValue().getDate();
                }
            };
        });
        colT1Loc.setCellValueFactory(cellData -> {
            return new ObservableValueBase<String>() {
                @Override
                public String getValue() {
                    return cellData.getValue().getLocation();
                }
            };
        });
        colT1LocuriDisponibile.setCellValueFactory(cellData -> {
            return new ObservableValueBase<Integer>() {
                @Override
                public Integer getValue() {
                    return cellData.getValue().getAvailableSeats();
                }
            };
        });
        colT1LocuriVandute.setCellValueFactory(cellData -> {
            return new ObservableValueBase<Integer>() {
                @Override
                public Integer getValue() {
                    return cellData.getValue().getSoldSeats();
                }
            };
        });

        colT2Nume.setCellValueFactory(cellData -> {
            return new ObservableValueBase<String>() {
                @Override
                public String getValue() {
                    return cellData.getValue().getArtist().getName();
                }
            };
        });
        colT2Loc.setCellValueFactory(cellData -> {
            return new ObservableValueBase<String>() {
                @Override
                public String getValue() {
                    return cellData.getValue().getLocation();
                }
            };
        });
        colT2Ora.setCellValueFactory(cellData -> {
            return new ObservableValueBase<LocalTime>() {
                @Override
                public LocalTime getValue() {
                    return cellData.getValue().getBeginTime();
                }
            };
        });
        colT2LocuriDisponibile.setCellValueFactory(cellData -> {
            return new ObservableValueBase<Integer>() {
                @Override
                public Integer getValue() {
                    return cellData.getValue().getAvailableSeats();
                }
            };
        });
    }
}