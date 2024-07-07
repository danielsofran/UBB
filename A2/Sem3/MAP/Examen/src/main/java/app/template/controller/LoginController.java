package app.template.controller;

import app.template.exceptions.ControllerException;
import app.template.exceptions.MyException;
import app.template.models.Client;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import app.template.ui.MessageBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    private Service service;

    @FXML
    private TextField userField;

    public void setData(Service service)
    {
        this.service = service;
    }

    public void Login() {
        String loginText = userField.getText();
        try{
            Client clientLogged = service.login(loginText);
            if(clientLogged == null)
                throw new ControllerException("Autentificare invalida");
            GUIMaker.openNewWindow("client.fxml", clientLogged.getNume(), fxmlLoader -> {
                ClientController controller = fxmlLoader.getController();
                controller.setData(service, clientLogged);
                service.addObserver(controller);
            });
        }
        catch (MyException | IOException ex)
        {
            MessageBox.showErrorMessage(null, ex.getMessage());
        }
    }
}