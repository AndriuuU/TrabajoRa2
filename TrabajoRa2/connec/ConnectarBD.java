package connec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class ConnectarBD {
	static Statement statement;
	static Connection cc;
	java.sql.PreparedStatement ps;
	private String host = "jdbc:mysql://localhost/school";
	private String user = "root";
	private String pass = "";
	private String tabla="profesor";
	private String dni="414151J", nombre="Andres", apellidos="Molina", email="andres@gmail.com";
	
	public ConnectarBD() {
		
		try {
			cc = DriverManager.getConnection(host, user, pass);
			statement = cc.createStatement();
			System.out.println("Connection success!");
		} catch (SQLException ex) {
			System.out.println("Connection failed!");
		}
	}
	
	//TO INSERT DATA
	public void insert(){
	    try{
	        String query = "INSERT INTO "+tabla+" values('"+dni+"','"+nombre+"','"+apellidos+"','"+email+"');";
	        statement.execute(query);
	        System.out.println("Inserted");
	        
	    } catch(Exception e){
	        System.out.println("Not Inserted");
	    }
	}

	 //TO VIEW DATA
	 public void view(){
	    try {
	        String insertquery = "select * from profesor";
	        ResultSet result = statement.executeQuery(insertquery);
	        if(result.next()){
	            System.out.println("Value " + result.getString(2));
	            System.out.println("Value " + result.getString(3));
	        }
	    } catch (SQLException ex) {
	        System.out.println("Problem To Show Data");
	    }
	 }

	 //TO UPDATE DATA
	 public void update(){
	    try {
	        String insertquery = "UPDATE `table_name` set `field`='value',`field2`='value2' WHERE field = 'value'";
	        statement.executeUpdate(insertquery);
	        System.out.println("Updated");
	    } catch (SQLException ex) {
	        System.out.println(ex.getMessage());
	    }
	 }

	//TO DELETE DATA
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
