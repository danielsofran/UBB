package app.template;

import app.template.controller.StaffController;
import app.template.controller.TableController;
import app.template.models.Table;
import app.template.orm.ConnectionManager;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ConnectionManager connectionManager = new ConnectionManager("MAP_ComenziRestaurante");
        Service service = new Service(connectionManager);

        GUIMaker.refreshWindow("staff.fxml", "Staff", stage, fxmlLoader -> {
            StaffController controller = fxmlLoader.getController();
            controller.setService(service);
            service.addObserver(controller);
        });

        List<Table> tables = service.getAllTables();
        tables.forEach(table -> {
            try {
                GUIMaker.openNewWindow("table.fxml", "Table "+table.getId(), fxmlLoader -> {
                    TableController controller = fxmlLoader.getController();
                    controller.setData(service, table);
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public static void main(String[] args) {
        launch();
    }
}