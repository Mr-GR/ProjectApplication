package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class LoginController implements Initializable {

	
	@FXML
	private Button LoginButton;
	
	@FXML 
	private Button NewUserButton;
	
	@FXML 
	private Button ForgotButton; 
	
	@FXML
	private TextField tf_username;
	
	@FXML
	private TextField tf_password;


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		LoginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			LoginMethods.logInUser(event, tf_username.getText(), tf_password.getText());
				
		}
		});
	
	
		NewUserButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			LoginMethods.changeScene(event, "CreateAccount.fxml", "Sign UP!");
			}
		});
		
		
		
		ForgotButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
		    LoginMethods.changeScene(event, "ForgotScene.fxml", "Retrive Question");
			}
		});
		
		
		
		
	}
	


}