package application;
//Ricardo Ortega 12/7

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CreateAccountController implements Initializable {

		private Button createAccount;
		@FXML
		private Button login;
		@FXML
		private TextField firstName;
		@FXML
		private TextField lastName;
		@FXML
		private TextField username;
		@FXML
		private TextField password;
		@FXML
		private TextField email;
		@FXML
		private TextField address;
		@FXML
		private TextField zip;
		@FXML
		private TextField state;
		@FXML
		private TextField ssn;
		@FXML
		private TextField securityQuestion;
		@FXML
		private TextField answer;
		
	
		public void initialize(URL url, ResourceBundle rb) { 
		createAccount.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				try {
					if(!firstName.getText().trim().isEmpty() && !lastName.getText().trim().isEmpty() && !address.getText().trim().isEmpty() && !zip.getText().trim().isEmpty() && !state.getText().trim().isEmpty() && !username.getText().trim().isEmpty() && !password.getText().trim().isEmpty() 
							&& !email.getText().trim().isEmpty() && !ssn.getText().trim().isEmpty() && !securityQuestion.getText().trim().isEmpty() && !answer.getText().trim().isEmpty()) {
						LoginMethods.insertUser(event, firstName.getText(), lastName.getText(), address.getText(), zip.getText(), state.getText(), username.getText(), password.getText(), 
								email.getText(), ssn.getText(), securityQuestion.getText(), answer.getText());
						
					}else {
						System.out.println("Please fill in all information!");
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("Please fill in all information to sign up!");
						alert.show();
						
				}
			}catch(Exception e) {
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				 alert.setContentText("Error during sign-up. Please try again.");
                 alert.show();
			}
			
			}
			});
		
		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				LoginMethods.changeScene(event,"Login.fxml", "Rocket Red Air Login" );
			}
		});
		}
}

