package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import data.Data;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Pet;

import java.io.*;
import java.time.LocalDateTime;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    public static Stage primaryStage;
    public static Pet pet;
    public static ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Data.MENU_VIEW));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Тамагочи");
        primaryStage.show();
        Platform.setImplicitExit(true);
        primaryStage.setOnCloseRequest(e -> {
            try {
                exit();
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
        });
    }

    private static void save() throws JsonProcessingException {
        if(Main.pet != null) {
            String jsonPet = Data.objectMapper.writeValueAsString(Main.pet);
            String jsonCurrentDateTime = Data.objectMapper.writeValueAsString(LocalDateTime.now());
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(Data.PATH_TO_LOAD_FILE))) {
                bw.write(jsonPet);
                bw.newLine();
                bw.write(jsonCurrentDateTime);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String load() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(Data.PATH_TO_LOAD_FILE))) {
            sb.append(br.readLine());
            sb.append("#");
            sb.append(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void exit() throws JsonProcessingException {
        save();
        Data.statDecay.shutdown();
        Platform.exit();
    }

    public static void createStage(String schema) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource(schema));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
