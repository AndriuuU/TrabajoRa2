package clases;

public class Subjects {

	private String codSubject, name;
	private int hours;
	private String dniProfessor;

	public Subjects(String codSubject, String name, int hours, String dniProfessor) {
		super();
		this.codSubject = codSubject;
		this.name = name;
		this.hours = hours;
		this.dniProfessor = dniProfessor;
	}

	public Subjects() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCodSubject() {
		return codSubject;
	}

	public void setCodSubject(String codSubject) {
		this.codSubject = codSubject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getDniProfessor() {
		return dniProfessor;
	}

	public void setDniProfessor(String dniProfessor) {
		this.dniProfessor = dniProfessor;
	}

	@Override
	public String toString() {
		return "Subject [codSubject=" + codSubject + ", name=" + name + ", hours=" + hours + ", dniProfessor="
				+ dniProfessor + "]";
	}

}
