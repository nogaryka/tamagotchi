import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        //loader.setController(new controller.Controller());
        loader.setLocation(getClass().getResource("/view/MainView.fxml"));
        //controller.Controller controller = loader.getController();
        //URL xmlUrl = getClass().getResource("/MainView.fxml");
        //loader.setLocation(xmlUrl);
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.show();
    }
}
