package app.template;

import app.template.controller.ClientController;
import app.template.controller.MainController;
import app.template.exceptions.RepositoryException;
import app.template.models.Client;
import app.template.orm.ConnectionManager;
import app.template.orm.exceptions.DataNotFoundException;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import app.template.ui.MessageBox;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ConnectionManager connectionManager = new ConnectionManager("MAPS1_2022");
        Service service = new Service(connectionManager);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        MainController controller = fxmlLoader.getController();
        controller.setService(service);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        List<Client> clients = readClients(service);
        //MessageBox.showSuccessMessage(null, String.valueOf(clients.size()));
        clients.forEach(c -> {
            try {
                GUIMaker.openNewWindow("client.fxml", c.getName(), clientLoader -> {
                    ClientController clientController = clientLoader.getController();
                    clientController.setData(service, c);
                    service.addObserver(clientController);
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private List<Client> readClients(Service service){
        List<Client> rez = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int index = str.indexOf('.');
        if(index > 0)
            str = str.substring(0, index);
        str = str.replace('.', ' ');
        for(String token : str.split(","))
        {
            token = token.trim();
            Long id;
            try {
                id = Long.parseLong(token);
            }
            catch (Exception ex)
            {
                MessageBox.showErrorMessage(null, "Can not parse id "+token+"!");
                continue;
            }
            try {
                Client client = service.getClient(id);
                rez.add(client);
            }
            catch (RepositoryException ex)
            {
                MessageBox.showErrorMessage(null, "Client with id "+id+" not found!");
            }
        }
        return rez;
    }

    public static void main(String[] args) {
        launch();
    }
}