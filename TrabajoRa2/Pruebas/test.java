package Pruebas;

import java.sql.Statement;

import windows.Register;

public class test {
	static Statement statement;

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		//Login l = new Login();
		//RaStudentView r = new RaStudentView();
		
		Register r = new Register();
		
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
