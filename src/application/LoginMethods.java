package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.ForgotSecurityController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


// We have all the Methods that interact with the Login, New User, and Forgot password. 
// Import LoginMethods class create a new instance and then use the methods to run. 


public class LoginMethods {
	
    public static void changeScene(ActionEvent event, String fxmlFile, String title) {

        Parent root = null;

        try {
            root = FXMLLoader.load(LoginMethods.class.getResource(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.println("Setting scene...");
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        System.out.println("Scene set successfully.");
    }

    private static final String URL = "jdbc:sqlserver://applicationprogramming.database.windows.net:1433;database=applicationdb;";

    public static Connection establishConnection() throws SQLException {
        return DriverManager.getConnection(URL, "Obsidian", "SolidState?");
    }

    public static void insertUser(ActionEvent event, String firstName, String lastName, String address, String zipCode,
            String state, String Username, String Password, String email, String ssn, String securityQuestion,
            String securityAnswer) {
        try {
            String insertSql = "INSERT INTO dbo.Users "
                    + "(FirstName, LastName, Address, ZipCode, State, Username, Password, Email, SSN, SecurityQuestion, SecurityAnswer) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            try (Connection connection = establishConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, address);
                preparedStatement.setString(4, zipCode);
                preparedStatement.setString(5, state);
                preparedStatement.setString(6, Username);
                preparedStatement.setString(7, Password);
                preparedStatement.setString(8, email);
                preparedStatement.setString(9, ssn);
                preparedStatement.setString(10, securityQuestion);
                preparedStatement.setString(11, securityAnswer);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Error", "Database Insert Error", e.getMessage(), AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql) {
        try (Connection connection = establishConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void showAlert(String title, String header, String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void logInUser(ActionEvent event, String username, String password) {
        ResultSet resultSet = null;

        try {

            String logInUser = "SELECT Password FROM dbo.Users WHERE Username = ?";

            try (Connection connection = establishConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(logInUser)) {
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();

                if (!resultSet.isBeforeFirst()) {
                    System.out.println("User not found in the database!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("New User! ");
                    alert.show();
                    LoginMethods.changeScene(event, "CreateAccount.fxml", "Sign IP!");
                } else {
                    while (resultSet.next()) {
                        String retrievedPassword = resultSet.getString("Password");
                        if (retrievedPassword.equals(password)) {
                            changeScene(event, "HomePortal.fxml", "Rocket Red Air");
                        } else {
                            System.out.println("Passwords did not match!");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("The provided credentials are incorrect!");
                            alert.show();
                            
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   public static void retrieveQuestion(ActionEvent event, String username) {
        ResultSet resultSet = null;

        try {
            String forgotPassword = "SELECT SecurityQuestion, SecurityAnswer, Password FROM dbo.Users WHERE Username = ?";

            try (Connection connection = establishConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(forgotPassword)) {
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();

                if (!resultSet.isBeforeFirst()) {
                    System.out.println("User not found in the database!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please create a new account!");
                    alert.show();
                    changeScene(event, "CreateAccount.fxml", "Sign Up!");
                } else {
                    if (resultSet.next()) {
                        String securityQuestion = resultSet.getString("SecurityQuestion");
                        changeSceneQuestion(event, "ForgotSecurityQuestion.fxml", "Check Security Question", securityQuestion);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
        	
        }
    } 
   
// method to change scence and bring infromation from perviours 
    public static void changeSceneQuestion(ActionEvent event, String fxmlFileName, String windowTitle,
            String securityQuestion) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginMethods.class.getResource(fxmlFileName));
            Parent root = loader.load();
            if ("ForgotSecurityQuestion.fxml".equals(fxmlFileName)) {
                ForgotSecurityController controller = loader.getController();
                controller.displaySecurityQuestion(securityQuestion);
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(windowTitle);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
 

// Rertive password method needs to be set on action the the correct security answer will provide the password
    public static void retrievePassword(ActionEvent event, String tf_securityAnswer) {
    ResultSet resultSet = null;

    try {

       String CheckAnwser = "SELECT Password, SecurityAnswer FROM dbo.Users WHERE SecurityAnswer = ?";

        try (Connection connection = establishConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(CheckAnwser)) {
        	
        	
            preparedStatement.setString(1, tf_securityAnswer);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            	String retrievedPassword = resultSet.getString("Password");
            	String securityQuestion = resultSet.getString("SecurityAnswer");
            	
            	if (tf_securityAnswer.equals(securityQuestion)) {
            		Alert sucessAlert = new Alert(Alert.AlertType.INFORMATION);
            		sucessAlert.setTitle("Password retrieval");
            		sucessAlert.setHeaderText("Password");
            		sucessAlert.setContentText("Your Password is: " + retrievedPassword);
            		sucessAlert.show();
            	} else {
            		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            		errorAlert.setTitle("Security Incorrect");
            		errorAlert.setHeaderText("Security Alert");
            		errorAlert.setContentText("Security Answer is incorrect!");
            		errorAlert.show();
            	}
       
            } else {
            	
            	Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        		errorAlert.setTitle("FILL INFORMATION");
        		errorAlert.setHeaderText("Information missing");
        		errorAlert.setContentText("Please fill out all information!");
        		errorAlert.show();

           	
            }
                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } finally {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

}