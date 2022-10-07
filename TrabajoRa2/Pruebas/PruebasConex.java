package Pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PruebasConex {

	public String db = "trabajora2";
	public String url = "jdbc:mysql://localhost:3306/phpmyadmin/" + db;
	public String user = "admin";
	public String pass = "admin";

	public PruebasConex() {
		Conectar();
	}

	public void Conectar() {

//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = null;
//			conn = DriverManager.getConnection("jdbc:mysql://localhost/trabajadora2", "root", " ");
//			System.out.print("Database is connected !");
//			conn.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
