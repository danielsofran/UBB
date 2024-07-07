package com.example.temalab3;

import com.example.temalab3.controller.LoginController;
import com.example.temalab3.controller.MainController;
import com.example.temalab3.repository.*;
import com.example.temalab3.service.ShowService;
import com.example.temalab3.service.TicketService;
import com.example.temalab3.service.UserService;
import com.example.temalab3.tests.DbTest;
import com.example.temalab3.ui.GUIMaker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        UserRepository userRepository = new UserRepoDB("bd.config");
        ShowRepository showRepository = new ShowRepoDB("bd.config");
        TicketRepository ticketRepository = new TicketRepoDB("bd.config");

        UserService userService = new UserService(userRepository);
        ShowService showService = new ShowService(showRepository);
        TicketService ticketService = new TicketService(ticketRepository, showRepository);

        GUIMaker.refreshWindow("login.fxml", "hello!", stage, fxmlLoader -> {
            LoginController controller = fxmlLoader.getController();
            controller.setData(userService, showService, ticketService, stage);
        });
    }

    public static void main(String[] args) {
//        DbTest.main(args);
        launch();
    }
}