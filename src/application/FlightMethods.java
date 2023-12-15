package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import edu.gsu.gui.CancelPortalController;
import application.LoginMethods;
//import edu.gsu.gui.FinalizeBookingController;
import application.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class FlightMethods {
	
	public static void showAlert(String title, String header, String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }	
	
	private static final String URL = "jdbc:sqlserver://applicationprogramming.database.windows.net:1433;database=applicationdb;";
	
	public static Connection establishConnection() throws SQLException {
		return DriverManager.getConnection(URL, "Obsidian", "SolidState?");
		}
	
	
	public static ObservableList<Flight> searchFlights(String departureCity, String arrivalCity) {
	    ObservableList<Flight> flightList = FXCollections.observableArrayList();

	    try (Connection connection = establishConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM dbo.Fligths WHERE DepartureCity = ? AND ArrivalCity = ?")) {
	        preparedStatement.setString(1, departureCity);
	        preparedStatement.setString(2, arrivalCity);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            Flight flight = new Flight();
	            flight.setFlightNo(Integer.toString(resultSet.getInt("FlightId")));
	            flight.setDepartureCity(resultSet.getString("DepartureCity"));
	            flight.setArrivalCity(resultSet.getString("ArrivalCity"));
	            flight.setSchedule_Departure(Integer.toString(resultSet.getInt("SCHEDULED_DEPARTURE")));
	            flight.setSchedule_Arrival(Integer.toString(resultSet.getInt("SCHEDULED_ARRIVAL")));
	            flight.setTicketPrice(Float.toString(resultSet.getFloat("Price")));
	            
	            // Set other flight attributes...
	            flightList.add(flight);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return flightList;
	}


	    public static void bookFlight(ActionEvent event, String username, int flightID) {
	        // Check if the user has already booked the same flight
	        if (hasUserBookedFlight(username, flightID)) {
	            showAlert("Error", "Booking Error", "You have already booked this flight.", AlertType.ERROR);
	            return;
	        }

	        // Check for date and time conflicts
	        if (hasDateAndTimeConflict(username, flightID)) {
	            showAlert("Error", "Booking Error", "There is a date and time conflict with another booked flight.", AlertType.ERROR);
	            return;
	        }

	        // Check if the flight is full
	        if (isFlightFull(flightID)) {
	            showAlert("Error", "Booking Error", "The flight is full. Cannot book more passengers.", AlertType.ERROR);
	            return;
	        }

	        // Perform the booking and update the database
	        try {
	            String insertBookingSql = "INSERT INTO dbo.Bookings (Username, FlightId) VALUES (?, ?)";
	            try (Connection connection = establishConnection();
	                 PreparedStatement preparedStatement = connection.prepareStatement(insertBookingSql)) {
	            	System.out.println("Establishing Connection");
	                preparedStatement.setString(1, username);
	                preparedStatement.setInt(2, flightID);
	                preparedStatement.executeUpdate();
	                System.out.println("Success");
	                showAlert("Success", "Booking Successful", "You have booked this flight!", AlertType.INFORMATION);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            showAlert("Error", "Booking Error", "Failed to book the flight.", AlertType.ERROR);
	        }
	    }
	    
	    public static void cancelFlight(ActionEvent event, String username, int flightID) {
	        // Check if the user has booked the flight to cancel
	        if (!hasUserBookedFlight(username, flightID)) {
	            showAlert("Error", "Cancellation Error", "You have not booked this flight.", AlertType.ERROR);
	            return;
	        }

	        // Perform update the database
	        try {
	            String cancelBookingSql = "DELETE FROM dbo.Bookings WHERE Username = ? AND FlightId = ?";
	            try (Connection connection = establishConnection();
	                 PreparedStatement preparedStatement = connection.prepareStatement(cancelBookingSql)) {
	                preparedStatement.setString(1, username);
	                preparedStatement.setInt(2, flightID);
	                int rowsAffected = preparedStatement.executeUpdate();

	                if (rowsAffected > 0) {
	                    showAlert("Success", "Cancellation Successful", "Flight cancellation successful.", AlertType.INFORMATION);
	                } else {
	                    showAlert("Error", "Cancellation Error", "Failed to cancel the flight. Please try again.", AlertType.ERROR);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            showAlert("Error", "Cancellation Error", "Failed to cancel the flight.", AlertType.ERROR);
	        }
	    }

	    public static boolean hasUserBookedFlight(String username, int flightID) {
	        try {
	            String checkBookingSql = "SELECT * FROM dbo.Bookings WHERE Username = ? AND FlightID = ?";
	            try (Connection connection = establishConnection();
	                 PreparedStatement preparedStatement = connection.prepareStatement(checkBookingSql)) {
	                preparedStatement.setString(1, username);
	                preparedStatement.setInt(2, flightID);
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    return resultSet.next();
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public static boolean hasDateAndTimeConflict(String username, int flightID) {
	        try {
	            String checkConflictSql = "SELECT * FROM dbo.Bookings b1 " +
	                    "INNER JOIN dbo.Bookings b2 ON b1.Username = b2.Username " +
	                    "WHERE b1.FlightId = ? AND b2.FlightId = ? AND b1.Username = ?";
	            try (Connection connection = establishConnection();
	                 PreparedStatement preparedStatement = connection.prepareStatement(checkConflictSql)) {
	                preparedStatement.setInt(1, flightID);
	                preparedStatement.setInt(2, flightID);
	                preparedStatement.setString(3, username);
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    return resultSet.next();
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public static boolean isFlightFull(int flightID) {
	        try {
	            String countPassengersSql = "SELECT COUNT(*) AS PassengerCount FROM dbo.Bookings WHERE FlightId = ?";
	            try (Connection connection = establishConnection();
	                 PreparedStatement preparedStatement = connection.prepareStatement(countPassengersSql)) {
	                preparedStatement.setInt(1, flightID);
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    if (resultSet.next()) {
	                        int passengerCount = resultSet.getInt("PassengerCount");
	                        
	                        int maxCapacity = 100;
	                        return passengerCount >= maxCapacity;
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return true; 
	        }
	        return false;
	    }
	    
	    public static void changeSceneBooking(ActionEvent event, String fxmlFileName, String windowTitle,
	            String flightNo) {
	        try {
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(LoginMethods.class.getResource(fxmlFileName));
	            Parent root = loader.load();
	            if ("FinalizeBooking.fxml".equals(fxmlFileName)) {
	  //              FinalizeBookingController controller = loader.getController();
	      //          controller.displayflightNo(flightNo);
	            }
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            stage.setScene(new Scene(root));
	            stage.setTitle(windowTitle);
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    
		public static ObservableList<Flight> searchUser(String user) {
		    ObservableList<Flight> flightList = FXCollections.observableArrayList();

		    try (Connection connection = establishConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(
		        		 "SELECT * FROM dbo.Fligths " +
		        		 "JOIN dbo.Bookings ON dbo.Fligths.FlightId = dbo.Bookings.FlightId " +
		                 "WHERE dbo.Bookings.Username = ?")) {
		        preparedStatement.setString(1, user);
		        ResultSet resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            Flight flight = new Flight();
		            flight.setFlightNo(Integer.toString(resultSet.getInt("FlightId")));
		            flight.setDepartureCity(resultSet.getString("DepartureCity"));
		            flight.setArrivalCity(resultSet.getString("ArrivalCity"));
		            flight.setSchedule_Departure(Integer.toString(resultSet.getInt("SCHEDULED_DEPARTURE")));
		            flight.setSchedule_Arrival(Integer.toString(resultSet.getInt("SCHEDULED_ARRIVAL")));
		            flight.setTicketPrice(Float.toString(resultSet.getFloat("Price")));
		            
		            // Set other flight attributes...
		            flightList.add(flight);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return flightList;

		}
		
	    
	    public static void changeSceneCancel(ActionEvent event, String fxmlFileName, String windowTitle,
	            String flightNo) {
	        try {
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(LoginMethods.class.getResource(fxmlFileName));
	            Parent root = loader.load();
	            if ("CancelPortal.fxml".equals(fxmlFileName)) {
	    //            CancelPortalController controller = loader.getController();
	        //        controller.displayflightNo(flightNo);
	            }
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            stage.setScene(new Scene(root));
	            stage.setTitle(windowTitle);
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}


