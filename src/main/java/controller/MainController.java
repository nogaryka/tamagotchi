package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    @FXML
    private Button mainButton;

    @FXML
    private void buttonClicked() {
        mainButton.setText("Click me again!");
    }
}
