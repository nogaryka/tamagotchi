package controller;

import data.Data;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import model.Pet;

public class ChoosePetController {
    @FXML
    private ListView<Pet> listView_listPets;

    @FXML
    private Label label;
    @FXML
    private Button accept;

    @FXML
    private void initialize() {
        listView_listPets.setItems(FXCollections.observableArrayList(Data.listPets));
        listView_listPets.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView_listPets.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> label.setText(newValue.toString()));
    }

    /*@FXML
    private void onMouseClicked() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(Data.MAIN_VIEW));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }*/
}
