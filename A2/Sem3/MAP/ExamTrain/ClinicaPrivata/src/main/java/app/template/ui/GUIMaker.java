package app.template.ui;

import app.template.Main;
import app.template.orm.classparser.PropertyParser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class GUIMaker {
    public static<E> void createTable(Class<E> the_class, TableView<E> t, String... hided_fields){
        List<TableColumn<E, String>> columns = new ArrayList<>();
        for(Field f : the_class.getDeclaredFields())
        {
            if(Arrays.stream(hided_fields).noneMatch(hf -> hf.equals(f.getName()))) {
                TableColumn<E, String> column = new TableColumn<>();
                column.setCellValueFactory(new PropertyValueFactory<>(f.getName()));
                column.setText(f.getName());
                columns.add(column);
            }
        }
        t.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        t.getColumns().setAll(columns);
    }

    public static <E> List<Pair<Label, TableView<E>>> createGroupedTable(Class<E> the_class, List<Pair<String, List<E>>> data, String... hided_fields)
    {
        List<Pair<Label, TableView<E>>> rez = new LinkedList<>();
        data.forEach(pair -> {
            Label label = new Label();
            label.setText(pair.getKey());
            TableView<E> tableView = new TableView<>();
            createEntityTable(the_class, tableView, hided_fields);
            rez.add(new Pair<>(label, tableView));
        });
        return rez;
    }

    public static<E> void createEntityTable(Class<E> the_class, TableView<E> t, String... hided_fields){
        List<TableColumn<E, String>> columns = new ArrayList<>();
        PropertyParser<Class<E>> parser = new PropertyParser<>(the_class);
        for(Field f : parser.getFields())
        {
            if(Arrays.stream(hided_fields).noneMatch(hf -> hf.equals(f.getName()))) {
                TableColumn<E, String> column = new TableColumn<>();
                column.setCellValueFactory(new PropertyValueFactory<>(f.getName()));
                column.setText(f.getName());
                columns.add(column);
            }
        }
        t.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        t.getColumns().setAll(columns);
    }

    public static void openNewWindow(String fxml, String title, Consumer<FXMLLoader> controllerSetter) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();

        controllerSetter.accept(fxmlLoader);

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void refreshWindow(String fxml, String title, Stage stage, Consumer<FXMLLoader> controllerSetter) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());

        controllerSetter.accept(fxmlLoader);

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}
