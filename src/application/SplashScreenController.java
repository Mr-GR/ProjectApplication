package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class SplashScreenController implements Initializable {

	@FXML
    private StackPane parent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
		
	
	@FXML
	private void close_app(MouseEvent event) {
		System.exit(0);
		
	}
	
}	