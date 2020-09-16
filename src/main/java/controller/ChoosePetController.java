package controller;

import data.Data;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import jdk.nashorn.internal.objects.ArrayBufferView;
import main.Main;
import model.Pet;
import model.Type;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ChoosePetController {
    @FXML
    private ListView<String> listView_listPets;

    @FXML
    private Label label_chosenPet;
    @FXML
    private Button button_accept;

    @FXML
    private void initialize() {
        listView_listPets.setItems(FXCollections.observableArrayList(Arrays.stream(Type.values())
                .map(Type::getTitle)
                .collect(Collectors.toList())));
        listView_listPets.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView_listPets.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> label_chosenPet.setText(newValue));
    }

    @FXML
    private void accept() {
        if(label_chosenPet.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Для продолжения выберете питомца");
            return;
        }
        Parent root = null;
        Main.pet = new Pet(label_chosenPet.getText());
        Main.pathToPetImage = Data.PATH_LIST.get(Main.pet.getType());
        try {
            root = FXMLLoader.load(getClass().getResource(Data.MAIN_VIEW));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}
