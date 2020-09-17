package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import data.Data;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Main;
import model.Food;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
        //imageView_viewPet.setImage(new Image(Main.pathToPetImage));
        label_satietyStatus.textProperty().bind(Main.pet.getSatiety().asString());
        label_humorStatus.textProperty().bind(Main.pet.getHumor());
        long delayAndPeriod = 60 / Main.pet.getCatabolismRatePerMinute();
        Data.statDecay.scheduleAtFixedRate(Main.pet, delayAndPeriod, delayAndPeriod, TimeUnit.SECONDS);
    }

    @FXML
    public void feed() {
        if(Main.pet.getType().getTitle().equals(Data.CORPSE))
        {
            button_feed.setDisable(false);
            return;
        }
        if (comboBox_foodList.getSelectionModel().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Нужно выбрать еду для ващего питомца");
            return;
        }
        Main.pet.eat(comboBox_foodList.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    public void logOff() throws JsonProcessingException {
        String jsonPet = Data.objectMapper.writeValueAsString(Main.pet);
        String jsonCurrentDateTime = Data.objectMapper.writeValueAsString(LocalDateTime.now());
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(Data.PATH_TO_LOAD_FILE))) {
            bw.write(jsonPet);
            bw.newLine();
            bw.write(jsonCurrentDateTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.exit();
    }
}
