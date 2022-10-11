package Pruebas;

import java.sql.SQLException;
import java.sql.Statement;

import connec.Connect;
import windows.Login;
import windows.RegisterStudent;

public class test {
	static Statement statement;

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException {

		//Login l = new Login();
		//RaStudentView r = new RaStudentView();
		
		RegisterStudent r = new RegisterStudent();
		//Connect c = new Connect();
		//c.login();
		
		//String dni, String nombre, String apellidos, String email, String fecha_nac, String foto, int telefono
		//Student a = new Student("31010101K","Perro","Sanche","perro@sanche.es","1990-10-5","sinFoto", 648950);
		//ConnectarBD c=new ConnectarBD();
		//c.insertStudent(a);
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
