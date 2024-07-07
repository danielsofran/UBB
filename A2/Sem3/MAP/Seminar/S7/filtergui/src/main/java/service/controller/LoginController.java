package service.controller;

import domain.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceManager;

import java.io.IOException;

public class LoginController {
    private ServiceManager service;

    @FXML
    public Button btnLogin;

    @FXML
    public TextField inputName;

    public void setService(ServiceManager service) {
        this.service = service;
    }

    private void openNewScene(Student student) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/notaView.fxml"));
        AnchorPane root=loader.load();

        NotaController ctrl=loader.getController();
        ctrl.setService(service);
        ctrl.setStudent(student);

        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }

    public void btnLogin_Click() throws IOException {
        String input = inputName.getText();
        Student student = service.findAllStudents().stream().filter(s -> s.getName().equals(input)).findFirst().orElse(null);
        if(student == null)
        {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setContentText("Null");
            a.show();
        }
        openNewScene(student);
    }
}
