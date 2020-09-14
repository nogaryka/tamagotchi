package controller;

import data.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.Main;

import java.io.IOException;

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


}
