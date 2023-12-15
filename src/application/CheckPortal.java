package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import application.User;



public class CheckPortal implements Initializable {

	@FXML TableView<Flight> table; 
	
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
    private Button Cancel;
    
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
				LoginMethods.changeScene(event, "HomePortal.fxml", "Rocekt Red Air Portal");
			}
		});
		
		Cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			FlightMethods.changeSceneCancel(event, "CancelPortal.fxml", "Confirm Cancelation", TextFlightNo.getText());
		}
			
		});
	
	}
	
}