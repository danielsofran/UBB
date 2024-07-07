package app.template;

import app.template.controller.LoginController;
import app.template.orm.ConnectionManager;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ConnectionManager connectionManager = new ConnectionManager("MAPExamen");
        Service service = new Service(connectionManager);


        GUIMaker.refreshWindow("login.fxml", "Login", stage, fxmlLoader -> {
            LoginController controller = fxmlLoader.getController();
            controller.setData(service);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}