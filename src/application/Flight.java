package application;

import java.util.ArrayList;
import java.sql.Date;

public class Flight {
	
	private String airlineName;
	private String flightNo;
	private String ArrivalCity;
	private Integer seatCapacity; 
	private String DepartureCity; 
	private String ArrivalDate; 
	private String DepartureDate; 
	private ArrayList<Integer> seat;
	private String Schedule_Departure;
	private String Schedule_Arrival; 
	private String TicketPrice;  
	
	
	public Flight () {
		
	}
	
	//System.out.println(Month + Day + DayofWeek); THE SOLUTION TO YOUR DATE PROBLE ONLY HAVE A DEPATURE DATE THEN SCHEDULE DEPARTURE AND SCHEDULE ARRIVAL
	// ADD PRIVATE INTS AND UPDATE CONTROLLER + DBFLIGHT


	public String getAirlineName() {
		return airlineName;
	}


	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	
	public String getArrivalCity() {
		return ArrivalCity;
	}


	public void setArrivalCity(String arrivalCity) {
		ArrivalCity = arrivalCity;
	}


	public Integer getSeatCapacity() {
		return seatCapacity;
	}


	public void setSeatCapacity(Integer seatCapacity) {
		this.seatCapacity = seatCapacity;
	}


	public String getDepartureCity() {
		return DepartureCity;
	}


	public void setDepartureCity(String departureCity) {
		DepartureCity = departureCity;
	}


	public ArrayList<Integer> getSeat() {
		return seat;
	}


	public void setSeat(ArrayList<Integer> seat) {
		this.seat = seat;
	}



	public String getSchedule_Departure() {
		return Schedule_Departure;
	}

	public void setSchedule_Departure(String Schedule_Departure) {
		this.Schedule_Departure = Schedule_Departure;
	}

	public String getSchedule_Arrival() {
		return Schedule_Arrival;
	}

	public void setSchedule_Arrival(String Schedule_Arrival) {
		this.Schedule_Arrival = Schedule_Arrival;
	}

	public String getDepartureDate() {
		return DepartureDate;
	}

	public void setDepartureDate(String DepartureDate) {
		this.DepartureDate = DepartureDate;
	}

	public String getArrivalDate() {
		return ArrivalDate;
	}

	public void setArrivalDate(String ArrivalDate) {
		this.ArrivalDate = ArrivalDate;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getTicketPrice() {
		return TicketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		TicketPrice = ticketPrice;
	}

}