package edu.gsu.azure.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cnn.MyCreds;


public class AzureSql {

public static void main(String[] args) {
		
		System.out.println("Performing setup..");
		MyCreds MyCreds = new cnn.MyCreds(null, null); //must null in order to work 
		String userName = MyCreds.getUsername();
		String userPassword = MyCreds.getPassword();
		String ccnString = 
				"jdbc:sqlserver://applicationprogramming.database.windows.net:1433;" 
						+ "database=applicationdb;"
						+ "user=" + userName + ";"
						+ "password=" + userPassword + ";"
						+ "encrypt=true;"
						+ "trustSerCertificate=false;"
						+ "loginTimeout=30;";
		//use the SelectAzure method to call the data
		AzureSql azure = new AzureSql();
		System.out.println("Connecting.."); //Show the progress of the connection 
	//test:
		String sql = "Select CustomerID, FirstName, MiddleName From SalesLT.Customer; ";
		azure.SelectAzureSQL(userName, userPassword, sql, ccnString);
		
	}
	private void SelectAzureSQL(String userName, String userPassword, String sql, String cnnStr) {
		//Get a result set from Azure and display 
		System.out.println("selecting data..");
		ResultSet resultSet = null; //
		try {
			Connection cnn = DriverManager.getConnection(cnnStr);
				Statement statement =  cnn.createStatement();{
					resultSet = statement.executeQuery(sql);
					while(resultSet.next()) {
						System.out.println(resultSet.getString(1) +
								", " + resultSet.getString(2) +
									", " + resultSet.getString(3));
					}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();//throw out the error output 	
		}
	}
	//hard code to insert information  
		private void ExecuteAzureSQL(String userName, String userPassword, String sql, String cnnStr) {
			System.out.println("Executing SQL statement...");
			//try method 
			try {
				Connection cnn = DriverManager.getConnection(cnnStr);
					PreparedStatement statement = cnn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);{
						ResultSet resultSet = null;
						statement.execute();
						resultSet = statement.getGeneratedKeys();
						while(resultSet.next()) {
							System.out.println("Key(s): " + resultSet.getString(1));
						}
					}
			}
		catch(SQLException e) {
				e.printStackTrace();
			}
		}
}
