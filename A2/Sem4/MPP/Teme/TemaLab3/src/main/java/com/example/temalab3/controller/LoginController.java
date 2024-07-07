package com.example.temalab3.controller;

import com.example.temalab3.domain.User;
import com.example.temalab3.exceptions.ServiceException;
import com.example.temalab3.service.ShowService;
import com.example.temalab3.service.TicketService;
import com.example.temalab3.service.UserService;
import com.example.temalab3.ui.GUIMaker;
import com.example.temalab3.ui.MessageBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private Stage stage;
    private UserService userService;
    private ShowService showService;
    private TicketService ticketService;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputPassword;

    public void setData(UserService userService, ShowService showService, TicketService ticketService, Stage stage)
    {
        this.userService = userService;
        this.ticketService = ticketService;
        this.showService = showService;
        this.stage = stage;
    }

    public void Login() throws IOException {
        try{
            User user = userService.login(inputName.getText(), inputPassword.getText());
            GUIMaker.openNewWindow("main.fxml", user.getUsername()+"'s Profile", (fxmlLoader, stage) -> {
                MainController controller = fxmlLoader.getController();
                controller.setData(showService, user, ticketService, stage);
            });
            stage.close();
        }
        catch (ServiceException ex){
            MessageBox.showErrorMessage(null, ex.getMessage());
        }
    }
}