package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CancelPortalController implements Initializable {
	

	@FXML
	private Button Return;
	
	@FXML 
	private Label flightID;
	
	@FXML 
	private TextField tf_username;
	
	@FXML
	private TextField tf_FlightID; 

	@FXML
	private Button CancelButton; 

	public void displayflightNo (String flightNo){
		flightID.setText(flightNo);
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		Return.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LoginMethods.changeScene(event, "HomePortal.fxml", "Rocket Red Air Portal");
			}
		});
		
		
		CancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent event) {
				FlightMethods.cancelFlight(event, tf_username.getText(), Integer.valueOf(tf_FlightID.getText()));
		}
		});
		
	}
}