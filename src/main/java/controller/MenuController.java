package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import data.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.Main;
import model.Pet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class MenuController {

    @FXML
    private void startNewGame(ActionEvent event)
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(Data.CHOOSE_PET_VIEW));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }

    @FXML
    private void continueGame() throws JsonProcessingException {
        String jsonPet = "";
        String jsonLastDateTime  = "";
        try(BufferedReader br = new BufferedReader(new FileReader(Data.PATH_TO_LOAD_FILE))) {
            jsonPet = br.readLine();
            jsonLastDateTime = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.pet = Data.objectMapper.readValue(jsonPet, Pet.class);
        LocalDateTime lastDate = Data.objectMapper.readValue(jsonLastDateTime, LocalDateTime.class);
        long count = ChronoUnit.MINUTES.between(lastDate, LocalDateTime.now());
        Main.pet.setSatiety(count * Main.pet.getCatabolismRatePerMinute());
    }

    @FXML
    private void logOff() {
        Main.exit();
    }
}
