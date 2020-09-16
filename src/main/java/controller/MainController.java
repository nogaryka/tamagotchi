package controller;

import data.Data;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Main;
import model.Food;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MainController {
    @FXML
    public Label label_humorStatus;
    @FXML
    public Label label_satietyStatus;
    @FXML
    public Button button_feed;
    @FXML
    public Button button_logOff;
    @FXML
    public ComboBox comboBox_foodList;
    @FXML
    public ImageView imageView_viewPet;

    @FXML
    private void initialize() {
        comboBox_foodList.setItems(FXCollections.observableArrayList(Arrays.stream(Food.values())
        .map(Food::getTitle)
        .collect(Collectors.toList())));
        imageView_viewPet.setImage(new Image(Main.pathToPetImage));
        label_satietyStatus.textProperty().bind(Main.pet.getSatiety().asString());
        label_humorStatus.textProperty().bind(Main.pet.getHumor());
    }

    @FXML
    public void feed() {
        Main.pet.eat(comboBox_foodList.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    public void logOff() {
    }
}
