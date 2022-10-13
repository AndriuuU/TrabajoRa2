package clases;

public class Asignatura {

	private String codAsig, nombre;
	private int horas;
	private String dniProfesor;

	public Asignatura(String codAsig, String nombre, int horas, String dniProfesor) {
		super();
		this.codAsig = codAsig;
		this.nombre = nombre;
		this.horas = horas;
		this.dniProfesor = dniProfesor;
	}

	public Asignatura() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCodAsig() {
		return codAsig;
	}

	public void setCodAsig(String codAsig) {
		this.codAsig = codAsig;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public String getDniProfesor() {
		return dniProfesor;
	}

	public void setDniProfesor(String dniProfesor) {
		this.dniProfesor = dniProfesor;
	}

	@Override
	public String toString() {
		return "Asignatura [codAsig=" + codAsig + ", nombre=" + nombre + ", horas=" + horas + ", dniProfesor="
				+ dniProfesor + "]";
	}

}
