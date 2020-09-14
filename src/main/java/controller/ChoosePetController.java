package controller;

import data.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import main.Main;
import model.Pet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChoosePetController {
    @FXML
    private ListView listView_listPets;

    @FXML
    private void initialize() {
        listView_listPets.setItems(FXCollections.observableArrayList(Data.listPets));
    }

    @FXML
    private void onMouseClicked() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(Data.MAIN_VIEW));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}
