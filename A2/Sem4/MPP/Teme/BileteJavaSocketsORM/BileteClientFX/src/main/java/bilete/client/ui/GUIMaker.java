package bilete.client.ui;

import bilete.client.StartRpcClientFX;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GUIMaker {
    public static void openNewWindow(String fxml, String title, Consumer<FXMLLoader> controllerSetter) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartRpcClientFX.class.getClassLoader().getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();

        controllerSetter.accept(fxmlLoader);

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void openNewWindow(String fxml, String title, BiConsumer<FXMLLoader, Stage> controllerSetter) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartRpcClientFX.class.getClassLoader().getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();

        controllerSetter.accept(fxmlLoader, stage);

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void refreshWindow(String fxml, String title, Stage stage, Consumer<FXMLLoader> controllerSetter) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartRpcClientFX.class.getClassLoader().getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());

        controllerSetter.accept(fxmlLoader);

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
