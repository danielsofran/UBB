package app.template;

import app.template.controller.LocalitatiController;
import app.template.controller.RauriController;
import app.template.controller.TableButtonController;
import app.template.orm.ConnectionManager;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ConnectionManager connectionManager = new ConnectionManager("MAP_ANAR");
        Service service = new Service(connectionManager);

        GUIMaker.refreshWindow("rauri.fxml", "Rauri", stage, fxmlLoader -> {
            RauriController controller = fxmlLoader.getController();
            controller.setService(service);
            service.addObserver(controller);
        });

        GUIMaker.openNewWindow("localitati.fxml", "Localitati", fxmlLoader -> {
            LocalitatiController controller = fxmlLoader.getController();
            controller.setService(service);
            service.addObserver(controller);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}