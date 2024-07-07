package app.template;

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
        ConnectionManager connectionManager = new ConnectionManager("MAPExamen");
        Servi
        ce service = new Service(connectionManager);

        GUIMaker.refreshWindow("table_and_header.fxml", "Title", stage, fxmlLoader -> {
            TableButtonController controller = fxmlLoader.getController();
            controller.setData(service);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}