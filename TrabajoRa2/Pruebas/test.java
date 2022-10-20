	package Pruebas;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import windows.RegisterStudent;


public class test {
	static Statement statement;

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException, ParseException {


		//
		


		
//		AdminWindow ad = new AdminWindow();
//		 Login l = new Login();
		

//		RaStudentView r = new RaStudentView();
//		StudentView s = new StudentView("987654321B");
//		StudentView s = new StudentView("123456789A");
//		TeacherView.dniTeacher = "74544252J";
//		TeacherView t = new TeacherView();
//		AdminWindow ad = new AdminWindow();

//		AdminWindow aw = new AdminWindow();

		RegisterStudent r = new RegisterStudent();
//		RegisterTeacher rt = new RegisterTeacher();
//		Connect c = new Connect();
//		c.checkMatricula("123456324C");
//		c.deleteTeacher("74544252J");
//		c.comboFiller();
//		Register r = new Register();

		// String dni, String nombre, String apellidos, String email, String fecha_nac,
		// String foto, int telefono
		// Student a = new
		// Student("31010101K","Perro","Sanche","perro@sanche.es","1990-10-5","sinFoto",
		// 648950);
		// Connect c=new Connect();
		// c.insertStudent(a);
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
