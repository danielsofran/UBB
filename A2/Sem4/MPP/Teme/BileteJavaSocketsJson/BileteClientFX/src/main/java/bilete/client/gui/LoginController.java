package bilete.client.gui;

import bilete.client.StartRpcClientFX;
import bilete.client.ui.GUIMaker;
import bilete.client.ui.MessageBox;
import bilete.domain.Ticket;
import bilete.domain.User;
import bilete.services.BileteException;
import bilete.services.IBileteObserver;
import bilete.services.IBileteServices;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class LoginController {
    private Stage stage;
    private IBileteServices server;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputPassword;

    public void setData(IBileteServices server, Stage stage)
    {
        this.server = server;
        this.stage = stage;
    }

    public void Login() throws IOException {
        User user = new User(inputName.getText(), inputPassword.getText());
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(StartRpcClientFX.class.getClassLoader().getResource("main.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            MainController controller = fxmlLoader.getController();
            server.login(user, controller);
            controller.setData(server, user);

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    try {
                        controller.logout();
                    } catch (BileteException e) {
                        MessageBox.showErrorMessage(null, e.getMessage());
                    }
                }
            });

            stage.setTitle("Bilete");
            stage.setScene(scene);
            stage.show();

            this.stage.close();
        }
        catch (BileteException ex){
            MessageBox.showErrorMessage(null, ex.getMessage());
        }
    }
}