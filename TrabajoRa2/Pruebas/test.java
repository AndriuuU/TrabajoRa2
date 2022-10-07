package Pruebas;

import java.sql.Statement;

import connec.ConnectarBD;

public class test {
	static Statement statement;

	public static void main(String[] args) {
		
		ConnectarBD c=new ConnectarBD();
		c.insert();
		c.view();
//		String host = "jdbc:mysql://localhost:3306/trabajora2";
//	    String user = "root";
//	    String pass = "";
//
//	    try {
//	    	Connection connection = DriverManager.getConnection(host, user, pass);
//	    	statement = connection.createStatement();
//	        System.out.println("Connection success!");
//	    } catch (SQLException ex) {
//	        System.out.println("Connection failed!");
//	    }
	}

}
