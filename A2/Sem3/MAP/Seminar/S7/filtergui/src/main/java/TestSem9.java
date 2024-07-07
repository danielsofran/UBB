import service.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceManager;

public class TestSem9 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/login.fxml"));
        AnchorPane root=loader.load();

        LoginController controller = loader.getController();
        controller.setService(new ServiceManager());

        primaryStage.setScene(new Scene(root, 300, 600));
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
