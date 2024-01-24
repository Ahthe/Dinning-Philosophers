/*
Dining-Philosophers Problem Simulator
Name: Ahthesham Ali Syed
Date: 12/10/2023
*/

package stars.dinningphilosophers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}