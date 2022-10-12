package connec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.security.auth.Subject;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import clases.Student;
import clases.Teacher;
import windows.SubjectsView;

public class Connect {
	static Statement statement;
	static Connection cc;
	private String host = "jdbc:mysql://localhost/school";
	private String user = "root";
	private String pass = "";
	private Map<String, String> students;
	private Map<String, String> teachers;
	public Map<String, String> subjectContent = new HashMap<String, String>();
	public Connect() {
		try {
			cc = DriverManager.getConnection(host, user, pass);
			statement = cc.createStatement();
			System.out.println("Connection success!");
		} catch (SQLException ex) {
			System.out.println("Connection failed!");
		}
	}

	// To insert a new alum, if dni already exist throw exception with a joptionpane
	// error
	public void insertStudent(Student s) {
		try {
			String query = "INSERT INTO alumnos values('" + s.getDni() + "','" + s.getNombre() + "','"
					+ s.getApellidos() + "','" + s.getEmail() + "','" + s.getFecha_nac() + "','" + s.getFoto() + "','"
					+ s.getTelefono() + "','" + s.getPassw() + "');";
			statement.execute(query);
			System.out.println("Inserted");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ya existe una cuenta con ese DNI", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Read the map(subjects) and compare with the array of checkbox to check wich one is selected
	//if is selected inser dniStudent and idSubject
	public void insertMatricula(String s) throws SQLException {
		if (SubjectsView.jcSubjects.length > 0) {
			for (JCheckBox c : SubjectsView.jcSubjects) {
				Iterator<Entry<String, String>> iterator = subjectContent.entrySet().iterator();
				if (c.isSelected()) {
					while (iterator.hasNext()) {
						Map.Entry<String, String> entry = iterator.next();
						if (c.getText().toString().equalsIgnoreCase(entry.getValue())) {
							String query = "INSERT INTO matricula values('" + s + "','" + entry.getKey() + "');";
							statement.execute(query);
						}
					}
				}
			}
		}
	}

	// To insert a new teacher, if dni already exist throw exception with a
	// joptionpane error
	public void insertTeacher(Teacher t) {
		try {
			// String dni, String nombre, String apellidos, String email
			String query = "INSERT INTO profesor values('" + t.getDni() + "','" + t.getNombre() + "','"
					+ t.getApellidos() + "','" + t.getEmail() + "','" + t.getPasswd() + "');";
			System.out.println(query);
			statement.execute(query);
			System.out.println("Inserted");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ya existe una cuenta con ese DNI", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// To search users created in the tables student and teacher
	public String searchUser(String logDni, String logPass) throws SQLException {
		students = new HashMap<String, String>();
		teachers = new HashMap<String, String>();
		String queryStudent = "select dni,pass from alumnos";
		String queryTeachers = "select dni,pass from profesor";

		// Insert data in students Map
		ResultSet resultStudent = statement.executeQuery(queryStudent);
		while (resultStudent.next()) {
			students.put(resultStudent.getString("dni"), resultStudent.getString("pass"));
		}
		resultStudent.close();

		// Insert data in teachers Map
		ResultSet resultTeacher = statement.executeQuery(queryTeachers);
		while (resultTeacher.next()) {
			teachers.put(resultTeacher.getString("dni"), resultTeacher.getString("pass"));
		}
		resultTeacher.close();

		// Test if the user exist, and if exist check password,
		// The return value will depend on if the user is a student or teacher
		String estado = "";
		for (String key : students.keySet()) {
			if (logDni.equalsIgnoreCase(key)) {
				for (String valor : students.values()) {
					if (logPass.equalsIgnoreCase(valor))
						estado = "studentaccepted";
					else
						estado = "notaccepted";
				}
			}
		}
		if (estado.equalsIgnoreCase("")) {
			estado = checkTeacher(logDni, logPass);
		}

		// The value returned will be :
		// "" -> if user doesnt exist
		// "notaccepted" -> if the password is wrong
		// "studentaccepted" or "teacheraccepted" if the user and the password is
		// correct
		return estado;
	}

	public String checkTeacher(String logDni, String logPass) {
		String estado = "";
		for (String key : teachers.keySet()) {
			if (logDni.equalsIgnoreCase(key)) {
				for (String valor : teachers.values()) {
					if (logPass.equalsIgnoreCase(valor))
						estado = "teacheraccepted";
					else
						estado = "notaccepted";
				}
			}
		}
		return estado;
	}

	// Fill subjectContent(Map) with all of the subjects available in the bd
	@SuppressWarnings("unchecked")
	public Map<String, String> boxFiller() throws SQLException {
		String querySubject = "select codAsig,nombre from asignatura";
		ResultSet resultSubjects = statement.executeQuery(querySubject);
		while (resultSubjects.next()) {
			subjectContent.put(resultSubjects.getString("codAsig"), resultSubjects.getString("nombre"));
		}
		return subjectContent;
	}

	// TO VIEW DATA
	public void viewTeacher() {
		List<Teacher> listTeacher = new ArrayList<>();
		try {
			String insertquery = "select * from profesor";
			ResultSet result = statement.executeQuery(insertquery);
			if (result.next()) {
				Teacher teacher = new Teacher(result.getString("dni"), result.getString("nombre"),
						result.getString("apellidos"), result.getString("email"), result.getString("pass"));
				listTeacher.add(teacher);
			}
			for (Teacher t : listTeacher) {
				System.out.println(t);
			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
	}

	// TO VIEW DATA
	public void viewStudents() {
		List<Student> listStudents = new ArrayList<>();
		try {
			String insertquery = "select * from alumnos";
			ResultSet result = statement.executeQuery(insertquery);
			if (result.next()) {
				Student studen = new Student(result.getString("dni"), result.getString("nombre"),
						result.getString("apellidos"), result.getString("email"), result.getString("fecha_nac"),
						result.getString("foto"), Integer.parseInt(result.getString("telefono")),
						result.getString("pass"));
				listStudents.add(studen);
			}
			for (Student s : listStudents) {
				System.out.println(s);
			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
	}

	public void viewStudents(String dni) {
		try {

			String insertquery = "SELECT ";
			ResultSet result = statement.executeQuery(insertquery);
			if (result.next()) {

			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}

	}

	public Map<String, String> viewStudentsRA(String dni) {
		Map<String, String> ras = new HashMap<String, String>();
		try {
			// Falta terminar el view
			String insertquery = "SELECT c.idRa, c.nota FROM califica c, matricula m, ra r WHERE '" + dni
					+ "' = m.dniAlumno AND m.dniAlumno =c.dniAlumno AND r.id=c.idRa AND r.codAsig=m.codAsig;";

			ResultSet result = statement.executeQuery(insertquery);
			if (result.next()) {
				ras.put(result.getString("idRa"), result.getString("nota"));
				result.next();
				System.out.println(result.getString("idRa"));
			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
		return ras;
	}

	// TO UPDATE DATA
	public void update() {
		try {
			String insertquery = "UPDATE `table_name` set `field`='value',`field2`='value2' WHERE field = 'value'";
			statement.executeUpdate(insertquery);
			System.out.println("Updated");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	// TO DELETE DATA
	public void delete() {
		try {
			String insertquery = "DELETE FROM `table_name` WHERE field = 'value'";
			statement.executeUpdate(insertquery);
			System.out.println("Deleted");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
