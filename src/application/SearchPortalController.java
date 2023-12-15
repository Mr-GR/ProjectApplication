package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchPortalController implements Initializable{
	@FXML 
	private TableView<Flight> table; 
	
    @FXML
    private TableColumn<Flight, String> ACTable;

    @FXML
    private TableColumn<Flight, Integer> ScheduledArrival;

    @FXML
    private TextField ArrivalCity;

    @FXML
    private TextField ArrivalDate;

    @FXML
    private TableColumn<Flight, String> DCTable;

    @FXML
    private TableColumn<Flight, Integer> ScheduledDeparture;

    @FXML
    private TextField DepartureCity;

    @FXML
    private TextField DepartureDate;

    @FXML
    private TableColumn<Flight, Integer> FlightNumber;

    @FXML
    private TableColumn<Flight, Float> PriceTable;

    @FXML
    private TableColumn<Flight, ArrayList<Integer>> SeatTable;
    
    @FXML
    private Button BackToPortal;
    
    @FXML 
    private Button Search;
    
    @FXML
    private Button Book;
    
    @FXML
    private TextField TextFlightNo; 
    
    private ObservableList<Flight> flightList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		ACTable.setCellValueFactory(new PropertyValueFactory<>("arrivalCity")); 
		ScheduledArrival.setCellValueFactory(new PropertyValueFactory<>("Schedule_Arrival"));
		DCTable.setCellValueFactory(new PropertyValueFactory<>("DepartureCity"));
		ScheduledDeparture.setCellValueFactory(new PropertyValueFactory<>("Schedule_Departure"));
		FlightNumber.setCellValueFactory(new PropertyValueFactory<>("flightNo"));
		PriceTable.setCellValueFactory(new PropertyValueFactory<>("TicketPrice"));
		
		table.setItems(flightList);
		
		
		
		
		BackToPortal.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LoginMethods.changeScene(event, "FlightPortal.fxml", "Coral Blue Air Portal");
			}
		});
		
	//	Search.setOnAction(new EventHandler<ActionEvent>() {
	//		@Override
	//		public void handle(ActionEvent event) {
	//			DBFlight.searchFlights(event, DepartureCity.getText(), ArrivalCity.getText());
	//		}
	//	});
		
		
		Book.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FlightMethods.changeSceneBooking(event, "FinalizeBooking.fxml", "Finalize Booking", TextFlightNo.getText());
		}
			
		});
	
				
	}
	
	@FXML
	public void handleSearch(ActionEvent event) {
	    // Get the search parameters from text fields
	    String departureCity = DepartureCity.getText();
	    String arrivalCity = ArrivalCity.getText();

	    // Perform the search in the database
	    ObservableList<Flight> searchResults = FlightMethods.searchFlights(departureCity, arrivalCity);

	    // Set the items to the table
	    table.setItems(searchResults); 
	}
	

}
