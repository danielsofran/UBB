package app.template.ui;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MessageBox {

    public static void showMessage(Stage owner, Alert.AlertType type, String header, String text){
        Alert message=new Alert(type);
        message.setHeaderText(header);
        message.setContentText(text);
        message.initOwner(owner);
        message.showAndWait();
    }

    public static void showErrorMessage(Stage owner, String text){
        Alert message=new Alert(Alert.AlertType.ERROR);
        message.initOwner(owner);
        message.setTitle("Mesaj eroare");
        message.setContentText(text);
        message.showAndWait();
    }

    public static void showSuccessMessage(Stage owner, String text){
        Alert message=new Alert(Alert.AlertType.CONFIRMATION);
        message.initOwner(owner);
        message.setTitle("Succes!");
        message.setContentText(text);
        message.showAndWait();
    }
}