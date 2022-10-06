package connec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectarBD {
	static Statement statement;
	static Connection connection;
	private String host = "jdbc:mysql://localhost:3306/trabajora2";
	private String user = "admin";
	private String pass = "admin";
	private String tabla="profesor";
	private String dni="414151J", nombre="Andres", apellidos="Molina", email="andres@gmail.com";
	
	public ConnectarBD() {
		
		try {
			connection = DriverManager.getConnection(host, user, pass);
			statement = connection.createStatement();
			System.out.println("Connection success!");
		} catch (SQLException ex) {
			System.out.println("Connection failed!");
		}
	}
	
	//for inserting data
	public void insert(){
	    try{
	        String insertquery = "INSERT INTO 'tablename' VALUES('value1', 'value2', 'value3', 'value4')";
	        String remplazar=insertquery.replace("tablename", tabla).replace("value1", dni).replace("value2", nombre).replace("value3",apellidos).replace("value4", email);
	        System.out.println(remplazar);
	        statement.executeUpdate(remplazar);
	        
	        System.out.print("Inserted");
	        
	    } catch(Exception e){
	        System.out.print("Not Inserted");
	    }
	}

	 //for viewing data
	 public void view(){
	    try {
	        String insertquery = "select * from `table_name` where field = 'value1'";
	        ResultSet result = statement.executeQuery(insertquery);
	        if(result.next()){
	            System.out.println("Value " + result.getString(2));
	            System.out.println("Value " + result.getString(3));
	        }
	    } catch (SQLException ex) {
	        System.out.println("Problem To Show Data");
	    }
	 }

	 //to update data
	 public void update(){
	    try {
	        String insertquery = "UPDATE `table_name` set `field`='value',`field2`='value2' WHERE field = 'value'";
	        statement.executeUpdate(insertquery);
	        System.out.println("Updated");
	    } catch (SQLException ex) {
	        System.out.println(ex.getMessage());
	    }
	 }

	//to delete data
	public void delete(){
	     try {
	        String insertquery = "DELETE FROM `table_name` WHERE field = 'value'";
	        statement.executeUpdate(insertquery);
	        System.out.println("Deleted");
	    } catch (SQLException ex) {
	        System.out.println(ex.getMessage());
	    }
	 }
}
