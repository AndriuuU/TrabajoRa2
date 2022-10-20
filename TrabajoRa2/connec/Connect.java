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

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import clases.Ra;
import clases.Student;
import clases.Subjects;
import clases.Teacher;
import windows.RaProfessorView;
import windows.RaStudentView;
import windows.StudentView;
import windows.SubjectsView;
import windows.TeacherRaGrade;
import windows.TeacherView;

public class Connect {
	static Statement statement;
	static Connection cc;
	private String host = "jdbc:mysql://localhost/school";
	private String user = "root";
	private String pass = "";
	private Map<String, String> students;
	private Map<String, String> teachers;
	public Map<String, String> subjectContent = new HashMap<String, String>();;
	@SuppressWarnings("unused")
	private List<String> subjects;

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
			String query = "INSERT INTO alumnos values('" + s.getDni() + "','" + s.getName() + "','" + s.getSurname()
					+ "','" + s.getEmail() + "','" + s.getB_date() + "','" + s.getPhoto() + "','" + s.getPhone() + "','"
					+ s.getPassw() + "');";
			statement.execute(query);
			System.out.println("Inserted");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "You are trying to insert an existing account", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void insertSubject(Subjects s) {
		try {
			String query = "INSERT INTO asignatura values('" + s.getCodSubject() + "','" + s.getName() + "','"
					+ s.getHours() + "','" + s.getDniProfessor() + "');";
			statement.execute(query);
			System.out.println("Inserted");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "You are trying to insert an existing subject", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Read the map(subjects) and compare with the array of checkbox to check wich
	// one is selected
	// if is selected inser dniStudent and idSubject
	public void insertMatricula(String d,String s) throws SQLException {
		String query = "INSERT INTO matricula values('" + d + "','" + s + "');";
		statement.execute(query);
	}
	public void deleteMatricula(String d,String s) throws SQLException {
		String query = "DELETE FROM matricula WHERE dniAlumno = '" + d + "' AND codAsig = '" + s + "';";
		statement.execute(query);
	}

	// To insert a new teacher, if dni already exist throw exception with a
	// joptionpane error
	public void insertTeacher(Teacher t) {
		try {
			// String dni, String nombre, String apellidos, String email
			String query = "INSERT INTO profesor values('" + t.getDni() + "','" + t.getName() + "','" + t.getSurname()
					+ "','" + t.getEmail() + "','" + t.getPasswd() + "');";
			System.out.println(query);
			statement.execute(query);
			System.out.println("Inserted");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "You are trying to insert an existing account", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void insertRa(Ra r) {

		try {
			String query = "INSERT INTO ra values('" + r.getId() + "','" + r.getName() + "','" + r.getDescription()
					+ "','" + r.getWeighting() + "','" + r.getCodSubject().getCodSubject() + "');";
			statement.execute(query);
			System.out.println("Inserted");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "You are trying to insert an existing account", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public List<Subjects> getEnrrolled(String dni) throws SQLException {
		List<String> matriculado = new ArrayList<String>();
		String queryMatriculas = "select codAsig from matricula where dniAlumno = '" + dni + "';";
		statement.execute(queryMatriculas);
		ResultSet resultMatricula = statement.executeQuery(queryMatriculas);
		while (resultMatricula.next()) {
			matriculado.add(resultMatricula.getString("codAsig"));
		}
		resultMatricula.close();
		List<Subjects> subjects = new ArrayList<Subjects>();

		for (String s : matriculado) {
			String querySubjects = "select codAsig,nombre from asignatura where codAsig = '" + s + "';";
			ResultSet resultSubjects = statement.executeQuery(querySubjects);
			while (resultSubjects.next()) {
				Subjects ss = new Subjects(resultSubjects.getString("codAsig"), resultSubjects.getString("nombre"), 0,
						"");
				subjects.add(ss);
			}
		}
		return subjects;
	}

	public List<Subjects> getNotEnrrolled(List<Subjects> enrrolled) throws SQLException {
		List<Subjects> raEnrrolled = new ArrayList<Subjects>();
		String queryMatriculas = "select codAsig,nombre from asignatura";
		statement.execute(queryMatriculas);
		ResultSet resultMatricula = statement.executeQuery(queryMatriculas);
		while (resultMatricula.next()) {
			boolean existe = false;
			String x = resultMatricula.getString("nombre");
			for (Subjects s : enrrolled) {
				if (x.equalsIgnoreCase(s.getName())) {
					existe = true;
				}
			}
			if (existe == false) {
				Subjects ss = new Subjects(resultMatricula.getString("codAsig"), resultMatricula.getString("nombre"), 0,
						"");
				raEnrrolled.add(ss);
			}

		}
		return raEnrrolled;

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
		String state = "";
		for (String key : students.keySet()) {
			if (logDni.equalsIgnoreCase(key)) {
				for (String valor : students.values()) {
					if (logPass.equalsIgnoreCase(valor)) {
						state = "sloged";
					} else {
						if (!state.equalsIgnoreCase("sloged"))
							state = "wrongpass";
					}
				}
			}
		}
		if (state.equalsIgnoreCase("")) {
			state = checkTeacher(logDni, logPass);
		}
		if (state.equalsIgnoreCase("loged"))
			new StudentView(logDni);

		// The value returned will be :
		// "" -> if user doesnt exist
		// "notaccepted" -> if the password is wrong
		// "studentaccepted" or "teacheraccepted" if the user and the password is
		// correct
		return state;
	}

	public String checkTeacher(String logDni, String logPass) {
		String state = "";
		for (String key : teachers.keySet()) {
			if (logDni.equalsIgnoreCase(key)) {
				for (String valor : teachers.values()) {
					if (logPass.equalsIgnoreCase(valor)) {
						state = "tloged";
					} else {
						if (!state.equalsIgnoreCase("tloged"))
							state = "wrongpass";
					}
				}
			}
		}
		return state;
	}

	// Fill subjectContent(Map) with all of the subjects available in the bd
	public Map<String, String> boxFiller() throws SQLException {
		String querySubject = "select codAsig,nombre from asignatura";
		ResultSet resultSubjects = statement.executeQuery(querySubject);
		while (resultSubjects.next()) {
			subjectContent.put(resultSubjects.getString("codAsig"), resultSubjects.getString("nombre"));
		}
		return subjectContent;
	}

	// TO VIEW DATA OF TEACHERS
	public List<Teacher> viewTeacher() {
		List<Teacher> listTeacher = new ArrayList<>();
		try {
			String insertquery = "select * from profesor";
			ResultSet result = statement.executeQuery(insertquery);
			while (result.next()) {
				Teacher teacher = new Teacher(result.getString("dni"), result.getString("nombre"),
						result.getString("apellidos"), result.getString("email"), result.getString("pass"));
				listTeacher.add(teacher);
			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
		return listTeacher;
	}

	public List<String> viewTeacherDni() {
		List<String> listDni = new ArrayList<>();
		try {
			String insertquery = "select dni from profesor";
			ResultSet result = statement.executeQuery(insertquery);
			while (result.next()) {
				listDni.add(result.getString("dni"));

			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
		return listDni;
	}

	public List<Ra> viewRa() {
		List<Ra> listRa = new ArrayList<>();
		try {
			String insertquery = "select * from ra";
			ResultSet result = statement.executeQuery(insertquery);
			while (result.next()) {
				Ra ra = new Ra(result.getString("id"), result.getString("nombre"), result.getString("descripcion"),
						Float.parseFloat(result.getString("ponderacion")), result.getString("codAsig"));
				listRa.add(ra);

			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
		return listRa;
	}

	// TO VIEW DATA
	public List<Student> viewStudents() {
		List<Student> listStudents = new ArrayList<>();
		try {
			String insertquery = "select * from alumnos";
			ResultSet result = statement.executeQuery(insertquery);
			while (result.next()) {
				Student studen = new Student(result.getString("dni"), result.getString("nombre"),
						result.getString("apellidos"), result.getString("email"), result.getString("fecha_nac"),
						result.getString("foto"), Integer.parseInt(result.getString("telefono")),
						result.getString("pass"));
				listStudents.add(studen);
			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
		return listStudents;
	}

	public List<Subjects> viewSubjects() {
		List<Subjects> listSubjects = new ArrayList<>();
		try {
			String insertquery = "select * from asignatura";
			ResultSet result = statement.executeQuery(insertquery);
			while (result.next()) {
				Subjects as = new Subjects(result.getString("codAsig"), result.getString("nombre"),
						Integer.parseInt(result.getString("horas")), result.getString("dniProfesor"));
				listSubjects.add(as);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Problem To Show Data");
		}
		return listSubjects;
	}

	public void viewStudents(String dni) {
		try {
			String insertquery = "SELECT r.codAsig,a.nombre, SUM(c.nota*(r.ponderacion/100)) 'Nota' FROM califica c, matricula m, ra r,asignatura a WHERE '"
					+ dni
					+ "' = m.dniAlumno AND m.dniAlumno =c.dniAlumno AND r.id=c.idRa AND r.codAsig=m.codAsig AND a.codAsig =r.codAsig GROUP BY r.codAsig;";
			ResultSet result = statement.executeQuery(insertquery);
			while (result.next()) {
				Object[] data = { result.getString("codAsig"), result.getString("nombre"), result.getString("nota") };
				StudentView.tablemodel.addRow(data);
			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
	}

	public void viewStudentsRA(String dni, String codAsig) {

		try {

			String insertquery = "SELECT c.idRa, r.nombre, c.nota, r.ponderacion FROM califica c, matricula m, ra r WHERE '"
					+ dni + "' = m.dniAlumno AND m.dniAlumno =c.dniAlumno AND r.id=c.idRa AND r.codAsig='" + codAsig
					+ "' GROUP BY r.id;";

			ResultSet result = statement.executeQuery(insertquery);

			while (result.next()) {

				Object[] data = { result.getString("idRa"), result.getString("nombre"), result.getString("nota"),
						result.getString("ponderacion") };

				RaStudentView.tablemodel.addRow(data);

			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}

	}

	public void viewTeacher(String dniTeacher) {
		try {

			String insertquery = "SELECT codAsig, nombre, horas FROM asignatura WHERE '" + dniTeacher
					+ "'=dniProfesor;";

			ResultSet result = statement.executeQuery(insertquery);

			while (result.next()) {

				Object[] data = { result.getString("codAsig"), result.getString("nombre"), result.getString("horas") };

				TeacherView.tablemodel.addRow(data);

			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
	}

	public void viewTeacherRa(String codAsig) {
		try {

			String insertquery = "SELECT id, nombre FROM ra WHERE '" + codAsig + "'=codAsig;";

			ResultSet result = statement.executeQuery(insertquery);

			while (result.next()) {

				Object[] data = { result.getString("id"), result.getString("nombre") };

				RaProfessorView.tablemodel.addRow(data);

			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
	}

	public void viewTeacherRaGrade(String codRa) {
		try {

			String insertquery = "SELECT a.nombre, c.nota FROM alumnos a, califica c WHERE c.dniAlumno=a.dni AND c.idRa='"
					+ codRa + "';";

			ResultSet result = statement.executeQuery(insertquery);

			while (result.next()) {

				Object[] data = { result.getString("nombre"), result.getString("nota") };

				TeacherRaGrade.tablemodel.addRow(data);

			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
	}

	public void viewTeacherFinalGrade(String codAsig) {
		try {

			String insertquery = "SELECT r.codAsig,alu.nombre, SUM(c.nota*(r.ponderacion/100)) 'Nota' "
					+ "FROM califica c, matricula m, ra r,asignatura a, alumnos alu " + "WHERE '" + codAsig
					+ "'=m.codAsig AND m.dniAlumno =c.dniAlumno AND r.id=c.idRa AND r.codAsig=m.codAsig AND a.codAsig =r.codAsig AND alu.dni=m.dniAlumno "
					+ "GROUP BY alu.dni;";

			ResultSet result = statement.executeQuery(insertquery);

			while (result.next()) {

				Object[] data = { result.getString("nombre"), result.getString("nota") };

				TeacherRaGrade.tablemodel.addRow(data);

			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
	}

	public Student getInfoStudent(String dni, List<Student> lista) {
		Student s;
		for (Student a : lista) {
			if (a.getDni().equals(dni)) {
				s = new Student(a.getDni(), a.getName(), a.getSurname(), a.getEmail(), a.getB_date(), a.getPhoto(),
						a.getPhone(), a.getPassw());
				return s;
			}
		}
		return null;

	}

	public String getInfoStudentDNI(String name) {
		try {
			String insertquery = "SELECT dni FROM alumnos WHERE '" + name + "'=nombre;";
			ResultSet result = statement.executeQuery(insertquery);

			if (result.next()) {
				return result.getString("dni");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

	public Subjects getSubjectRa(String cod) {
		try {

			String insertquery = "SELECT * FROM asignatura WHERE '" + cod + "'=codAsig;";

			ResultSet result = statement.executeQuery(insertquery);

			if (result.next()) {

				Subjects data = new Subjects(result.getString("codAsig"), result.getString("nombre"),
						Integer.parseInt(result.getString("horas")), result.getString("dniProfesor"));

				return data;

			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
		return null;
	}

	public Ra getRa(String cod) {
		try {

			String insertquery = "SELECT * FROM Ra WHERE '" + cod + "'=id;";

			ResultSet result = statement.executeQuery(insertquery);

			if (result.next()) {

				Ra data = new Ra(result.getString("id"), result.getString("nombre"), result.getString("descripcion"),
						Float.parseFloat(result.getString("ponderacion")), getSubjectRa(result.getString("codAsig")));

				return data;

			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
		return null;
	}

	public Teacher getTeacher(String cod) {
		try {

			String insertquery = "SELECT * FROM profesor WHERE '" + cod + "'=dni;";

			ResultSet result = statement.executeQuery(insertquery);

			if (result.next()) {

				Teacher data = new Teacher(result.getString("dni"), result.getString("nombre"),
						result.getString("apellidos"), result.getString("email"), result.getString("pass"));

				return data;

			}
		} catch (SQLException ex) {
			System.out.println("Problem To Show Data");
		}
		return null;
	}

	// TO UPDATE DATA
	public void updateStudent(Student s) {
		try {
			String studentQuery = "UPDATE alumnos set nombre = '" + s.getName() + "" + "', apellidos= '"
					+ s.getSurname() + "" + "', email= '" + s.getEmail() + "" + "', fecha_nac='" + s.getB_date() + ""
					+ "', foto='" + s.getPhoto() + "" + "', telefono='" + s.getPhone() + "" + "', pass='" + s.getPassw()
					+ "" + "' where dni = '" + s.getDni() + "';";
//			System.out.println(insertquery);
			statement.executeUpdate(studentQuery);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Update Error");
			e.printStackTrace();
		}
		System.out.println("Updated");
	}

	public void updateTeacher(Teacher t) {
		try {
			String teacherQuery = "UPDATE alumnos set nombre = '" + t.getName() + "" + "', apellidos= '"
					+ t.getSurname() + "" + "', email= '" + t.getEmail() + "" + "', fecha_nac='" + t.getPasswd() + "';";
			statement.executeUpdate(teacherQuery);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Update Error");
			e.printStackTrace();
		}
	}

	public void updateRa(Ra r) {
		try {
			String teacherQuery = "UPDATE ra set nombre= '" + r.getName() + "', descripcion= '" + r.getDescription()
					+ "', ponderacion='" + r.getWeighting() + "' WHERE id= '" + r.getId() + "' ;";
			statement.executeUpdate(teacherQuery);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Update Error");
			e.printStackTrace();
		}
		System.out.println("Update");
	}

	public void updateGradeRa(String dni, String codRa, float nota) {
		try {
			String teacherQuery = "UPDATE `califica` SET `nota`='" + nota + "' WHERE dniAlumno='" + dni
					+ "' AND `idRa`='" + codRa + "';";
			statement.executeUpdate(teacherQuery);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Update Error");
			e.printStackTrace();
		}
		System.out.println("Update");
	}

	public void deleteStudent(String dni) {
		try {
			int output = JOptionPane.showConfirmDialog(null, "Are you sure you want to perform this action? ",
					"Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (output == JOptionPane.YES_OPTION) {
				String deleteSubject = "DELETE FROM matricula WHERE dniAlumno = '" + dni + "';";
				statement.execute(deleteSubject);
				String updateCalificate = "DELETE FROM califica  WHERE dniAlumno = '" + dni + "';";
				statement.executeUpdate(updateCalificate);
				String deleteStudent = "DELETE FROM alumnos WHERE dni = '" + dni + "';";
				statement.execute(deleteStudent);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, deletion failed");
			e.printStackTrace();
		}
	}

	public void deleteSubject(String codAsig) {
		try {
			int output = JOptionPane.showConfirmDialog(null, "Are you sure you want to perform this action? ",
					"Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (output == JOptionPane.YES_OPTION) {
				String delSubject = "DELETE FROM asignatura WHERE codAsig = '" + codAsig + "'";
				statement.execute(delSubject);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, deletion failed");
		}
	}

	public void deleteRA(String codRa) {
		try {
			int output = JOptionPane.showConfirmDialog(null, "Are you sure you want to perform this action? ",
					"Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (output == JOptionPane.YES_OPTION) {
				String delRA = "DELETE FROM ra WHERE id = '" + codRa + "'";
				statement.execute(delRA);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, deletion failed");
		}
	}

	public void deleteRa(String cod) {
		try {
			int output = JOptionPane.showConfirmDialog(null, "Are you sure you want to perform this action? ",
					"Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (output == JOptionPane.YES_OPTION) {
				String delRa = "DELETE FROM ra WHERE id = '" + cod + "'";
				statement.execute(delRa);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, deletion failed");
		}
	}

	// TO DELETE DATA
	public void deleteTeacher(String dni) {
		try {
			int output = JOptionPane.showConfirmDialog(null, "Are u sure you want to delete this teacher?", "Message",
					JOptionPane.YES_NO_OPTION);
			if (output == JOptionPane.YES_OPTION) {
				String updateSubjects = "UPDATE asignatura SET dniProfesor = NULL WHERE dniProfesor = '" + dni + "'";
				statement.executeUpdate(updateSubjects);
				String deleteTeacher = "DELETE FROM `profesor` WHERE dni = '" + dni + "'";
				statement.execute(deleteTeacher);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, deletion failed");
			e.printStackTrace();
		}

	}

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
