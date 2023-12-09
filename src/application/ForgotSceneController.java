package application;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ForgotSceneController implements Initializable  {
	
	@FXML
	public Button retrieveQuestion;
	
	@FXML 
	public Button ReturnLogin; 
	
	@FXML
	public TextField tf_username;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
		retrieveQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LoginMethods.retrieveQuestion(event, tf_username.getText());
			}
		});
		
	
			
		ReturnLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LoginMethods.changeScene(event, "Login.fxml", "Log in!");
			}
				
			
		});
		
	}
}
	
	

