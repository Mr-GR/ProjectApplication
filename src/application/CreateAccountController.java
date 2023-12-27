package application;
	
	//Ricardo Ortega 12/7

	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.control.Alert;
	import javafx.fxml.FXML;
	import javafx.fxml.Initializable;
	import java.net.URL;
	import java.util.ResourceBundle;


	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;

	public class CreateAccountController implements Initializable {
		@FXML
		private Button myButton; 
		@FXML
		private Button myButton1;
		@FXML 
		private TextField tf_firstname;
		@FXML 
		private TextField tf_lastname;
		@FXML
		private TextField tf_address;
		@FXML 
		private TextField myZip;
		@FXML 
		private TextField tf_state;
		@FXML
		private TextField tf_username;
		@FXML
		private TextField tf_password;
		@FXML 
		private TextField tf_email;
		@FXML
		private TextField mySSN;
		@FXML
		private TextField tf_security; 
		@FXML
		private TextField tf_anwser; 
		@FXML
		private Label CreateAccount;
		@FXML 
		private Label CreateAccount1;
		
			public void initialize(URL url, ResourceBundle rb) { 
				myButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						
						try {
							if(!tf_firstname.getText().trim().isEmpty() && !tf_lastname.getText().trim().isEmpty() && !tf_address.getText().trim().isEmpty() && !myZip.getText().trim().isEmpty() && !tf_state.getText().trim().isEmpty() && !tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty() 
									&& !tf_email.getText().trim().isEmpty() && !mySSN.getText().trim().isEmpty() && !tf_security.getText().trim().isEmpty() && !tf_anwser.getText().trim().isEmpty()) {
								
								LoginMethods.insertUser(event, tf_firstname.getText(), tf_lastname.getText(), tf_address.getText(), myZip.getText(), tf_state.getText(), tf_username.getText(), tf_password.getText(), 
										tf_email.getText(), mySSN.getText(), tf_security.getText(), tf_anwser.getText());
								
								System.out.println("Account Created");
								Alert alert = new Alert(Alert.AlertType.INFORMATION);
								alert.setContentText("Account Created");
								alert.show();
								
								LoginMethods.changeScene(event, "Login.fxml", "Log In");
							
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
			
			myButton1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					
					LoginMethods.changeScene(event,"Login.fxml", "Rocket Red Air Login" );
				}
			});
			}
	}




