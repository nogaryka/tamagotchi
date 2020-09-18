package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import data.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import main.Main;
import model.Pet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MenuController {

    @FXML
    private void startNewGame(ActionEvent event) {
        Main.createStage(Data.CHOOSE_PET_VIEW);
    }

    @FXML
    private void continueGame() throws JsonProcessingException {
        String saveData = Main.load();
        Main.pet = Data.objectMapper.readValue(saveData.split("#")[0], Pet.class);
        LocalDateTime lastDate = Data.objectMapper.readValue(saveData.split("#")[1], LocalDateTime.class);
        long count = ChronoUnit.MINUTES.between(LocalDateTime.now(), lastDate);
        Main.pet.setSatiety(count * Main.pet.getCatabolismRatePerMinute());
        Main.imageProperty.setValue(new Image(Data.PATH_LIST.get(Main.pet.getType())));
        Main.createStage(Data.MAIN_VIEW);
    }

    @FXML
    private void logOff() throws JsonProcessingException {
        Main.exit();
    }
}
