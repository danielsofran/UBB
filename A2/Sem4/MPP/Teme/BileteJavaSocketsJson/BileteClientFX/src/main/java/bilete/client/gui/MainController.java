package bilete.client.gui;

import bilete.client.ui.GUIMaker;
import bilete.client.ui.MessageBox;
import bilete.domain.Show;
import bilete.domain.Ticket;
import bilete.domain.User;
import bilete.services.BileteException;
import bilete.services.IBileteObserver;
import bilete.services.IBileteServices;
import javafx.application.Platform;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class MainController implements IBileteObserver {
    private Stage stage;
    private User user;
    private IBileteServices server;

    ObservableList<Show> listSpectacole = FXCollections.observableArrayList();
    ObservableList<Show> listArtisti = FXCollections.observableArrayList();

    @FXML
    private DatePicker datePicker;

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

    public void setData(IBileteServices server, User user) throws BileteException {
        this.server = server;
        this.user = user;

        listSpectacole.setAll(server.findAllArtisti());
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
                controller.setData(show, server, stage);
            });
        }
        catch (IOException ex){
            MessageBox.showErrorMessage(null, "Eroare la deschiderea ferestrei!\n" + ex.getMessage());
        }
    }

    public void cauta() {
        LocalDate date = datePicker.getValue();
        try{
            listArtisti.setAll(server.findShowsOnDate(date));
        }
        catch (BileteException ex){
            MessageBox.showErrorMessage(null, ex.getMessage());
        }
    }

    public void logout() throws BileteException {
        server.logout(user, this);
        System.exit(0);
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

    @Override
    public void bileteUpdated(Ticket ticket) throws BileteException {
//        listSpectacole.setAll(server.findAllArtisti());
//        listArtisti.setAll(server.findShowsOnDate(datePicker.getValue()));
        System.out.println("Bilete updated, inainte de runLater");
        Runnable updateBilete = () -> {
            System.out.println("Bilete updated, in runLater");
            for(int i = 0; i < listSpectacole.size(); i++){
                Show show = listSpectacole.get(i);
                if(show.getId().equals(ticket.getShow().getId())){
                    show.setAvailableSeats(show.getAvailableSeats() - ticket.getSeats());
                    show.setSoldSeats(show.getSoldSeats() + ticket.getSeats());
                    listSpectacole.set(i, show);
                }
            }
            for(int i = 0; i < listArtisti.size(); i++){
                Show show = listArtisti.get(i);
                if(show.getId().equals(ticket.getShow().getId())){
                    show.setAvailableSeats(show.getAvailableSeats() - ticket.getSeats());
                    show.setSoldSeats(show.getSoldSeats() + ticket.getSeats());
                    listArtisti.set(i, show);
                }
            }
        };
        //updateBilete.run();
        Platform.runLater(updateBilete);
    }
}