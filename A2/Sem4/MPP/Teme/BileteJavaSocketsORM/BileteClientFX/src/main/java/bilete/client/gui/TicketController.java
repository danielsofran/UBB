package bilete.client.gui;

import bilete.client.ui.MessageBox;
import bilete.domain.Show;
import bilete.domain.Ticket;
import bilete.services.BileteException;
import bilete.services.IBileteServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TicketController {
    private Stage stage;
    private Show show;
    private IBileteServices server;

    @FXML
    private Spinner<Integer> spnNrLocuri;

    @FXML
    private TextField txtNumeCumparator;

    @FXML
    private void initialize()
    {
        spnNrLocuri.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
    }

    public void setData(Show show, IBileteServices service, Stage stage)
    {
        this.stage = stage;
        this.show = show;
        this.server = service;
    }

    public void cumpara()
    {
        String nume = txtNumeCumparator.getText();
        int nrLocuri = spnNrLocuri.getValue();
        try{
            Ticket ticket = new Ticket(show, nume, nrLocuri);
            server.sellTicket(ticket);
            Platform.runLater(() -> {
                stage.close();
                MessageBox.showErrorMessage(null, "Ticket bought successfully!");
            });
        }
        catch (BileteException e) {
            MessageBox.showErrorMessage(null, e.getMessage());
        }
    }
}
