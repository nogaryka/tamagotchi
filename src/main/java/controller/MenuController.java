package controller;

import data.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.Main;
import model.Pet;

import java.io.*;

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
    private void continueGame(){
        Pet pet;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Data.PATH_TO_LOAD_FILE))) {
            pet = (Pet) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
