package controller;

import data.Data;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import main.Main;
import model.Pet;
import model.Type;

import javax.swing.*;
import java.util.Arrays;
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
                .filter(x -> x != Type.CORPSE)
                .map(Type::getTitle)
                .collect(Collectors.toList())));
        listView_listPets.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView_listPets.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> label_chosenPet.setText(newValue));
    }

    @FXML
    private void accept() {
        if (label_chosenPet.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Для продолжения выберете питомца");
            return;
        }
        Main.pet = new Pet(label_chosenPet.getText());
        Main.imageProperty.setValue(new Image(Data.PATH_LIST.get(Main.pet.getType())));
        Main.createStage(Data.MAIN_VIEW);
    }
}
