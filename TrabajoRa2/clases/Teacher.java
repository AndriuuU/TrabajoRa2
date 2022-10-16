package clases;

public class Teacher {

	private String dni, name, surname, email, passwd;

	public Teacher(String dni, String name, String surname, String email, String passwd) {
		super();
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.passwd = passwd;
	}

	public Teacher() {
		super();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "Profesor [dni=" + dni + ", name=" + name + ", surnames=" + surname + ", email=" + email + "]";
	}

}
