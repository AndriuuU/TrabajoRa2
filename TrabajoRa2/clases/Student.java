package clases;

public class Student {
	private String dni, name, surname, email, b_date, passw, photo;
	private int phone;

	public Student(String dni, String name, String surname, String email, String b_date, String photo, int phone,
			String passw) {
		super();
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.b_date = b_date;
		this.photo = photo;
		this.phone = phone;
		this.passw = passw;
	}

	public Student() {
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

	public String getB_date() {
		return b_date;
	}

	public void setB_date(String b_date) {
		this.b_date = b_date;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getPassw() {
		return passw;
	}

	public void setPassw(String passw) {
		this.passw = passw;
	}

	@Override
	public String toString() {
		return "Student [dni=" + dni + ", name=" + name + ", surname=" + surname + ", email=" + email + ", b_date="
				+ b_date + ", photo=" + photo + ", phone=" + phone + ", passw=" + passw + "]";
	}

}
