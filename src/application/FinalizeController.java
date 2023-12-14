package application;

import java.net.URL;
import java.util.ResourceBundle;
//import edu.gsu.gui.DBFlight;
//import edu.gsu.gui.DBUtilites;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FinalizeController implements Initializable {
	
	@FXML
	private Button Return;
	
	@FXML 
	private Label flightID;
	
	@FXML 
	private TextField tf_username;
	
	@FXML
	private TextField tf_FlightID; 
	
	@FXML
	private Button ConfirmButton; 
	
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
				//DBUtilites.changeScene(event, "SearchBooked.fxml", "Search for Flights");
			}
		});
		
		ConfirmButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//DBFlight.bookFlight(event, tf_username.getText(), Integer.valueOf(tf_FlightID.getText()));
			
			}
		});
		
		CancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent event) {
				DBFlight.cancelFlight(event, tf_username.getText(), Integer.valueOf(tf_FlightID.getText()));
		}
		});
		
		
	}


}
