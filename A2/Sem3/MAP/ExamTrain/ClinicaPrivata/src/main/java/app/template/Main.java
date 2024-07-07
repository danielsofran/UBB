package app.template;

import app.template.controller.MedicController;
import app.template.controller.SectieController;
import app.template.controller.TableButtonController;
import app.template.models.Medic;
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
        ConnectionManager connectionManager = new ConnectionManager("MAP_ClinicaPrivata");
        Service service = new Service(connectionManager);

        GUIMaker.refreshWindow("sectii.fxml", "Sectii", stage, fxmlLoader -> {
            SectieController controller = fxmlLoader.getController();
            controller.setService(service);
        });

        List<Medic> medici = service.getAllMedici();
        medici.forEach(medic -> {
            try {
                GUIMaker.openNewWindow("medic.fxml", medic.getNume(), fxmlLoader -> {
                    MedicController controller = fxmlLoader.getController();
                    controller.setData(service, medic);
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