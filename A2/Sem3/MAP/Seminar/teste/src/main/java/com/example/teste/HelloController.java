package com.example.teste;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class HelloController {
    private ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private ListView<String> container;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnRemove;

    @FXML
    public void initialize(){
        list.setAll("a", "aa");
        container.setItems(list);
    }

    public void add(){
        container.getItems().add("added string");
    }

    public void remove(){
        container.getItems().remove("aa");
    }
}