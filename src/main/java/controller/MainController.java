package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import data.Data;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import main.Main;
import model.Food;

import javax.swing.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
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
        imageView_viewPet.imageProperty().bind(Main.imageProperty);
        label_satietyStatus.textProperty().bind(Main.pet.getSatiety().asString());
        label_humorStatus.textProperty().bind(Main.pet.getHumor());
        long delayAndPeriod = 60 / Main.pet.getCatabolismRatePerMinute();
        Data.statDecay.scheduleAtFixedRate(Main.pet, delayAndPeriod, delayAndPeriod, TimeUnit.SECONDS);
    }

    @FXML
    public void feed() {
        if (Main.pet.getType().getTitle().equals(Data.CORPSE)) {
            button_feed.setDisable(false);
            return;
        }
        if (comboBox_foodList.getSelectionModel().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Нужно выбрать еду для ващего питомца");
            return;
        }
        Main.pet.eat(comboBox_foodList.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    public void logOff() throws JsonProcessingException {
        Main.exit();
    }
}
