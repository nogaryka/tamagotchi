package main;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import controller.MainController;
import data.Data;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Pet;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    public static Stage primaryStage;
    public static Pet pet;
    public static String pathToPetImage;

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Data.MENU_VIEW));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Тамагочи");
        primaryStage.show();
        Platform.setImplicitExit(true);
        primaryStage.setOnCloseRequest(e -> {
            exit();
        });
    }

    public static void exit() {
        Data.statDecay.shutdown();
        Platform.exit();
    }
}
