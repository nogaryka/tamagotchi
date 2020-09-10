import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;


public class Main extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/MainView.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.show();
    }
}
