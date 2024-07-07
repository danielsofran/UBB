package app.template.controller;

import app.template.exceptions.MyException;
import app.template.models.MenuItem;
import app.template.models.Table;
import app.template.service.Service;
import app.template.ui.GUIMaker;
import app.template.ui.MessageBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class TableController {
    Service service;
    Table table;

    List<TableView<MenuItem>> menuTables = new LinkedList<>();

    @FXML
    private Button btn;

    @FXML
    private VBox vbox;

    public void setData(Service service, Table table)
    {
        this.service = service;
        this.table = table;

        List<Pair<String, List<MenuItem>>> menuItems = service.getMenu();
        List<Pair<Label, TableView<MenuItem>>> menu = GUIMaker.createGroupedTable(MenuItem.class, menuItems, "id", "category");
        menu.forEach(labelTableViewPair -> {
            Label label = labelTableViewPair.getKey();
            TableView<MenuItem> tableView = labelTableViewPair.getValue();
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            vbox.getChildren().add(label);
            vbox.getChildren().add(tableView);
            menuTables.add(tableView);
        });
    }

    @FXML
    public void initialize(){

    }

    @FXML
    void btnClicked() {
        List<MenuItem> menuItems = new LinkedList<>();
        menuTables.forEach(table -> {
            List<MenuItem> items = table.getSelectionModel().getSelectedItems();
            menuItems.addAll(items);
        });
        try{
            service.order(table, menuItems);
        }
        catch (MyException ex)
        {
            MessageBox.showErrorMessage(null, ex.getMessage());
        }
    }
}