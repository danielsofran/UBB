package com.example.temalab3.controller;

import com.example.temalab3.domain.Show;
import com.example.temalab3.exceptions.ServiceException;
import com.example.temalab3.service.TicketService;
import com.example.temalab3.ui.MessageBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TicketController {
    private Stage stage;
    private Show show;
    private TicketService service;

    @FXML
    private Spinner<Integer> spnNrLocuri;

    @FXML
    private TextField txtNumeCumparator;

    @FXML
    private Button btnCumpara;

    @FXML
    private void initialize()
    {
        spnNrLocuri.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
    }

    public void setData(Show show, TicketService service, Stage stage)
    {
        this.stage = stage;
        this.show = show;
        this.service = service;
    }

    public void cumpara()
    {
        String nume = txtNumeCumparator.getText();
        int nrLocuri = spnNrLocuri.getValue();
        try{
            service.sellTicket(show, nrLocuri, nume);
            stage.close();
            MessageBox.showErrorMessage(null, "Ticket bought successfully!");
        }
        catch (ServiceException e)
        {
            MessageBox.showErrorMessage(null, e.getMessage());
        }
    }
}
