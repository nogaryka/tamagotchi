package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import data.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import main.Main;
import model.Pet;
import model.Type;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class MenuController {

    @FXML
    private void startNewGame(ActionEvent event) throws JsonProcessingException {
        String lastDateToJson = null;
        String petToJson = null;
        File file = new File(Data.PATH_TO_LOAD_FILE);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            if(file.length() == 0) {
                Main.createStage(Data.CHOOSE_PET_VIEW);
                return;
            }
            petToJson = br.readLine();
            lastDateToJson = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pet pet =  Data.objectMapper.readValue(Objects.requireNonNull(petToJson), Pet.class);
        LocalDateTime lastDate = Data.objectMapper.readValue(Objects.requireNonNull(lastDateToJson), LocalDateTime.class);
        long count = ChronoUnit.MINUTES.between(lastDate, LocalDateTime.now());
        if(pet.getType() == Type.CORPSE && count < 5)
        {
            JOptionPane.showMessageDialog(null, "Вы еще оплакиваете своего старого питомца");
            return;
        }
        Main.createStage(Data.CHOOSE_PET_VIEW);
    }

    @FXML
    private void continueGame() throws JsonProcessingException {
        File file = new File(Data.PATH_TO_LOAD_FILE);
        if(file.length() == 0) {
            JOptionPane.showMessageDialog(null, "Нет сохраненного питомца");
            return;
        }
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
