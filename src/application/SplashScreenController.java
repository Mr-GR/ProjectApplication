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

	/*	
		private void showSplashScreen() {
	        // Create a label with a loading message
	        Label loadingLabel = new Label("Loading...");

	        // Customize the appearance of the label as needed
	        loadingLabel.setStyle("-fx-font-size: 24; -fx-text-fill: white;");

	        // Add the label to the StackPane
	        parent.getChildren().add(loadingLabel);

	        // You can implement additional logic such as delays or animations here
	        // For example, you can use PauseTransition for a delay
	        PauseTransition pause = new PauseTransition();
	        pause.setOnFinished(event -> {
	            // Remove the label when the delay is over
	            parent.getChildren().remove(loadingLabel);
	        });
	        pause.play();
	    }
*/
		
	
	@FXML
	private void close_app(MouseEvent event) {
		System.exit(0);
		
	}
	
}	